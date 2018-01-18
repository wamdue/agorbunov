package ru.job4j.mapping.carstorage;

/**
 * Created on 17.01.18.
 * Chassy information.
 * @author Wamdue
 * @version 1.0
 */
public class Chassy {
    /**
     * Chassy id.
     */
    private int id;
    /**
     * Chassy name.
     */
    private String name;

    /**
     * Get Chassy id.
     * @return Chassy id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set Chassy id.
     * @param id - Chassy id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Chassy name.
     * @return - Chassy name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Chassy name.
     * @param name Chassy name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
