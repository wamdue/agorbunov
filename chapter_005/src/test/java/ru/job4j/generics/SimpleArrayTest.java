package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 20.07.17.
 * Testing simple array class.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleArrayTest {
    /**
     * testing containing of the item.
     * have array of strings from 0 to 9
     * call get(3) expect 3.
     */
    @Test
    public void whenAddingNewItemThenItCanBeFound() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        for (int i = 0; i < 10; i++) {
            simpleArray.add(String.valueOf(i));
        }
        assertThat(simpleArray.get(3), is("3"));
    }
    /**
     * testing containing of the item.
     * have array of strings from 0 to 9
     * call update(3, "10") expect get(3) = 10.
     */
    @Test
    public void whenUpdateRecordThenChange() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        for (int i = 0; i < 10; i++) {
            simpleArray.add(String.valueOf(i));
        }
        simpleArray.update(3, "10");
        assertThat(simpleArray.get(3), is("10"));
    }

    /**
     * Testing size of list.
     * When deleting item from list, then size of list, must be less, then before.
     */
    @Test
    public void whenRecordIsDeletedThenSizeMustBeLess() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        for (int i = 0; i < 10; i++) {
            simpleArray.add(String.valueOf(i));
        }
        simpleArray.delete("3");
        assertThat(simpleArray.size(), is(9));
    }
}