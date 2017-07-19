package ru.job4j.generics;

/**
 * Created on 17.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class User {
    private int id;
    private String name;
    private String city;

    /**
     *
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
