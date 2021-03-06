package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Extended Console input, with input corrections.
 */
public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, ArrayList<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException ex) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter number.");
            }
        } while (invalid);
        return value;
    }
}