package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing input stream for even numbers.
 * @author Wamdue
 * @version 1.0
 * @since 04.12.2017
 */
public class ByteStreamTest {
    /**
     * Have input stream with even number 500.
     * Expectimg result = true.
     */
    @Test
    public void ifHaveEvenNumberiNStreanThenTrue() {
        ByteStream stream = new ByteStream();
        boolean expect = true;
        String number = "500";
        ByteArrayInputStream in = new ByteArrayInputStream(number.getBytes());
        assertThat(expect, is(stream.isNumber(in)));
    }

    /**
     * Have input stream with not even number 501.
     * Expecting result = false.
     */
    @Test
    public void ifNotHaveEvenNumberiNStreanThenFalse() {
        ByteStream stream = new ByteStream();
        boolean expect = false;
        String number = "501";
        ByteArrayInputStream in = new ByteArrayInputStream(number.getBytes());
        assertThat(expect, is(stream.isNumber(in)));
    }


}