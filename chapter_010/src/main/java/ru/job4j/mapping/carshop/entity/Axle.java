package ru.job4j.mapping.carshop.entity;

/**
 * Created on 19.01.18.
 * Axle type.
 * @author Wamdue
 * @version 1.0
 */
public class Axle {
    /**
     * Axle type id.
     */
    private int id;
    /**
     * Axle type name.
     */
    private String name;

    /**
     * Get Axle type id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new Axle type id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Axle type name.
     * @return - type name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new Axle type name.
     * @param name - new type name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
