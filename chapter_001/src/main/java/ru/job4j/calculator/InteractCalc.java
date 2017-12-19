package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Created on 19.12.17.
 * Communication with user, leading srp.
 * @author Wamdue
 * @version 1.0
 */
public class InteractCalc {
    /**
     * Store result of calculation.
     */
    private double result;
    /**
     * Input method.
     */
    private final Scanner scanner;
    /**
     * Calculation class.
     */
    private Calculator calculator = new Calculator();
    /**
     * Plus action.
     */
    private static final int PLUS = 1;
    /**
     * Minus action.
     */
    private static final int MINUS = 2;
    /**
     * Multiply action.
     */
    private static final int MULTIPLY = 3;
    /**
     * Divide action.
     */
    private static final int DIVIDE = 4;
    /**
     * User previous result of calculation.
     */
    private static final int PREVIOUS = 5;
    /**
     * Exit number.
     */
    private static final int EXIT = 6;
    /**
     * List of menu strings.
     */
    private final List<String> list = new ArrayList<>();
    /**
     * Map of possible calculation actions.
     */
    private final Map<Integer, BiFunction<Double, Double, Double>> actions = new HashMap<>();

    /**
     * Main constructor.
     * @param scanner - method input.
     */
    public InteractCalc(Scanner scanner) {
        this.scanner = scanner;
        this.init();
        this.loadActions();
    }


    /**
     * Loading menu strings.
     */
    private void init() {
        this.list.add(String.format("%d - plus", PLUS));
        this.list.add(String.format("%d - minus", MINUS));
        this.list.add(String.format("%d - multiply", MULTIPLY));
        this.list.add(String.format("%d - divide", DIVIDE));
        this.list.add(String.format("%d - use previous result", PREVIOUS));
        this.list.add(String.format("%d - exit", EXIT));
    }

    /**
     * Loading possible actions.
     */
    private void loadActions() {
        this.actions.put(1, this.plus());
        this.actions.put(2, this.minus());
        this.actions.put(3, this.multiply());
        this.actions.put(4, this.divide());
    }

    /**
     * Cover for action plus.
     * @return - result of plus action.
     */
    private BiFunction<Double, Double, Double> plus() {
        return (val, val1) -> {
            this.calculator.add(val, val1);
            this.result = this.calculator.getResult();
            return this.result;
        };
    }

    /**
     * Cover for action minus.
     * @return - result of minus action.
     */
    private BiFunction<Double, Double, Double> minus() {
        return (val, val1) -> {
            this.calculator.substruct(val, val1);
            this.result = this.calculator.getResult();
            return this.result;
        };
    }

    /**
     * Cover for action multiply.
     * @return - result of multiply action.
     */
    private BiFunction<Double, Double, Double> multiply() {
        return (val, val1) -> {
            this.calculator.multiply(val, val1);
            this.result = this.calculator.getResult();
            return this.result;
        };
    }

    /**
     * Cover for action divide.
     * @return - result of divide action.
     */
    private BiFunction<Double, Double, Double> divide() {
        return (val, val1) -> {
            if (val1 > 0) {
                this.calculator.div(val, val1);
                this.result = this.calculator.getResult();
            } else {
                System.out.println("Error, division by zero!");
            }
            return this.result;
        };
    }

    /**
     * Draw menu in console.
     * @param x - lengs of menu.
     */
    private void show(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println(this.list.get(i));
        }
    }

    /**
     * Get value for variable.
     * @return - value.
     */
    private Double getVal() {
        System.out.println("Enter value: ");
        return this.scanner.nextDouble();
    }

    /**
     * Start main loop.
     */
    public void start() {
        int pos = 0;
        double val;
        double val1;
        while (!(EXIT == pos)) {
            this.show(this.list.size());
            pos = scanner.nextInt();
            if (pos == EXIT) {
                continue;
            } else if (pos == PREVIOUS) {
                this.show(4);
                pos = scanner.nextInt();
                val = this.result;
            } else {
                val = this.getVal();
            }
            val1 = this.getVal();
            this.actions.get(pos).apply(val, val1);
            System.out.println(String.format("Result: %f", this.result));
            System.out.println();
        }
    }

    /**
     * Starting calculator.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InteractCalc calc = new InteractCalc(scanner);
            calc.start();
        }
    }
}
