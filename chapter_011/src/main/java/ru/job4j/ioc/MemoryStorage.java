package ru.job4j.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * Memory storage for users.
 * @author Wamdue
 * @version 1.0
 * @since 04.02.2018
 */

public class MemoryStorage implements Storage {
    /**
     * Array to store users.
     */
    private List<User> storage = new ArrayList<>();

    /**
     * Add user to storage.
     * @param user
     */
    @Override
    public void add(User user) {
        this.storage.add(user);
    }

    /**
     * Get list of users in storage.
     * @return - list of users.
     */
    public List<User> getUsers() {
        return this.storage;
    }
}
