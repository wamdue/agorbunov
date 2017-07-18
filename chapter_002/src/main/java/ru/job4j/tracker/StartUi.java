package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User interface class.
 */
public class StartUi {
    /**
     * @param EXIT - exit program.
     */
    private static final int EXIT = 6;
    /****/
    private ArrayList<Integer> ranges = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
    /**
     * @param input - class for drawing main menu, and read input.
     */
    private Input input;
    /**
     * @param tracker - class tracker.
     */
    private Tracker tracker;

    /**
     * @param args - console arguments.
     *             Entry point!
     */
    public static void main(String[] args) {
        new StartUi(new ValidateInput(), new Tracker()).init();
    }

    /**
     * Constructor.
     */
    public StartUi(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Init class for UI.
     */
    public void init() {
        String element;
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
        while (true) {
            menu.show();
            int value = input.ask("Select: ", ranges);
            if (value == EXIT) {
                break;
            }
            menu.select(value);
        }
    }
}