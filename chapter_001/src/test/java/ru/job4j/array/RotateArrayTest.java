package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test rotate array.
*/
public class RotateArrayTest {
    /**
    * Array 3 x 3.
    * source : 1 2 3
    *          4 5 6
    *          7 8 9
    * expect : 7 4 1
    *          8 5 2
    *          9 6 3
    */
    @Test
    public void whenArrayThreeByThreeThenRotate() {
	RotateArray arr = new RotateArray();
	int[][] source = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	int[][] expected = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
	assertThat(arr.rotate(source), is(expected));
    }
    /**
    * Array 2 x 2.
    * source : 1 2
    *          3 4
    * expect : 3 1
    *          4 2
    */
    @Test
    public void whenArrayTwoByTwoThenRotate() {
	RotateArray arr = new RotateArray();
	int[][] source = {{1, 2}, {3, 4}};
	int[][] expected = {{3, 1}, {4, 2}};
	assertThat(arr.rotate(source), is(expected));
    }
}