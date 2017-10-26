package ru.job4j.crud;

import java.sql.Timestamp;

/**
 * Created on 23.10.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private Timestamp createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}