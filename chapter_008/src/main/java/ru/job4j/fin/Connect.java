package ru.job4j.fin;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created on 13.10.17
 * Working with database.
 * @author Wamdue
 * @version 1.0
 */
public class Connect {
    private Connection conn;
    private Properties prop = new Properties();
    private final Logger log = Logger.getLogger(Connect.class);

    public Connect() {
        init();
    }

    /**
     * load properties file.
     */
    private void init() {
        try {
            prop.load(new FileReader(new File("Parse.properties")));
        } catch (IOException e) {
            log.error("Could not read parameter file", e.fillInStackTrace());
        }
    }

    /**
     * Connect to db
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
            conn.setAutoCommit(false);
            this.createTables();
        } catch (SQLException e) {
            log.error("Couldn`t establish connection with DB", e.fillInStackTrace());
        }
    }

    /**
     * Add new record to batch.
     * @param entryList - list of values to insert.
     */
    public void addBatch(List<Entry> entryList) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("insert"))) {
            for (Entry e : entryList) {
                if (this.isNewRecord(e)) {
                    preparedStatement.setString(1, e.getSource());
                    preparedStatement.setString(2, e.getName());
                    preparedStatement.setString(3, e.getUrl());
                    preparedStatement.setTimestamp(4, e.getTimestamp());
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            log.error("Cannot insert record to db", e.fillInStackTrace());
        }
    }

    /**
     * Close connection to db
     */
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e.fillInStackTrace());
            }
        }
    }

    private boolean isNewRecord(Entry entry) {
        boolean result = true;
        try (PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("check_record"))) {
            preparedStatement.setString(1, entry.getName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int i = resultSet.getInt(1);
                result = i == 0;
            } catch (SQLException ex) {
                log.error("Cannot execute query", ex.fillInStackTrace());
            }
        } catch (SQLException e) {
            log.error("Cannot create statement on query", e.fillInStackTrace());
        }
        return result;
    }

    /**
     * Check if no records in db.
     * @return result.
     */
    public boolean emptyDB() {
        boolean result = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("not_empty"));
        ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            result = resultSet.getInt(1) == 0;
        } catch (SQLException e) {
            log.error("Cannot check size of db.", e.fillInStackTrace());
            e.printStackTrace();
        }
        return result;
    }

    public void createTables() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("table"))) {
            preparedStatement.executeUpdate();
            conn.commit();
            prop.getProperty("table");
        } catch (SQLException e) {
            log.error("Cannot check tables", e.fillInStackTrace());
        }
    }
}
