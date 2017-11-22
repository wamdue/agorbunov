package ru.job4j.fin.repository;

import org.apache.log4j.Logger;
import ru.job4j.fin.dao.AddressDao;
import ru.job4j.fin.dao.MusicTypeDao;
import ru.job4j.fin.dao.RoleDao;
import ru.job4j.fin.dao.UserDao;
import ru.job4j.fin.entity.Address;
import ru.job4j.fin.entity.MusicType;
import ru.job4j.fin.entity.Role;
import ru.job4j.fin.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created on 22.11.17.
 * User repository pattern realisation.
 * @author Wamdue
 * @version 1.0
 */
public class UserRepository {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RoleRepository.class);
    /**
     * Connection to db.
     */
    private Connection connection;
    /**
     * Properties.
     */
    private Properties props = new Properties();
    /**
     * User dao copy..
     */
    private UserDao userDao;
    /**
     * Role dao copy.
     */
    private RoleDao roleDao;
    /**
     * MusicType dao copy.
     */
    private MusicTypeDao musicTypeDao;
    /**
     * Address dao copy.
     */
    private AddressDao addressDao;

    /**
     * Main constructor.
     * @param connection - connection to db.
     */
    public UserRepository(Connection connection) {
        this.connection = connection;
        this.init();
        this.userDao = new UserDao(this.connection);
        this.roleDao = new RoleDao(this.connection);
        this.musicTypeDao = new MusicTypeDao(this.connection);
        this.addressDao = new AddressDao(this.connection);
    }

    /**
     * Loading properties.
     */
    private void init() {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream input = cl.getResourceAsStream("psinit.properties");
            props.load(input);
            LOGGER.info("Init properties loaded successfully!");
        } catch (IOException e) {
            LOGGER.error("Init properties cannot be loaded", e);
        }
    }

    /**
     * Find user and all entities for him by id.
     * @param id - user id.
     * @return - full user information.
     */
    public User getUserbyId(int id) {
        User user = this.userDao.findById(id);
        Address address = user.getAddress();
        if (address != null && address.getId() > 0) {
            user.setAddress(this.addressDao.findById(address.getId()));
        }
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("select_user_roles"))) {
            statement.setInt(1, user.getId());
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    int i = set.getInt("role_id");
                    user.addRole(this.roleDao.findById(i));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot load user roles from DB.", e);
        }

        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("select_user_music"))) {
            statement.setInt(1, user.getId());
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    int i = (set.getInt("typeid"));
                    user.addMusicType(this.musicTypeDao.findById(i));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot load user music types from DB.", e);
        }

        return user;
    }

    /**
     * Adding new user to db.
     * @param user - user to add.
     */
    public void createUser(User user) {
        int addressId = this.addressDao.add(user.getAddress());
        if (addressId > 0) {
            user.getAddress().setId(addressId);
        }

        int userId = this.userDao.add(user);

        if (userId > 0) {
            for (Role role : user.getRoles()) {
                try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("add_role2user"))) {
                    statement.setInt(1, userId);
                    statement.setInt(2, role.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    LOGGER.error("Cannot insert user roles in DB.", e);
                }
            }

            for (MusicType musicType : user.getMusicTypes()) {
                try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("add_music2user"))) {
                    statement.setInt(1, userId);
                    statement.setInt(2, musicType.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    LOGGER.error("Cannot insert user music types in DB.", e);
                }
            }
            LOGGER.info(String.format("New user id# %d add to db.", userId));
        }

    }

    /**
     * Get list of users with address name.
     * @param address - address to search.
     * @return - list of users.
     */
    public List<User> getUsersByAddress(String address) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("find_users_by_address"))) {
            statement.setString(1, address);
            users = this.fillListOfUsers(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Cannot load list of users by address", e);
        }
        return users;
    }

    /**
     * Get list of users with current role.
     * @param role - source role.
     * @return - list of users.
     */
    public List<User> getUsersByRole(Role role) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("find_users_by_role"))) {
            statement.setInt(1, role.getId());
            users = this.fillListOfUsers(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Cannot load list of users by role", e);
        }

        return users;
    }

    /**
     * Get list of user with current music type.
     * @param type - current music type.
     * @return - list of users.
     */
    public List<User> getUsersByMusicType(MusicType type) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(this.props.getProperty("find_users_by_music_type"))) {
            statement.setInt(1, type.getId());
            users = this.fillListOfUsers(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Cannot load list of users by music type", e);
        }
        return users;
    }

    /**
     * Internal method, to work with result set.
      * @param set - source set.
     * @return - filled user list.
     */
    private List<User> fillListOfUsers(ResultSet set) {
        List<User> users = new ArrayList<>();
        try (ResultSet s = set) {
            while (s.next()) {
                users.add(this.getUserbyId(s.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}