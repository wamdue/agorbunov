package ru.job4j.fin.enums;

import ru.job4j.fin.model.PSConnection;

import java.sql.Connection;

/**
 * Created on 01.12.17.
 * Enum for singleton realisation.
 * @author Wamdue
 * @version 1.0
 */
public enum Connect {
    /**
     * Constant to call connection.
     */
    INSTANCE;
    /**
     * Connection variable.
     */
    private PSConnection connection;

    /**
     * Realization singleton constructor.
     */
    Connect() {
        this.connection = new PSConnection();
    }

    /**
     * Get connection.
     * @return - current connection.
     */
    public Connection getConnection() {
        return this.connection.getConnection();
    }
}
