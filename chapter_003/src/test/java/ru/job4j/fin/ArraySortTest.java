package ru.job4j.fin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 27.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class ArraySortTest {
    /**
     * Example list.
     */
    private String[] strings = new String[]{"K1\\SK1",
            "K1\\SK2",
            "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2",
            "K2",
            "K2\\SK1\\SSK1",
            "K2\\SK1\\SSK2"};

    /**
     * Have to order example list by ascending, and add absent elements.
     * K1
     * K1\SK1
     * K1\SK1\SSK1
     * K1\SK1\SSK2
     * K1\SK2
     * K2
     * K2\SK1
     * K2\SK1\SSK1
     * K2\SK1\SSK2"
     */
    @Test
    public void whenHaveArrayWithElementsInNotCorrectOrderThenOrder() {
        ArraySort sort = new ArraySort();
        List<String> expect = new ArrayList<>(Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1"
                , "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        assertThat(sort.sortedArray(strings, ArraySort.ASC), is(expect));
    }
    /**
     * Have to order example list by Descending, and add absent elements.
     * K2
     * K2\SK1
     * K2\SK1\SSK2
     * K2\SK1\SSK1
     * K1
     * K1\SK2
     * K1\SK1
     * K1\SK1\SSK2
     * K1\SK1\SSK1
     */
    @Test
    public void whenHaveToOrderByDescendingThenDo() {
        ArraySort sort = new ArraySort();
        List<String> expect = new ArrayList<>(Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2"
                , "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"));
        assertThat(sort.sortedArray(strings, ArraySort.DESC), is(expect));
    }
}