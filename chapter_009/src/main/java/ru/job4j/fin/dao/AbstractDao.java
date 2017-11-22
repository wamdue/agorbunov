package ru.job4j.fin.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created on 21.11.17.
 * Abstract dao class, load properties, and get connection.
 * @author Wamdue
 * @version 1.0
 */
public class AbstractDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
    /**
     * Connection to db.
     */
    private Connection connection;
    /**
     * Properties.
     */
    private Properties props = new Properties();

    /**
     * Main constructor.
     * @param connection - connection to db.
     */
    public AbstractDao(Connection connection) {
        this.connection = connection;
        this.init();
    }

    /**
     * Load properties from file.
     */
    private void init() {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream input = cl.getResourceAsStream("psinit.properties");
            props.load(input);
            LOGGER.info("Properties loaded successfully");
        } catch (IOException e) {
            LOGGER.error("Properties cannot be loaded", e.fillInStackTrace());
        }
    }

    /**
     * Get properties.
     * @return - properties.
     */
    public Properties getProps() {
        return props;
    }

    /**
     * Get connection.
     * @return - connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
