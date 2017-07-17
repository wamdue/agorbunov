package ru.job4j.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created on 17.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class ConvertListTest {
    /**
     * Testing array to list conveter.
     * have 1, 2, 3
     *     ,4, 5, 6
     *     ,7, 8, 9.
     * expecting 1, 2, 3, 4, 5, 6, 7, 8, 9.
     */
    @Test
    public void whenHaveMatrixThenMakeList() {
        List<Integer> expected = new ArrayList<>();
        int[][] source = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ConvertList convertList = new ConvertList();
        for (int i = 0; i < 9; i++) {
            expected.add(i+1);
        }
        assertThat(convertList.toList(source), is(expected));
    }

    /**
     * Testing list to array converter.
     * have : 0, 1, 2, 3, 4, 5, 6, 7, 8, 9.
     * expecting : 0, 1, 2
     *            ,3, 4, 5
     *            ,6, 7, 8
     *            ,9, 0, 0
     */
    @Test
    public void whenHaveListThenMakeArray() {
        List<Integer> list = new ArrayList<>();
        int[][] expected = new int[][] { {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 0, 0}};
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        ConvertList convertList = new ConvertList();
        assertThat(convertList.toArray(list, 4), is(expected));
    }
}
