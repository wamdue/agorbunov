package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 27.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class LinkedSetTest {
    /**
     * Fill set with numbers from 0 to 9.
     * call next() three times.
     * Expect 2.
     */
    @Test
    public void whenAddTenItemsThenIterateThreeTimes(){
        LinkedSet<Integer> set = new LinkedSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }
        Iterator<Integer> iterator = set.iterator();
        iterator.next();
        iterator.next();
        int expect = 2;
        assertThat(iterator.next(), is(expect));
    }
}