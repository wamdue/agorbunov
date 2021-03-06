package ru.job4j.io;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 13.12.17.
 * Console chat test.
 * @author Wamdue
 * @version 1.0
 */
public class ConsoleChatTest {
    /**
     * Line separator.
     */
    private static final String LN = System.getProperty("line.separator");

    /**
     * Exit test.
     * When enter : закончить
     * Expecting exit loop and program.
     */
    @Test
    public void whenEnterEndWordThenExit() {
        this.taskCase("закончить", "До встречи!\n");
    }

    /**
     * Stop word test.
     * When enter : стоп
     * Expecting no answering on other words.
     */
    @Test
    public void whenEnterStopWordThenNoAnswer() {
        this.taskCase(Joiner.on(LN).join("стоп", "one", "закончить"), "Отойду на минутку.\n", "До встречи!\n");
    }

    /**
     * Answers test.
     * When enter several words.
     * Expecting answers from bot.
     */
    @Test
    public void whenEnterWordThenHaveAnswer() {
        this.taskCase(Joiner.on(LN).join("one", "two", "three", "закончить"), "", "До встречи!\n");
    }

    /**
     * Answers test.
     * When word : стоп
     * Then enter : продолжить
     * and then enters more words.
     * Expecting answers from bot.
     */
    @Test
    public void whenEnterStopWordAndContinueThenHaveAnswer() {
        this.taskCase(Joiner.on(LN).join("стоп", "продолжить", "three", "закончить"), "Отойду на минутку.\nПродолжаем разговор.\nДо встречи!\n");
    }

    /**
     * Frequency used code.
     *
     * @param example - example string.
     * @param expect  - expected result.
     */
    private void taskCase(String example, String expect) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(example.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ConsoleChat chat = new ConsoleChat();
        chat.startChat(inputStream, outputStream);
        assertThat(expect, is(outputStream.toString()));
    }

    /**
     * Temp method.
     * @param example - example string.
     * @param start - string starts.
     * @param end - string ends.
     */
    private void taskCase(String example, String start, String end) {

    ByteArrayInputStream inputStream = new ByteArrayInputStream(example.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    ConsoleChat chat = new ConsoleChat();
    chat.startChat(inputStream, outputStream);
    boolean expect = true;

    assertThat(expect, is(outputStream.toString().startsWith(start) && outputStream.toString().endsWith(end)));
    }

}