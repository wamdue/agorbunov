package ru.job4j.crud;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created on 23.10.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class DBConnection {

    private Connection connection;
    private static final Logger logger = Logger.getLogger(DBConnection.class);
    private Properties props = new Properties();

    /**
     * Establish connection to db.
     */
    private void connect() {
        try {
            String login = this.props.getProperty("login");
            String password = this.props.getProperty("password");
            String url = this.props.getProperty("url");
            this.connection = DriverManager.getConnection(url, login, password);
            logger.info("Connection to db users established successfully");
            this.createTable();
        } catch (SQLException e) {
            logger.error("Cannot establish connection to DB", e.fillInStackTrace());
        }
    }

    /**
     * Main constructor.
     */
    public DBConnection() {
        this.init();
        this.connect();
    }

    /**
     * Read params from file.
     */
    private void init() {
        try {
            this.props.load(new FileReader(new File("Init.properties")));
        } catch (IOException e) {
            logger.error("Cannot read Init.properties", e.fillInStackTrace());
        }
    }

    /**
     * create Table if needed.
     */
    private void createTable() {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("create_table"))) {
            statement.execute();
            logger.info("Tables created!");
        } catch (SQLException e) {
            logger.error("Cannot create tables", e.fillInStackTrace());
        }
    }

    /**
     * Get list of users by name.
     * @param name - name to search in db.
     * @return list of users, empty list if no one if found.
     */
    public User getUserByName(String name) {
        User user = new User();
        if (name != null) {
            try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("get_user_by_name"))) {
                statement.setString(1, name);
                try (ResultSet set = statement.executeQuery()) {
                    set.next();
                    user.setName(set.getString("name"));
                    user.setLogin(set.getString("login"));
                    user.setEmail(set.getString("email"));
                    user.setCreateDate(set.getTimestamp("createdate"));
                }
            } catch (SQLException e) {
                logger.error("Cannot make search from table users", e.fillInStackTrace());
            }
        }
        return user;
    }

    /**
     * Adding new user to db.
     * @param user - user to add.
     */
    public void addUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("add_user"))){
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.executeUpdate();
            logger.info("User Added");
        } catch (SQLException e) {
            logger.error("Cannot insert new user", e.fillInStackTrace());
        }
    }

    /**
     * Delete user from db.
     * @param name - name of user to delete.
     */
    public void deleteUser(String name) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("delete_user"))) {
            statement.setString(1, name);
            statement.executeUpdate();
            logger.info("User successfully deleted");
        } catch (SQLException e) {
            logger.error("Cannot delete user", e.fillInStackTrace());
        }
    }

    /**
     * Update user.
     * @param user - user to update.
     */
    public void updateUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("update_user"))) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.executeUpdate();
            logger.info("User successfully updated.");
        } catch (SQLException e) {
            logger.error("Cannot update user", e.fillInStackTrace());
        }
    }

    /**
     * Close connection to db.
     */
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.error("Cannot close connection", e.fillInStackTrace());
        }
    }
}
