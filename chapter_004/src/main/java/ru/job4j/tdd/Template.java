package ru.job4j.tdd;

import java.util.Map;

/**
 * Created on 28.12.17.
 * Main interface for string generators.
 * @author Wamdue
 * @version 1.0
 */
public interface Template {
    /**
     * String generator, like string format.
     * @param template - template string.
     * @param data - array of data to insert.
     * @return - formatted string.
     */
    String generate(String template, Map<String, String> data);
}
