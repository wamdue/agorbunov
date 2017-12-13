package ru.job4j.socket;

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
 *
 * @author Wamdue
 * @version 1.0
 */
public class BotClientTest {
    /**
     * Exit emulation.
     * When sending to server "пока"
     * Expecting exit.
     * @throws Exception - exception.
     */
    @Test
    public void connect() throws Exception {
        this.testCase("пока", null);
    }

    /**
     * Frequency using code.
     * @param question - question to server.
     * @param expected - response from server.
     */
    private void testCase(String question, String expected) {
        try {
            Socket socketClient = mock(Socket.class);
            ByteArrayInputStream in = new ByteArrayInputStream(question.getBytes());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            when(socketClient.getInputStream()).thenReturn(in);
            when(socketClient.getOutputStream()).thenReturn(out);

            BotClient client = new BotClient(socketClient);

            client.connect(in);

            assertThat(out.toString(), is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}