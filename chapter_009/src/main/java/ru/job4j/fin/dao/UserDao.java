package ru.job4j.fin.dao;

import org.apache.log4j.Logger;
import ru.job4j.fin.entity.Address;
import ru.job4j.fin.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20.11.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class UserDao extends AbstractDao implements EntityDao<User> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    /**
     * Main constructor.
     * @param connection - connection to db.
     */
    public UserDao(Connection connection) {
        super(connection);
    }

    /**
     * Insert new user into db.
     * @param user - user to insert.
     * @return id of the record.
     */
    @Override
    public int add(User user) {
        int result = 0;

        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("create_user"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            result = statement.executeUpdate();
            LOGGER.info("Add user to db.");
            try (ResultSet set = statement.getGeneratedKeys()) {
                while (set.next()) {
                    result = set.getInt("id");
                }
            }

        } catch (SQLException e) {
            LOGGER.error("User cannot be inserted to db.", e);
        }

        return result;
    }

    /**
     * Delete user from db.
     * @param user - user to delete.
     * @return - return true if deleted.
     */
    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("delete_user"))) {
            statement.setInt(1, user.getId());
            result = statement.executeUpdate() > 0;
            LOGGER.info("User has been delete from db.");
        } catch (SQLException e) {
            LOGGER.error("User cannot be inserted to db.", e);
        }
        return result;
    }

    /**
     * Update user informations in db.
     * @param id - entity id to update.
     * @param user - new user.
     * @return true if updated.
     */
    @Override
    public boolean update(int id, User user) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("update_user"))) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAddress().getId());
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
            LOGGER.info("User information updated.");
        } catch (SQLException e) {
            LOGGER.error("Cannot update user information.", e);
        }
           return result;
    }

    /**
     * Find user by id.
     * @param id - id to search.
     * @return - user.
     */
    @Override
    public User findById(int id) {
        User user = new User();
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("select_user"))) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    user.setId(set.getInt("id"));
                    user.setName(set.getString("name"));
                }
                LOGGER.info("User founded successfully");
            }
        } catch (SQLException e) {
            LOGGER.error("User cannot be found.", e);
        }
        return user;
    }

    /**
     * Get list of user from db.
     * @return - list of users.
     */
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = this.getConnection().createStatement();
        ResultSet set = statement.executeQuery(this.getProps().getProperty("select_users"))) {
            User user;
            while (set.next()) {
                user = new User();
                user.setId(set.getInt("id"));
                user.setName(set.getString("name"));
                users.add(user);
            }
            LOGGER.info("All users loaded!");
        } catch (SQLException e) {
            LOGGER.error("Cannot load oll users from db.", e);
        }
        return users;
    }
}
