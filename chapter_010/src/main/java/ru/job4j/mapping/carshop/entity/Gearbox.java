package ru.job4j.mapping.carshop.entity;

/**
 * Created on 19.01.18.
 * Gearbox type.
 * @author Wamdue
 * @version 1.0
 */
public class Gearbox {
    /**
     * Gearbox type id.
     */
    private int id;
    /**
     * Gearbox type name.
     */
    private String name;

    /**
     * Get Gearbox type id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new Gearbox type id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Gearbox type name.
     * @return - type name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new Gearbox type name.
     * @param name - new type name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
