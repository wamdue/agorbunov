package ru.job4j.fin.dao;

import org.apache.log4j.Logger;
import ru.job4j.fin.entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 21.11.17.
 * Address manipulation.
 * @author Wamdue
 * @version 1.0
 */
public class AddressDao extends AbstractDao implements EntityDao<Address> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AddressDao.class);

    /**
     * Main constructor.
      * @param connection - connection to db.
     */
    public AddressDao(Connection connection) {
        super(connection);
    }

    /**
     * Add new address to db.
     * @param address - address.
     * @return - id in db, or 0 if not inserted.
     */
    @Override
    public int add(Address address) {
        int result = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("create_address"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, address.getId());
            statement.setString(2, address.getAddress());
            result = statement.executeUpdate();
            if (result > 0) {
                LOGGER.info("Address added to db.");
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot insert address to db.", e);
        }
        return result;
    }

    /**
     * Delete address from db.
     * @param address - address to delete
     * @return - true if deleted.
     */
    @Override
    public boolean delete(Address address) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("delete_address"))) {
            statement.setInt(1, address.getId());
            result = statement.executeUpdate() > 0;
            LOGGER.info("Address deleted successfully.");
        } catch (SQLException e) {
            LOGGER.error("Cannot delete address from db.", e);
        }
        return result;
    }

    /**
     * Update address in db.
     * @param id - entity id to update.
     * @param address - updated address.
     * @return true if updated.
     */
    @Override
    public boolean update(int id, Address address) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("update_address"))) {
            statement.setString(1, address.getAddress());
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
            LOGGER.info("Address updated successfully in db.");
        } catch (SQLException e) {
            LOGGER.error("Cannot update address in db.", e);
        }
        return result;
    }

    /**
     * Get address by id.
     * @param id - id to search.
     * @return - address.
     */
    @Override
    public Address findById(int id) {
        Address address = new Address();
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("select_address"))) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    address.setId(set.getInt(1));
                    address.setAddress(set.getString(2));
                }
            }
            LOGGER.info("Address found.");
        } catch (SQLException e) {
            LOGGER.error("Cannot find address in db.", e);
        }
        return address;
    }

    /**
     * Get all addresses.
     * @return - list of addresses.
     */
    @Override
    public List<Address> getAll() {
        List<Address> addresses = new ArrayList<>();
        try (Statement statement = this.getConnection().createStatement();
        ResultSet set = statement.executeQuery(this.getProps().getProperty("select_addresses"))) {
            Address address;
            while (set.next()) {
                address = new Address();
                address.setId(set.getInt("user_id"));
                address.setAddress(set.getString("name"));
                addresses.add(address);
            }
            LOGGER.info("Filled list of users.");
        } catch (SQLException e) {
            LOGGER.error("Cannot fill list of addresses.", e);
        }
        return addresses;
    }
}
