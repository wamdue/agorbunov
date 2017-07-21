package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 21.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class SimpleLinkedListTest {
    /**
     * Have collection with 20 elements (from 0 to 19).
     * expected size of 20 elements.
     */
    @Test
    public void whenAddingNewItemsThenSizeMustGrow() {
        SimpleLinkedList<Integer> numbers = new SimpleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        int expected = 20;
        assertThat(numbers.size(), is(expected));
    }
    /**
     * Have collection with 20 elements (from 0 to 19).
     * call get(13) expect 13.
     */
    @Test
    public void whenAddNewItemsThenGetSecondThenReturnOne() {
        SimpleLinkedList<Integer> numbers = new SimpleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        int expected = 13;
        assertThat(numbers.get(13), is(expected));
    }
    /**
     * Have collection with 20 elements (from 0 to 19).
     * class next() 3 times, expect 2.
     */
    @Test
    public void whenStartToIterateThenReternNextElement() {
        SimpleLinkedList<Integer> numbers = new SimpleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        Iterator<Integer> iterator = numbers.iterator();
        iterator.next();
        iterator.next();
        int expect = 2;
        assertThat(iterator.next(), is(expect));
    }
}