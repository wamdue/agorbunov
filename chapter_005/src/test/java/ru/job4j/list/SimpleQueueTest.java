package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 24.07.17.
 * Testing SimpleQueue class.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleQueueTest {
    /**
     * Fill queue with 10 numbers, from 0 to 9.
     * call 2 times poll().
     * call peek() expect 2.
     */
    @Test
    public void whenAddTenItemsThenRemoveTwoThenPollTwo() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        queue.poll();
        queue.poll();
        int expect = 2;
        assertThat(queue.peek(), is(expect));
    }
}