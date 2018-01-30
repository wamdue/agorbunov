package ru.job4j.mapping.carshop.entity;

/**
 * Created on 19.01.18.
 * Body type.
 * @author Wamdue
 * @version 1.0
 */
public class Body {
    /**
     * Body type id.
     */
    private int id;
    /**
     * Body type name.
     */
    private String name;

    /**
     * Get Body type id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new Body type id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Body type name.
     * @return - type name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new Body type name.
     * @param name - new type name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
