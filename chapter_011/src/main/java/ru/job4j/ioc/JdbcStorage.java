package ru.job4j.ioc;

import java.util.List;
import java.util.Properties;

/**
 * JDBC storage of users.
 * @author Wamdue
 * @version 1.0
 * @since 04.02.2018
 */
public class JdbcStorage implements Storage {
    /**
     * Connection properties.
     */
    private Properties properties;

    /**
     * Main constructor.
     * @param properties db connections properties.
     */
    public JdbcStorage(Properties properties) {
        this.properties = properties;
    }

    /**
     * Add user to db.
     * @param user - user to add.
     */
    @Override
    public void add(User user) {

    }

    /**
     * Get list of users from db.
     * @return - list of users.
     */
    @Override
    public List<User> getUsers() {
        return null;
    }
}
