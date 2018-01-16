package ru.job4j.config.db;

/**
 * Created on 16.01.18.
 * Singleton realisation.
 * @author Wamdue
 * @version 1.0
 */
public enum DB {
    /**
     * Single element.
     */
    INSTANCE;
    /**
     * Link to connection.
     */
    private Connection connection;

    /**
     * Main constructor.
     */
    DB() {
        this.connection = new Connection();
    }

    /**
     * Get link to connection.
     * @return connection.
     */
    public Connection getConnection() {
        return this.connection;
    }
}
