package ru.job4j.thread;

/**
 * Created on 08.09.17.
 * Dummy class.
 * @author Wamdue
 * @version 1.0
 */
public class Dummy {
    /**
     * Source string.
     */
    private String name;

    /**
     * Get source string.
     * @return - string.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new value of the string.
     * @param name - new string.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Main constructor.
     * @param name - string value.
     */
    public Dummy(String name) {
        this.name = name;
    }
}
