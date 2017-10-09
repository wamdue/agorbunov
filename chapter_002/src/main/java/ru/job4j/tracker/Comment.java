package ru.job4j.tracker;

import java.sql.Timestamp;

/**
 * Created on 06.10.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class Comment {
    private int id;
    private String desc;
    private Timestamp timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
