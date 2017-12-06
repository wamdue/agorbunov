package ru.job4j.tracker;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Class for store information about task.
 */
public class Item {
    /**
     * @param id - number of task.
     */
    private int id;
    /**
     * @param name - name of the task.
     */
    private String name;
    /**
     * @param desc - description of the task.
     */
    private String desc;
    /**
     * List of comments.
     */
    private ArrayList<Comment> comments;
    /**
     * @param created - time of creation in mls.
     */
    private Timestamp created;

    /**
     * Empty constructor.
     */
    public Item() {
    }

    /**
     * Main constructor.
     *
     * @param name - name.
     * @param desc - description.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * @return id - returns id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return name - returns name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return desc - returns description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return comments - returns array of comments.
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * @return created - returns time of creation.
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * @param id - set id of the task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name - set new name of the task.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param desc - set new description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param created - set new time of creation.
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Set new list of comments.
     * @param comments - list of comments.
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}