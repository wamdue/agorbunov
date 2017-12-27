package ru.job4j.isp;

import java.util.List;

/**
 * Created on 27.12.17.
 * Menu elements.
 * @author Wamdue
 * @version 1.0
 */
public interface Element {
    /**
     * Menu action.
     */
    void execute();

    /**
     * Child elements.
     * @return list of elements.
     */
    List<Element> getElements();

    /**
     * Get element name.
     * @return - element name.
     */
    String getName();
}
