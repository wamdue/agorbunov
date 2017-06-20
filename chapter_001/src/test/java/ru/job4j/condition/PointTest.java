package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test calculation of Point class.
*/
public class PointTest {
    /**
    *  7 = 1 * 5 + 2.
    */
    @Test
    public void whenAOneAndBTwoThenSeven() {
	Point point = new Point(5, 7);
	boolean expected = true;
	assertThat(point.is(1, 2), is(expected));
    }
    /**
    *  7 != 2 * 5 + 2.
    */
    @Test
    public void whenATwoAndBTwoThenNotSeven() {
	Point point = new Point(5, 7);
	boolean expected = false;
	assertThat(point.is(2, 2), is(expected));
    }
}