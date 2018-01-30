package ru.job4j.mapping.carshop.entity;

/**
 * Created on 19.01.18.
 * Brand name.
 * @author Wamdue
 * @version 1.0
 */
public class Brand {
    /**
     * Brand type id.
     */
    private int id;
    /**
     * Brand type name.
     */
    private String name;

    /**
     * Get Brand type id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new Brand type id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Brand type name.
     * @return - type name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new Brand type name.
     * @param name - new type name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
