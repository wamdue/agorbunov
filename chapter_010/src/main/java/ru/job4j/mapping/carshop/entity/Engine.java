package ru.job4j.mapping.carshop.entity;

/**
 * Created on 19.01.18.
 * Engine type information.
 * @author Wamdue
 * @version 1.0
 */
public class Engine {
    /**
     * Engine type id.
     */
    private int id;
    /**
     * Engine type name.
     */
    private String name;

    /**
     * Get engine type id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new engine type id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get engine type name.
     * @return - type name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new engine type name.
     * @param name - new type name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
