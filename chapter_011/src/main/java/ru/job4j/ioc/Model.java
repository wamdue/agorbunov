package ru.job4j.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 02.02.18.
 *
 * @author Wamdue
 * @version 1.0
 */
@Component
public class Model {
    /**
     * Model id.
     */
    private int id;
    /**
     * Model name.
     */
    private String name;
    /**
     * User.
     */
    private final User user;

    /**
     * Main constructor.
     * @param user - user to autowire.
     */
    @Autowired
    public Model(User user) {
        this.user = user;
    }

    /**
     * Get model id.
     * @return - id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set model id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get model name.
     * @return - name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set model name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user.
     * @return - user.
     */
    public User getUser() {
        return this.user;
    }
}
