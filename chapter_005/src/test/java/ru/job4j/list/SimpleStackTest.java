package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 24.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class SimpleStackTest {
    /**
     * Fill stack with numbers from 0 to 9.
     * call pop(), then call peek().
     * expect 8.
     */
    @Test
    public void whenPushingTenItemsThenCallPeek() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        stack.pop();
        int expect = 8;
        assertThat(stack.peek(), is(expect));
    }

}