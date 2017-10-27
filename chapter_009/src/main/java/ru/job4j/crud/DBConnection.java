package ru.job4j.crud;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created on 23.10.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class DBConnection {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    /**
     * Main connection to db.
     */
    private Connection connection;
    /**
     * Properties to communicate with db.
     */
    private Properties props = new Properties();

    /**
     * Establish connection to db.
     */
    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            String login = this.props.getProperty("login");
            String password = this.props.getProperty("password");
            String url = this.props.getProperty("url");
            this.connection = DriverManager.getConnection(url, login, password);
            LOGGER.info("Connection to db users established successfully");
            this.createTable();
        } catch (SQLException e) {
            LOGGER.error("Cannot establish connection to DB", e.fillInStackTrace());
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find postgresql connection driver", e.fillInStackTrace());
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
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("Init.properties");
            this.props.load(input);
        } catch (IOException e) {
            LOGGER.error("Cannot read Init.properties", e.fillInStackTrace());
        }
    }

    /**
     * create Table if needed.
     */
    private void createTable() {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("create_table"))) {
            statement.execute();
            LOGGER.info("Tables created!");
        } catch (SQLException e) {
            LOGGER.error("Cannot create tables", e.fillInStackTrace());
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
                LOGGER.error("Cannot make search from table users", e.fillInStackTrace());
            }
        }
        return user;
    }

    /**
     * Adding new user to db.
     * @param user - user to add.
     */
    public void addUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("add_user"))) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.executeUpdate();
            LOGGER.info("User Added");
        } catch (SQLException e) {
            LOGGER.error("Cannot insert new user", e.fillInStackTrace());
        }
    }

    /**
     * Delete user from db.
     * @param id - id of user to delete.
     */
    public void deleteUser(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("delete_user"))) {
            statement.setInt(1, id);
            statement.executeUpdate();
            LOGGER.info("User successfully deleted");
        } catch (SQLException e) {
            LOGGER.error("Cannot delete user", e.fillInStackTrace());
        }
    }

    /**
     * Update user.
     * @param id - user id.
     * @param user - user to update.
     */
    public void updateUser(int id, User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("update_user"))) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setInt(4, id);
            statement.executeUpdate();
            LOGGER.info("User successfully updated.");
        } catch (SQLException e) {
            LOGGER.error("Cannot update user", e.fillInStackTrace());
        }
    }

    /**
     * Get list of all users from db.
     * @return - list of users, or empty list, if db is empty.
     */
    public List<User> listOfUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            try (ResultSet set = statement.executeQuery(this.props.getProperty("all_users"))) {
                while (set.next()) {
                    User user = new User();
                    user.setId(set.getInt("id"));
                    user.setName(set.getString("name"));
                    user.setLogin(set.getString("login"));
                    user.setEmail(set.getString("email"));
                    user.setCreateDate(set.getTimestamp("createdate"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get all users from db", e.fillInStackTrace());
        }
        return users;
    }

    /**
     * Close connection to db.
     */
    public void closeConnection() {
        try {
            this.connection.close();
            LOGGER.info("Connection closed successfully!");
        } catch (SQLException e) {
            LOGGER.error("Cannot close connection", e.fillInStackTrace());
        }
    }

    /**
     * Get user by id.
     * @param id - id source user.
     * @return - source user, or empty user, if not found.
     */
    public User getUserById(int id) {
        User user = new User();
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("get_user_by_id"))) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
            set.next();
            user.setId(set.getInt("id"));
            user.setName(set.getString("name"));
            user.setLogin(set.getString("login"));
            user.setEmail(set.getString("email"));
            user.setCreateDate(set.getTimestamp("createdate"));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get user by id", e.fillInStackTrace());
        }
        return user;
    }

    /**
     * Get instance of the class.
     * @return new instance.
     */
    public static DBConnection getInstance() {
        return new DBConnection();
    }
}