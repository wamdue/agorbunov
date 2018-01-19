package ru.job4j.mapping.carshop.model;

/**
 * Created on 19.01.18.
 * Singleton realisation.
 * @author Wamdue
 * @version 1.0
 */
public enum Connect {
    /**
     * Command string.
     */
    INSTANCE;
    /**
     * Link to connection.
     */
    private final DB db;

    /**
     * Main constructor.
     */
    Connect() {
        db = new DB();
    }

    /**
     * Get singleton item.
     * @return - db.
     */
    public DB getConnection() {
        return this.db;
    }
}
