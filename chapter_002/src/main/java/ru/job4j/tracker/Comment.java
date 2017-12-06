package ru.job4j.tracker;

import java.sql.Timestamp;

/**
 * Created on 06.10.17.
 * Comments.
 * @author Wamdue
 * @version 1.0
 */
public class Comment {
    /**
     * Comment id.
     */
    private int id;
    /**
     * Comment.
     */
    private String desc;
    /**
     * Creation time.
     */
    private Timestamp timestamp;

    /**
     * Get current id.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get comment description.
     * @return - comment.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Set new comment.
     * @param desc - comment.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Get creation time.
     * @return - creation time.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Set new creation time.
     * @param timestamp - creation time.
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
