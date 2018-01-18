package ru.job4j.mapping.carstorage;

/**
 * Created on 17.01.18.
 * Gearbox information.
 * @author Wamdue
 * @version 1.0
 */
public class Gearbox {
    /**
     * Gearbox id.
     */
    private int id;
    /**
     * Gearbox name.
     */
    private String name;

    /**
     * Get Gearbox id.
     * @return Gearbox id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set Gearbox id.
     * @param id - Gearbox id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Gearbox name.
     * @return - Gearbox name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Gearbox name.
     * @param name Gearbox name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
