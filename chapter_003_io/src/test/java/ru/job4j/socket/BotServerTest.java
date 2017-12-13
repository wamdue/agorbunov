package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created on 12.12.17.
 * Testing server bot.
 * @author Wamdue
 * @version 1.0
 */
public class BotServerTest {
    /**
     * Line separator for answers.
     */
    private static final String LN = System.getProperty("line.separator");

    /**
     * When enter exit, expect no answer.
     * @throws IOException - exception.
     */
    @Test
    public void whenAskServerExitThenNoAnswer() throws IOException {
        this.testCase("exit", "");
    }

    /**
     * When enter hello, expecting answer.
     * Hello, dear friend, I'm a oracle.\n\n
     * @throws IOException - exception.
     */
    @Test
    public void whenAskServerKnownQuestionThenResult() throws IOException {
        this.testCase(Joiner.on(LN).join("hello", "exit"),
                Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "", ""));
    }

    /**
     * When enter unknown question, expecting answer.
     * I don`t understand.\n\n
     * @throws IOException - exception.
     */
    @Test
    public void whenAskServerUnknownQuestionThenResult() throws IOException {
        this.testCase(Joiner.on(LN).join("who are you", "exit"),
                Joiner.on(LN).join("I don`t understand.", "", ""));
    }

    /**
     * Repeatable part of the test.
     * @param ask - question to server.
     * @param expected - expected answer.
     * @throws IOException - exception.
     */
    protected void testCase(String ask, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(
                ask.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotServer server = new BotServer(socket);

        server.start();

        assertThat(out.toString(), is(expected));
    }
}