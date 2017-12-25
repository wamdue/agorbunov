package ru.job4j.calculator;

import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Created on 25.12.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class EngineerCalc extends InteractCalc {

    /**
     * Calculating cos.
     */
    private static final int COS = 5;
    /**
     * Calculating sin.
     */
    private static final int SIN = 6;
    /**
     * Calculating tan.
     */
    private static final int TAN = 7;

    /**
     * Main constructor.
     *
     * @param scanner - method input.
     */
    public EngineerCalc(Scanner scanner) {
        super(scanner);
        this.init();
        this.loadActions();
    }

    /**
     * Add new menu items.
     */
    private void init() {
        this.addMenuItem(String.format("%d - Calc cos.", COS));
        this.addMenuItem(String.format("%d - Calc sin.", SIN));
        this.addMenuItem(String.format("%d - Calc tan.", TAN));
    }

    /**
     * Add new actions to list.
     */
    private void loadActions() {
        this.addAction(COS, this.cos());
        this.addAction(SIN, this.sin());
        this.addAction(TAN, this.tan());
    }

    /**
     * Calculating cos value.
     * @return - value of cos.
     */
    private BiFunction<Double, Double, Double> cos() {
        return (val, val1) -> {
            this.setResult(Math.cos(val));
            return this.getResult();
        };
    }
    /**
     * Calculating sin value.
     * @return - value of sin.
     */
    private BiFunction<Double, Double, Double> sin() {
        return (val, val1) -> {
            this.setResult(Math.sin(val));
            return this.getResult();
        };
    }
    /**
     * Calculating tan value.
     * @return - value of tan.
     */
    private BiFunction<Double, Double, Double> tan() {
        return (val, val1) -> {
            this.setResult(Math.tan(val));
            return this.getResult();
        };
    }

    /**
     * Start method.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EngineerCalc calc = new EngineerCalc(scanner);
            calc.start();
        }
    }
}
