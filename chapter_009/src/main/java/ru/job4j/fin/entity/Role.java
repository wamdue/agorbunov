package ru.job4j.fin.entity;

/**
 * Created on 20.11.17.
 * Possible roles.
 * @author Wamdue
 * @version 1.0
 */
public class Role {
    /**
     * Id of the role.
     */
    private int id;
    /**
     * Name of the role.
     */
    private String name;

    /**
     * Get role id.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new id;
     * @param id - new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get role name.
     * @return - role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new role name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
