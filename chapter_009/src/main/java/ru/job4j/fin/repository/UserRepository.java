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
import ru.job4j.fin.util.SearchType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 22.11.17.
 * User repository pattern realisation.
 * @author Wamdue
 * @version 1.0
 */
public class UserRepository extends UserDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RoleRepository.class);
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

    private final Map<String, Function<SearchType, List<User>>> search = new HashMap<>();

    /**
     * Main constructor.
     * @param connection - connection to db.
     */
    public UserRepository(Connection connection) {
        super(connection);
        this.roleDao = new RoleDao(this.getConnection());
        this.musicTypeDao = new MusicTypeDao(this.getConnection());
        this.addressDao = new AddressDao(this.getConnection());
        this.fillSearch();
    }

    /**
     * Fill dispatch map.
     */
    private void fillSearch() {
        this.search.put("address", this.addressSearch());
        this.search.put("role", this.roleSearch());
        this.search.put("type", this.musicTypeSearch());
    }
    /**
     * Find user and all entities for him by id.
     * @param id - user id.
     * @return - full user information.
     */
    public User getUserById(int id) {
        User user = this.findById(id);
        Address address = this.addressDao.findById(id);
        if (address != null && address.getId() != -1) {
            user.setAddress(address);
        }
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("select_user_roles"))) {
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

        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("select_user_music"))) {
            statement.setInt(1, user.getId());
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    int i = (set.getInt("type_id"));
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

        int userId = this.add(user);

        Address address = new Address();
        address.setId(userId);
        address.setAddress(user.getAddress().getAddress());
        this.addressDao.add(address);

        if (userId > 0) {
            for (Role role : user.getRoles()) {
                try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("add_role2user"))) {
                    statement.setInt(1, userId);
                    statement.setInt(2, role.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    LOGGER.error("Cannot insert user roles in DB.", e);
                }
            }

            for (MusicType musicType : user.getMusicTypes()) {
                try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("add_music2user"))) {
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
    private List<User> getUsersByAddress(String address) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("find_users_by_address"))) {
            statement.setString(1, "'%" + address + "%'");
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
    private List<User> getUsersByRole(Role role) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("find_users_by_role"))) {
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
    private List<User> getUsersByMusicType(MusicType type) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("find_users_by_music_type"))) {
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
                users.add(this.getUserById(s.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Search by address string.
     * @return - list of users.
     */
    private Function<SearchType, List<User>> addressSearch() {
        return type-> this.getUsersByAddress(type.getVal());
    }

    /**
     * Search by role.
     * @return - list of users.
     */
    private Function<SearchType, List<User>> roleSearch() {
        return type-> this.getUsersByRole(this.roleDao.findById(Integer.valueOf(type.getVal())));
    }

    /**
     * Search by music type.
     * @return - list of users.
     */
    private Function<SearchType, List<User>> musicTypeSearch() {
        return type-> this.getUsersByMusicType(this.musicTypeDao.findById(Integer.valueOf(type.getVal())));
    }

    /**
     * Main search methos.
     * @param searchType - search string with type.
     * @return - filled list of users.
     */
    public List<User> searchMethod(SearchType searchType) {
        return this.search.get(searchType.getType()).apply(searchType);
    }
}