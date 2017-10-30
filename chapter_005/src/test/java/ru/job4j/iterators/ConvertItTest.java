package ru.job4j.iterators;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 20.07.17.
 * Testing ConvertIt class.
 * @author Wamdue
 * @version 1.0
 */
public class ConvertItTest {
    /**
     * Have 2 iterators in one.
     * each of them contains 1 value.
     * call next() two times.
     * Expect 2.
     */
    @Test
    public void whenItHasTwoInnerIt() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new ConvertIt().convert(it);
        convert.next();
        int result = convert.next();
        assertThat(result, is(2));
    }
    /**
     * Have 2 iterators in one.
     * each of them contains 1 value.
     * call next() two times, then hasNext.
     * Expect false.
     */
    @Test
    public void whenItHasTwoInnerItAndNoMoreElementsThenFalse() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new ConvertIt().convert(it);
        convert.next();
        convert.next();
        boolean result = convert.hasNext();
        assertThat(result, is(false));
    }

}