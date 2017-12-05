package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 05.12.17.
 * Testing censor filter.
 * @author Wamdue
 * @version 1.0
 */
public class StreamCensorTest {
    /**
     * Abuse words, to delete from input stream.
     */
    private String[] abuse = {"non", "hello", "news"};

    /**
     * Have String = "hello friends, non can do good news in this cruel world."
     * Have abuse words, variable abuse.
     * Expect in result = "friends, can do good in this cruel world."
     */
    @Test
    public void whenHaveInputStreamWithSomeWordsThenDeleteThem() {
        String line = "hello friends, non can do good news in this cruel world.";
        String expect = "friends, can do good in this cruel world.";
        try (ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            StreamCensor censor = new StreamCensor();
            censor.dropAbuses(in, out, this.abuse);
            System.out.println(out.toString());
            assertThat(expect, is(out.toString().trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}