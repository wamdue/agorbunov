package ru.job4j.config.model;

import java.sql.Timestamp;

/**
 * Created on 11.01.18.
 * Data base table image.
 * @author Wamdue
 * @version 1.0
 */
public class Item {
    /**
     * Record id.
     */
    private int id;
    /**
     * Task description.
     */
    private String description;
    /**
     * Creation date time.
     */
    private Timestamp created;
    /**
     * Task status.
     */
    private int status;

    /**
     * Get tesk id.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set task id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get task description.
     * @return - description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set task description.
     * @param description - new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get task creation time.
     * @return creation time.
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Set task creation time.
     * @param created - new time.
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Task status.
     * @return status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set new task status.
     * @param status - new status.
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
