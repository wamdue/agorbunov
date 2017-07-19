package ru.job4j.tracker;

import java.util.ArrayList;

/**
* Stub for test.
*/
public class StubInput implements Input {
    /**
    * @param answers - list of posibile answers.
    */
    private ArrayList<String> answers;
    /**
    * @param position - counter.
    */
    private int position = 0;
    /**
    *
    */
    public StubInput(ArrayList<String> answers) {
	this.answers = answers;
    }
    /**
    * @param question - string to show on console.
    * @return byte array for autotest.
    */
    public String ask(String question) {
	return answers.get(position++);
    }
    /**
    * @param question - string to show on console.
    * @param range - range of values.
    * @return byte array for autotest.
    */
    public int ask(String question, ArrayList<Integer> range) {
	return Integer.valueOf(answers.get(position++));
    }

}