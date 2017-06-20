package ru.job4j.loop;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Testing calulation of factioral.
*/
public class FactorialTest {
    /**
    *	n = 5.
    * result = 120.
    */
    @Test
    public void whenFiveThenHundredTwenty() {
	Factorial f = new Factorial();
	int expected = 120;
	assertThat(f.calc(5), is(expected));
    }
    /**
    *	n = 0.
    * result = 1.
    */
    @Test
    public void whenZeroThenOne() {
	Factorial f = new Factorial();
	int expected = 1;
	assertThat(f.calc(0), is(expected));
    }
    /**
    *	n = -1.
    * result = 1.
    */
    @Test
    public void whenMinusThenOne() {
	Factorial f = new Factorial();
	int expected = 1;
	assertThat(f.calc(-1), is(expected));
    }
}