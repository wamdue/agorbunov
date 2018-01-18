package ru.job4j.mapping.carstorage;

/**
 * Created on 17.01.18.
 * Engine information.
 * @author Wamdue
 * @version 1.0
 */
public class Engine {
    /**
     * Engine id.
     */
    private int id;
    /**
     * Engine name.
     */
    private String name;

    /**
     * Get Engine id.
     * @return engine id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set engine id.
     * @param id - engine id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get engine name.
     * @return - engine name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set engine name.
     * @param name engine name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
