package ru.job4j.fin.model;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * Created on 20.11.17.
 * Connection class.
 * @author Wamdue
 * @version 1.0
 */
public class PSConnection {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PSConnection.class);
    /**
     *DB connection.
     */
    private Connection connection;
    /**
     * Properties with connection info.
     */
    private Properties props = new Properties();
    /**
     * Singleton variable.
     */
    private static volatile PSConnection instance;

    /**
     * Loading properties with connection info.
     */
    private void init() {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream input = cl.getResourceAsStream("ps.properties");
            props.load(input);
            LOGGER.info("DB properties loaded successfully!");
        } catch (IOException e) {
            LOGGER.error("DB properties cannot be loaded", e.fillInStackTrace());
        }
    }

    /**
     * Connecting to db.
     */
    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            String login = this.props.getProperty("login");
            String password = this.props.getProperty("password");
            String url = this.props.getProperty("url");
            Properties p = new Properties();
            p.setProperty("user", login);
            p.setProperty("password", password);

            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, p);
            PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
            ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);

            poolableConnectionFactory.setPool(connectionPool);
            PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);

            this.connection = dataSource.getConnection();
            LOGGER.info("Connection to db users established successfully");
        } catch (SQLException e) {
            LOGGER.error("Cannot establish connection to DB", e.fillInStackTrace());
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find postgresql connection driver", e.fillInStackTrace());
        }

    }

    /**
     * Get current connection to db.
     * @return - connection.
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Constructor constructor.
     */
    public  PSConnection() {
        this.init();
        this.connect();
        this.checkTables();
    }

    /**
     * Create tables if needed.
     */
    private void checkTables() {
        try (Statement statement = this.connection.createStatement()) {
            statement.addBatch(this.props.getProperty("create_users_table"));
            statement.addBatch(this.props.getProperty("create_roles_table"));
            statement.addBatch(this.props.getProperty("create_musictype_table"));
            statement.addBatch(this.props.getProperty("create_address_table"));
            statement.addBatch(this.props.getProperty("create_user_role_table"));
            statement.addBatch(this.props.getProperty("create_user_music_table"));
            statement.executeBatch();
            LOGGER.info("Tables created.");
        } catch (SQLException e) {
            LOGGER.error("Cannot create tables.", e);
        }
    }
}
