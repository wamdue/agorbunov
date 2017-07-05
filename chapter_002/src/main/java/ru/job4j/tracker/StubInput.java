package ru.job4j.tracker;
/**
* Stub for test.
*/
public class StubInput implements Input {
    /**
    * @param answers - list of posibile answers.
    */
    private String[] answers;
    /**
    * @param position - counter.
    */
    private int position = 0;
    /**
    *
    */
    public StubInput(String[] answers) {
	this.answers = answers;
    }
    /**
    * @param question - string to show on console.
    * @return byte array for autotest.
    */
    public String ask(String question) {
	return answers[position++];
    }
}