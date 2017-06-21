package ru.job4j.array;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Testing buble sort.
*/
public class BubleSortTest {
    /**
    * have [4, 2, 6, 1].
    * expecting [1, 2, 4, 6].
    */
    @Test
    public void whenUnsortedThenSort() {
	BubleSort sort = new BubleSort();
	int[] array = {4, 2, 6, 1};
	int[] expected = {1, 2, 4, 6};
	assertThat(sort.sort(array), is(expected));
    }
}