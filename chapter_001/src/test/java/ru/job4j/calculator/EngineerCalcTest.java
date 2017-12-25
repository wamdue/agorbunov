package ru.job4j.calculator;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 25.12.17.
 * Testing engineer calculator.
 * @author Wamdue
 * @version 1.0
 */
public class EngineerCalcTest {
    /**
     * Temp answer string.
     */
    private String temp = "-2 - exit\n"
            + "-1 - use previous result\n"
            + "1 - plus\n"
            + "2 - minus\n"
            + "3 - multiply\n"
            + "4 - divide\n"
            + "5 - Calc cos.\n"
            + "6 - Calc sin.\n"
            + "7 - Calc tan.\n"
            + "Enter value: \n"
            + "Enter value: \n"
            + "Result: %f\n"
            + "\n"
            + "-2 - exit\n"
            + "-1 - use previous result\n"
            + "1 - plus\n"
            + "2 - minus\n"
            + "3 - multiply\n"
            + "4 - divide\n"
            + "5 - Calc cos.\n"
            + "6 - Calc sin.\n"
            + "7 - Calc tan.\n";

    /**
     * When calculating cos.
     * Entering: 60, 0.
     * Expect: -0.952413.
     */
    @Test
    public void whenCalcCosThenGetSolution() {
        String expect = String.format(this.temp, -0.952413d);
        this.taskCase("5\n60\n0\n-2\n", expect);
    }

    /**
     * When calculating sin.
     * Entering: 60, 0.
     * Expect: -0.304811.
     */
    @Test
    public void whenCalcSinThenGetSolution() {
        String expect = String.format(this.temp, -0.304811d);
        this.taskCase("6\n60\n0\n-2\n", expect);
    }

    /**
     * When calculating tan.
     * Entering: 60, 0.
     * Expect: 0.320040.
     */
    @Test
    public void whenCalcTanThenGetSolution() {
        String expect = String.format(this.temp, 0.320040d);
        this.taskCase("7\n60\n0\n-2\n", expect);
    }

    /**
     * Main test method.
     * @param expression - menu expression.
     * @param expect - expecting result.
     */
    private void taskCase(String expression, String expect) {
        ByteArrayInputStream in = new ByteArrayInputStream(expression.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        EngineerCalc eng = new EngineerCalc(new Scanner(in));
        eng.start();
        assertThat(out.toString(), is(expect));

    }

}