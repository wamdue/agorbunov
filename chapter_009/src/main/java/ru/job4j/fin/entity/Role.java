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
     * Set new id.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (id != role.id) {
            return false;
        }
        return name != null ? name.equals(role.name) : role.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
