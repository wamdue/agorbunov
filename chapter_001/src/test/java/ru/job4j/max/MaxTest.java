package ru.job4j.max;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Testing max value.
*/
public class MaxTest {
    /**
    * Testing max(1, 2) = 2.
    */
    @Test
    public void whenOneToTwoThenTwo() {
	Max max = new Max();
	int expected = 2;
	assertThat(max.max(1, 2), is(expected));
    }
    /**
    * Testing max(1, 1) = 1.
    */
    public void whenOneToOneThenOne() {
	Max max = new Max();
	int expected = 1;
	assertThat(max.max(1, 1), is(expected));
    }
}