package ru.job4j.mapping.carshop.entity;


import org.springframework.data.annotation.Transient;

/**
 * Created on 19.01.18.
 * User information.
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
     * User password.
     */
    @Transient
    private String password;
    /**
     * Get User id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new User id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get User name.
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new User name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * User password.
     * @return - password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * User password.
     * @param password - new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
