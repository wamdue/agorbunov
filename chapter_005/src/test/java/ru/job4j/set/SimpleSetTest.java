package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 24.07.17.
 * Testing SimpleSet class.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleSetTest {
    /**
     * Have set filled with numbers from 0 to 19.
     * calls next() three times.
     * expect 2.
     */
    @Test
    public void whenAddTwentyUniqeItemsThenMustRetrieve() {
        SimpleSet<Integer> set = new SimpleSet<>();
        for (int i = 0; i < 20; i++) {
            set.add(i);
        }
        Iterator<Integer> iterator = set.iterator();
        iterator.next();
        iterator.next();
        int expect = 2;
        assertThat(iterator.next(), is(expect));
    }

    /**
     * Inserting several equal string in set.
     * expect size = 1.
     */
    @Test
    public void whenHaveSeveralQEqualItemsThenWork() {
        SimpleSet<String> set = new SimpleSet<>();
        for (int i = 0; i < 5; i++) {
            set.add("Hello");
        }
        int expect = 1;
        assertThat(set.size(), is(expect));
    }


}