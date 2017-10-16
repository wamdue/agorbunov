package ru.job4j.fin;

import java.sql.Timestamp;

/**
 * Created on 11.10.17
 * Entry bean.
 * @author Wamdue
 * @version 1.0
 */
public class Entry {
    private String source;
    private String name;
    private String url;
    private Timestamp timestamp;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
