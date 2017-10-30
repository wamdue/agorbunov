package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 21.07.17.
 * Testing SimpleList class.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleListTest {
    /**
     * Add 20 numbers to collection.
     * Size must be = 20.
     */
    @Test
    public void whenAddMoreThenTenItemsThenExtend() {
        SimpleList<Integer> numbers = new SimpleList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        int expect = 20;
        assertThat(numbers.size(), is(expect));
    }

    /**
     * Add 20 numbers to collection.
     * call get(19), expect 19.
     */
    @Test
    public void whenAddFiveItemsAndWantToGetItemTwoThenReturn() {
        SimpleList<Integer> numbers = new SimpleList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        int expect = 19;
        assertThat(numbers.get(19), is(expect));
    }

    /**
     * Add 20 items to collection.
     * Get Iterator.
     * call next() two times, expect 1.
     */
    @Test
    public void whenCallNextTwoTimesThenReturnOne() {
        SimpleList<Integer> numbers = new SimpleList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        Iterator<Integer> iterator = numbers.iterator();
        iterator.next();
        int expect = 1;
        assertThat(iterator.next(), is(expect));
    }

}