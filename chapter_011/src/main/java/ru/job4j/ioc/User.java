package ru.job4j.ioc;

/**
 * Created on 02.02.18.
 * Simple user.
 * @author Wamdue
 * @version 1.0
 */
public class User {
    /**
     * User id.
     */
    private int id;
    /**
     * User name.
     */
    private String name;

    /**
     * Get user id.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set user id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get user name.
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set user name.
     * @param name - user name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
