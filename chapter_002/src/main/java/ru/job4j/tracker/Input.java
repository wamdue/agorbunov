package ru.job4j.tracker;

import java.util.ArrayList;

/**
* Interface for input.
*/
public interface Input {
    /**
    * Main method to ask question and return answer.
     * @param question - message to display.
     * @return - complete string.
    */
    String ask(String question);
    /**
    * Additional methos to ask question and return answer with limit of range.
     * @param question - question to ask.
     * @param range - range of the parameters.
     * @return - number.
    */
    int ask(String question, ArrayList<Integer> range);
}