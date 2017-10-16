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
    private PreparedStatement statement;
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
            conn = DriverManager.getConnection(prop.getProperty("url")
                    , prop.getProperty("user"), prop.getProperty("password"));
            statement = conn.prepareStatement(prop.getProperty("table"));
            statement.executeUpdate();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("Couldn`t establish connection with DB", e.fillInStackTrace());
        }
    }

    /**
     * Add new record to batch.
     * @param entryList - list of values to insert.
     */
    public void addBatch(List<Entry> entryList) {
        try {
            statement = conn.prepareStatement(prop.getProperty("insert"));
            for (Entry e : entryList) {
                if(this.isNewRecord(e)) {
                    statement.setString(1, e.getSource());
                    statement.setString(2, e.getName());
                    statement.setString(3, e.getUrl());
                    statement.setTimestamp(4, e.getTimestamp());
                    statement.addBatch();
                }
            }
            statement.executeBatch();
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
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Cannot close statement", e.fillInStackTrace());
            }
        }
    }

    private boolean isNewRecord(Entry entry) throws SQLException {
        statement = conn.prepareStatement(prop.getProperty("check_record"));
        statement.setString(1, entry.getName());
        ResultSet set = statement.executeQuery();
        set.next();
        int i = set.getInt(1);
        set.close();
        return i == 0;
    }

    /**
     * Check if no records in db.
     * @return result.
     */
    public boolean emptyDB() {
        boolean result = false;
        ResultSet set = null;

        try {
            statement = conn.prepareStatement(prop.getProperty("not_empty"));
            set =statement.executeQuery();
            set.next();
            result = set.getInt(1) == 0;
        } catch (SQLException e) {
            log.error("Cannot check size of db.", e.fillInStackTrace());
            e.printStackTrace();
        } finally {
            if (set!= null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    log.error("Cannot close result set", e.fillInStackTrace());
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
