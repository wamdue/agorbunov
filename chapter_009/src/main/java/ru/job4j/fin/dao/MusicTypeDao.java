package ru.job4j.fin.dao;

import org.apache.log4j.Logger;
import ru.job4j.fin.entity.MusicType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 21.11.17.
 * Music type manipulation.
 * @author Wamdue
 * @version 1.0
 */
public class MusicTypeDao extends AbstractDao implements EntityDao<MusicType> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MusicTypeDao.class);

    /**
     * Main constructor.
     * @param connection - db connection.
     */
    public MusicTypeDao(Connection connection) {
        super(connection);
    }

    /**
     * Add new music type to db.
     * @param musicType - new music type.
     * @return id.
     */
    @Override
    public int add(MusicType musicType) {
        int result = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("create_music_type"))) {
            statement.setString(1, musicType.getName());
            statement.executeUpdate();
            try (ResultSet set = statement.getGeneratedKeys()) {
                while (set.next()) {
                    result = set.getInt("id");
                }
            }
            LOGGER.info("New music type added to db.");
        } catch (SQLException e) {
            LOGGER.error("Cannot insert new music type to db.", e);
        }
        return result;
    }

    /**
     * Delete music type from db.
     * @param musicType - music type to delete.
     * @return - true if successful.
     */
    @Override
    public boolean delete(MusicType musicType) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("delete_music_type"))) {
            statement.setInt(1, musicType.getId());
            result = statement.executeUpdate() > 0;
            LOGGER.info("Music type deleted successfully.");
        } catch (SQLException e) {
            LOGGER.error("Cannot delete music type from db.", e);
        }
        return result;
    }

    /**
     * Update music type info in db.
     * @param id - entity id to update.
     * @param musicType - new music type.
     * @return true if updated.
     */
    @Override
    public boolean update(int id, MusicType musicType) {
        boolean result = false;
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("update_music_type"))) {
            statement.setString(1, musicType.getName());
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
            LOGGER.info("Music type updated successfully.");
        } catch (SQLException e) {
            LOGGER.error("Cannot update new music type to db.", e);
        }
        return result;
    }

    /**
     * Get music type by id.
     * @param id - id to search.
     * @return - music type.
     */
    @Override
    public MusicType findById(int id) {
        MusicType musicType = new MusicType();
        try (PreparedStatement statement = this.getConnection().prepareStatement(this.getProps().getProperty("select_music_type"))) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    musicType.setId(set.getInt("id"));
                    musicType.setName(set.getString("name"));
                }
            }
            LOGGER.info("Music type founded successfully.");
        } catch (SQLException e) {
            LOGGER.error("Cannot get music type from db.", e);
        }
        return musicType;
    }

    /**
     * Get list of music types.
     * @return list of music types.
     */
    @Override
    public List<MusicType> getAll() {
        List<MusicType> types = new ArrayList<>();
        try (Statement statement = this.getConnection().createStatement();
        ResultSet set = statement.executeQuery(this.getProps().getProperty("select_music_types"))) {
            MusicType type;
            while (set.next()) {
                type = new MusicType();
                type.setId(set.getInt("id"));
                type.setName(set.getString("name"));
                types.add(type);
            }
            LOGGER.info("Music types list filled successfully.");
        } catch (SQLException e) {
            LOGGER.error("Cannot fill list of music types..", e);
        }
        return types;
    }
}
