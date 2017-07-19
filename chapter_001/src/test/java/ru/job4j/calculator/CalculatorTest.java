package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
*	Testing calculator.
*/
public class CalculatorTest {
	/**
	*	testing calculaton 1 + 1 = 2.
	*/
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	/**
	*	testing calculaton 4 / 2 = 2.
	*/
	@Test
    public void whenDivFourDivTwoThenTwo() {
        Calculator calc = new Calculator();
        calc.div(4D, 2D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	/**
	*	testing calculaton 2 * 3 = 6.
	*/
	@Test
    public void whenMultiplyTwoAndTreeThenSix() {
        Calculator calc = new Calculator();
        calc.multiply(2D, 3D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }
	/**
	*	testing calculaton 1 - 1 = 0.
	*/
	@Test
    public void whenSubstructOneMinusOneThenZero() {
        Calculator calc = new Calculator();
        calc.substruct(1D, 1D);
        double result = calc.getResult();
        double expected = 0;
        assertThat(result, is(expected));
    }
}