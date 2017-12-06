package ru.job4j.tracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * List of all tasks.
 */
public class Tracker {
    /**
     * @param items - list of tasks.
     */
    private ArrayList<Item> items = new ArrayList<>();
    /**
     * Connection to db.
     */
    private Connection conn;
    /**
     * Statement for queries.
     */
    private PreparedStatement statement;
    /**
     * Properties for connection.
     */
    private Properties prop = new Properties();

    /**
     * Main constructor.
     */
    public Tracker() {
        this.init();
    }

    /**
     * @param item - item to add and generate id for it.
     * @return - item.
     */
    public Item add(Item item) {
        try {
            statement = conn.prepareStatement(prop.getProperty("create_item"));
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setTimestamp(3, item.getCreated());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Load properties from file.
     */
    private void init() {
        try {
            prop.load(new FileInputStream(new File("Actions.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.conn = this.connect();
    }

    /**
     * Get connection to db.
     *
     * @return - current connection.
     */
    private Connection connect() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
            statement = connection.prepareStatement(prop.getProperty("create_tracker_table"));
            statement.executeUpdate();
            statement = connection.prepareStatement(prop.getProperty("create_comment_table"));
            statement.executeUpdate();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Close db connection.
     */
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param item - item for update.
     */
    public void update(Item item) {
        try {
            statement = conn.prepareStatement(prop.getProperty("update_task"));
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setInt(3, item.getId());
            System.out.println(statement.executeUpdate());
            statement = conn.prepareStatement(prop.getProperty("create_comment"));
            if (item.getComments().size() > 0) {
                Comment comm = item.getComments().get(0);
                statement.setInt(1, item.getId());
                statement.setString(2, comm.getDesc());
                statement.setTimestamp(3, comm.getTimestamp());
                statement.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param item - delete current item from array.
     */
    public void delete(Item item) {
        try {
            statement = conn.prepareStatement(prop.getProperty("delete_from_comments"));
            statement.setInt(1, item.getId());
            statement.executeUpdate();
            statement = conn.prepareStatement(prop.getProperty("delete_from_tracker_by_id"));
            statement.setInt(1, item.getId());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return list as array.
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> list = new ArrayList<>();
        try {
            statement = conn.prepareStatement(prop.getProperty("select_from_tracker"));
            list = this.getItems(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param key - value to search in items.
     * @return array - array of finded values.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> array = null;
        try {
            statement = conn.prepareStatement(prop.getProperty("select_from_tracker_by_name"));
            statement.setString(1, "%" + key + "%");
            array = this.getItems(statement.executeQuery());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return array;
    }

    /**
     * Create Item for ResultSet.
     *
     * @param set - source set.
     * @return new item.
     */
    private Item getItem(ResultSet set) {
        Item item = null;
        try {
            set.next();
            item = new Item();
            item.setId(set.getInt("id"));
            item.setName(set.getString("name"));
            item.setDesc(set.getString("des"));
            item.setComments(this.getComments(item.getId()));
            item.setCreated(set.getTimestamp("creation_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }

    /**
     * Get items from result set.
     *
     * @param set - set to analyze.
     * @return - list of items.
     */
    private ArrayList<Item> getItems(ResultSet set) {
        ArrayList<Item> items = new ArrayList<>();

        try {
            Item item = null;
            while (set.next()) {
                item = new Item();
                item.setId(set.getInt("id"));
                item.setName(set.getString("name"));
                item.setDesc(set.getString("des"));
                item.setComments(getComments(item.getId()));
                item.setCreated(set.getTimestamp("creation_time"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return items;
    }

    /**
     * Return all comments for the task.
     *
     * @param id - id of the task.
     * @return list of task comments.
     */
    private ArrayList<Comment> getComments(int id) {
        ArrayList<Comment> comments = new ArrayList<>();
        ResultSet set = null;
        try {
            statement = conn.prepareStatement(prop.getProperty("select_comments"));
            statement.setInt(1, id);
            set = statement.executeQuery();
            Comment comment;
            while (set.next()) {
                comment = new Comment();
                comment.setId(set.getInt("id"));
                comment.setDesc(set.getString("desc"));
                comment.setTimestamp(set.getTimestamp("creation_time"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return comments;
    }

    /**
     * @param id - id the task, which you want to find.
     * @return searched item, or null if not found.
     */
    public Item findById(int id) {
        Item item = null;
        try {
            statement = conn.prepareStatement(prop.getProperty("select_from_tracker_by_id"));
            statement.setInt(1, id);
            item = this.getItem(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * @return position - return number of filled buckets.
     */
    public int size() {
        return this.findAll().size();
    }
}