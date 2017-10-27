package ru.job4j.crud;

import java.sql.Timestamp;

/**
 * Created on 23.10.17.
 * User class.
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
     * User login.
     */
    private String login;
    /**
     * User email.
     */
    private String email;
    /**
     * User creation time.
     */
    private Timestamp createDate;

    /**
     * Get user name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new user name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user login.
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set new user login.
     * @param login - new login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get user email.
     * @return - email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set new user email.
     * @param email - new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user creation time.
     * @return - creation time.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Set user creation date.
     * @param createDate - new creation date.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Get user id.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new user id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }
}