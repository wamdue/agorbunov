package ru.job4j.fin.dao;

import org.apache.log4j.Logger;
import ru.job4j.fin.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 21.11.17.
 * Roles manipulations.
 * @author Wamdue
 * @version 1.0
 */
public class RoleDao extends AbstractDao implements EntityDao<Role> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RoleDao.class);
    /**
     * Main constructor.
     * @param connection - connection.
     */
    public RoleDao(Connection connection) {
        super(connection);
    }

    /**
     * Add new role to db.
     * @param role - role to insert.
     * @return - id of the role.
     */
    @Override
    public int add(Role role) {
        int result = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("create_role"))) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            try (ResultSet set = statement.getGeneratedKeys()) {
                while (set.next()) {
                    result = set.getInt("id");
                }
            }
            LOGGER.info("New role added to db.");
        } catch (SQLException e) {
            LOGGER.error("Cannot insert new role to db.", e);
        }
        return result;
    }

    /**
     * Delete role from db.
     * @param role - role to delete.
     * @return true if successfully.
     */
    @Override
    public boolean delete(Role role) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("delete_role"))) {
            statement.setInt(1, role.getId());
            result = statement.executeUpdate() > 0;
            LOGGER.info("Role deleted from db.");
        } catch (SQLException e) {
            LOGGER.error("Cannot delete role from db.", e);
        }
        return result;
    }

    /**
     * Update role in db.
     * @param id - entity id to update.
     * @param role - new role.
     * @return - true if successfully.
     */
    @Override
    public boolean update(int id, Role role) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("update_role"))) {
            statement.setString(1, role.getName());
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
            LOGGER.info("Role updated successfully.");
        } catch (SQLException e) {
            LOGGER.error("Cannot update role in db.", e);
        }
        return result;
    }

    /**
     * Find role by id.
     * @param id - id to search.
     * @return - role.
     */
    @Override
    public Role findById(int id) {
        Role role = new Role();
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("select_role"))) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    role.setId(set.getInt("id"));
                    role.setName(set.getString("name"));
                }
            }
            LOGGER.info("Role founded.");
        } catch (SQLException e) {
            LOGGER.error("Cannot find role in db.", e);
        }
        return role;
    }

    /**
     * Get list of all roles from db.
     * @return - list of roles, or empty list if not found
     */
    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try (Statement statement = this.getConnection().createStatement();
        ResultSet set = statement.executeQuery(this.getProps().getProperty("select_roles"))) {
            Role role;
            while (set.next()) {
                role = new Role();
                role.setId(set.getInt("id"));
                role.setName(set.getString("name"));
                roles.add(role);
            }
            LOGGER.info("List of roles filled successfully.");
        } catch (SQLException e) {
            LOGGER.error("Cannot fill list of roles.", e);
        }
        return roles;
    }
}
