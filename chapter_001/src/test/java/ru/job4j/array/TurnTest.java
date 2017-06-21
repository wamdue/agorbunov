package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class for testing reverse array.
*/
public class TurnTest {
    /**
    * enter [4, 1, 6, 2].
    * return [2, 6, 1, 4].
    */
    @Test
    public void whenArrayIsEvenThen() {
	Turn turn = new Turn();
	int[] array = {4, 1, 6, 2};
	int[] expected = {2, 6, 1, 4};
	assertThat(turn.back(array), is(expected));
    }
    /**
    * enter [1, 2, 3, 4, 5].
    * return [5, 4, 3, 2, 1].
    */
    @Test
    public void whenArrayIsNotEvenThen() {
	Turn turn = new Turn();
	int[] array = {1, 2, 3, 4, 5};
	int[] expected = {5, 4, 3, 2, 1};
	assertThat(turn.back(array), is(expected));
    }
}