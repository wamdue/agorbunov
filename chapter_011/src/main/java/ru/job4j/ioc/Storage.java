package ru.job4j.ioc;

import java.util.List;

/**
 * Simple storage interface.
 * @author Wamdue
 * @version 1.0
 * @since 04.02.2018
 */
public interface Storage {
    /**
     * Add user to storage.
     * @param user - user to add.
     */
    void add(User user);

    /**
     * Get all users from storage.
     * @return - list of users.
     */
    List<User> getUsers();
}

