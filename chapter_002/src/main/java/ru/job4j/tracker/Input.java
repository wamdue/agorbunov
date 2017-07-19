package ru.job4j.tracker;

import java.util.ArrayList;

/**
* Interface for input.
*/
public interface Input {
    /**
    * Main method to ask question and return answer.
    */
    String ask(String question);
    /**
    * Additional methos to ask question and return answer with limit of range.
    */
    int ask(String question, ArrayList<Integer> range);
}