package ru.job4j.strategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test strategy.
 */
public class PaintTest {
    /**
     * Square.
     * expected XXXXX
     * XXXXX
     * XXXXX
     * XXXXX
     * XXXXX
     */
    @Test
    public void whenHaveSquareThenMatch() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expected = "XXXXX\n"
                + "XXXXX\n"
                + "XXXXX\n"
                + "XXXXX\n"
                + "XXXXX\n";
        Paint paint = new Paint();
        paint.draw(new Square());
        assertThat(out.toString(), is(expected));
    }

    /**
     * Triangle.
     * expected
     *   X
     *  XXX
     * XXXXX
     */
    @Test
    public void whenHaveTriangleThenMatch() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expected = "  X  \n"
                + " XXX \n"
                + "XXXXX\n";
        Paint paint = new Paint();
        paint.draw(new Triangle());
        assertThat(out.toString(), is(expected));
    }
}