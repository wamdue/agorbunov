package ru.job4j.array;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Test for duplicates.
*/
public class ArrayDuplicateTest {
    /**
    * Main method.
    * have : hello, world, hello, super, world.
    * expect : hello, world, super.
    */
    @Test
    public void whenHaveDuplicatesThenRemove() {
	ArrayDuplicate example = new ArrayDuplicate();
	String[] source = {"hello", "world", "hello", "super", "world"};
	String[] expect = {"hello", "world", "super"};
	assertThat(example.remove(source), is(expect));
    }
}