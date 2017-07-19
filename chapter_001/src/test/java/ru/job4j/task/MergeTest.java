package ru.job4j.task;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Class for test merge arrays.
*/
public class MergeTest {
    /**
    * got : 1, 2, 3, 4, 5 and 6, 7, 8.
    * expected 1, 2, 3, 4, 5, 6, 7, 8.
    */
    @Test
    public void whenHaveTwoArraysInOrderThenSimpleMerge() {
	int[] first = {1, 2, 3, 4, 5};
	int[] second = {6, 7, 8};
	int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
	Merge merge = new Merge();
	assertThat(merge.merge(first, second), is(expected));
    }
    /**
    * got : 1, 2, 3, 7, 9 and 4, 5, 6, 8.
    * expected 1, 2, 3, 4, 5, 6, 7, 8, 9.
    */
    @Test
    public void whenHaveTwoArraysNotInOrderThenMerge() {
	int[] first = {1, 2, 3, 7, 9};
	int[] second = {4, 5, 6, 8};
	int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	Merge merge = new Merge();
	assertThat(merge.merge(first, second), is(expected));
    }
}