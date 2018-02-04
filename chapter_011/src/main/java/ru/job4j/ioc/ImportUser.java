package ru.job4j.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Using storage.
 * @author Wamdue
 * @version 1.0
 * @since 04.02.2018
 */
@Component
public class ImportUser {
    /**
     * Storage for users.
     */
    private Storage storage;

    /**
     * Main constructor.
     * @param storage - storage.
     */
    @Autowired
    public ImportUser(Storage storage) {
        this.storage = storage;
    }

    /**
     * Import user to storage.
     * @param user - user to import.
     */
    public void add(User user) {
        this.storage.add(user);
    }

    /**
     * Get list of users from storage.
     * @return - list of users.
     */
    public List<User> getUsers() {
        return this.storage.getUsers();
    }
}
