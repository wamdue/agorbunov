package ru.job4j.generics;

/**
 * Created on 17.07.17.
 * User information.
 * @author Wamdue
 * @version 1.0
 */
public class User {
    /**
     * User if.
     */
    private int id;
    /**
     * User name.
     */
    private String name;
    /**
     * City of user.
     */
    private String city;

    /**
     * Main constructor.
     * @param id - user id.
     * @param name - user name.
     * @param city - user city.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     *
     * @return user id.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return user city.
     */
    public String getCity() {
        return city;
    }
}
