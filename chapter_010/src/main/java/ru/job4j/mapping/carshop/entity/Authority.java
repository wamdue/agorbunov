package ru.job4j.mapping.carshop.entity;


/**
 * Created on 20.02.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class Authority {
    private int id;
    private User user;
    private String role;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
