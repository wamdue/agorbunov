package ru.job4j.loop;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Testing counter class.
*/
public class CounterTest {
    /**
    * Start from 1 to 20. result = 110.
    */
    @Test
    public void whenStartFromOneToTwentyThen() {
	Counter counter = new Counter();
	int expected = 110;
	assertThat(counter.add(1, 20), is(expected));
    }
    /**
    * Start from 20 to 1. result = 0.
    */
    @Test
    public void whenStartFromTwentyToOneThenZero() {
	Counter counter = new Counter();
	int expected = 0;
	assertThat(counter.add(20, 1), is(expected));
    }
    /**
    * Start from 20 to 20. result = 0.
    */
    @Test
    public void whenStartFromTwentyToTwentyThen() {
	Counter counter = new Counter();
	int expected = 0;
	assertThat(counter.add(20, 20), is(expected));
    }
}