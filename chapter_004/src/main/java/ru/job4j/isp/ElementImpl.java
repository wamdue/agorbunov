package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27.12.17.
 * Menu element realization.
 * @author Wamdue
 * @version 1.0
 */
public class ElementImpl implements Element {
    /**
     * Name of element.
     */
    private String name;
    /**
     * Child elements.
     */
    private List<Element> elements = new ArrayList<>();

    /**
     * Main constructor.
     * @param name - name of the element.
     */
    public ElementImpl(String name) {
        this.name = name;
    }

    /**
     * Menu action.
     */
    @Override
    public void execute() {

    }

    /**
     * Get list of child elements.
     * @return - child elements.
     */
    @Override
    public List<Element> getElements() {
        return this.elements;
    }

    /**
     * Get element name.
     * @return - element name.
     */
    @Override
    public String getName() {
        return this.name;
    }


}
