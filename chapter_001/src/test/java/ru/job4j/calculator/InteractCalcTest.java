package ru.job4j.calculator;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 19.12.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class InteractCalcTest {
    /**
     * Template string format answer when do one action, with custom result.
     */
    private String temp = "1 - plus\n"
            + "2 - minus\n"
            + "3 - multiply\n"
            + "4 - divide\n"
            + "5 - use previous result\n"
            + "6 - exit\n"
            + "Enter value: \n"
            + "Enter value: \n"
            + "Result: %f\n"
            + "\n"
            + "1 - plus\n"
            + "2 - minus\n"
            + "3 - multiply\n"
            + "4 - divide\n"
            + "5 - use previous result\n"
            + "6 - exit\n";

    /**
     * When want to exit menu, then exit.
     */
    @Test
    public void whenEnterExitThenExit() {
        String expression = "6\n";
        String expect = "1 - plus\n2 - minus\n3 - multiply\n4 - divide\n5 - use previous result\n6 - exit\n";
        this.taskCase(expression, expect);
    }

    /**
     * When want to plus 2 numbers:
     * Do : 4 + 6.
     * Expect: 10.
     */
    @Test
    public void whenWantPlusActionThenCalculate() {
        String expression = "1\n4\n6\n6";
        String expect = String.format(this.temp, 10d);
        this.taskCase(expression, expect);
    }

    /**
     * When want to substruct 2 numbers:
     * Do : 6 - 4.
     * Expect: 2.
     */
    @Test
    public void whenWantMinusActionThenCalculate() {
        String expression = "2\n6\n4\n6";
        String expect = String.format(this.temp, 2d);
        this.taskCase(expression, expect);
    }

    /**
     * When want to multiply 2 numbers:
     * Do : 6 * 4.
     * Expect: 24.
     */
    @Test
    public void whenWantMultiplyActionThenCalculate() {
        String expression = "3\n6\n4\n6";
        String expect = String.format(this.temp, 24d);
        this.taskCase(expression, expect);
    }

    /**
     * When want to divide 2 numbers:
     * Do : 6 / 2.
     * Expect: 3.
     */
    @Test
    public void whenWantDivideActionThenCalculate() {
        String expression = "4\n6\n2\n6";
        String expect = String.format(this.temp, 3d);
        this.taskCase(expression, expect);
    }

    /**
     * When want to divide 2 numbers and then add another to result:
     * Do : 6 / 2.
     * Expect: 5.
     * Add 3.
     * Expect: 5.
     */
    @Test
    public void whenWantUsePreviousResultThenCalculate() {
        String expression = "4\n6\n2\n5\n1\n2\n6";
        String tmp = "1 - plus\n"
                + "2 - minus\n"
                + "3 - multiply\n"
                + "4 - divide\n"
                + "Enter value: \n"
                + "Result: %f\n"
                + "\n"
                + "1 - plus\n"
                + "2 - minus\n"
                + "3 - multiply\n"
                + "4 - divide\n"
                + "5 - use previous result\n"
                + "6 - exit\n";
        String expect = String.format(this.temp + tmp, 3d, 5d);
        this.taskCase(expression, expect);
    }

    /**
     * Main test method, to remove doubles..
     * @param expression - menu selections.
     * @param expect     - expect answer.
     */
    private void taskCase(String expression, String expect) {
        ByteArrayInputStream in = new ByteArrayInputStream(expression.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        InteractCalc calc = new InteractCalc(new Scanner(in));
        calc.start();
        assertThat(out.toString(), is(expect));
    }

}