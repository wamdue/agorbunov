package ru.job4j.crud.model;

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
     * User password.
     */
    private String password;
    /**
     * User role.
     */
    private Role role;
    /**
     * User city.
     */
    private String city;
    /**
     * User country.
     */
    private String country;

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

    /**
     * Get current user password.
     * @return - user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set new user password.
     * @param password - new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get current user role.
     * @return - user role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set new user role.
     * @param role - new Role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Get user city.
     * @return - user city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set new user city.
     * @param city - new user city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get current user country.
     * @return - user country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set new user country.
     * @param country - new country.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}