package ru.job4j.jdbc;

/**
 * Created on 04.10.17.
 * Field information.
 * @author Wamdue
 * @version 1.0
 */
public class Field {
    /**
     * Value of the field.
     */
    private int value;

    /**
     * Get current value.
     * @return - value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Set new value.
     * @param value - new value.
     */
    public void setValue(int value) {
        this.value = value;
    }
}
