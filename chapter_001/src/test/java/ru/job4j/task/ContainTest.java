package ru.job4j.task;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Class for test Contain.java.
*/
public class ContainTest {
    /**
    * Origin - string "Hello World."
    * sub - "el"
    * expect - true.
    */
    @Test
    public void whenContainsThenTrue() {
	Contain contain = new Contain();
	boolean expect = true;
	String origin = "Hello World.";
	String sub = "el";
	assertThat(contain.contains(origin, sub), is(expect));
    }
    /**
    * Origin - string "Hello World."
    * sub - "tr"
    * expect - false.
    */
    @Test
    public void whenNotContainsThenFalse() {
	Contain contain = new Contain();
	boolean expect = false;
	String origin = "Hello World.";
	String sub = "tr";
	assertThat(contain.contains(origin, sub), is(expect));
    }
}