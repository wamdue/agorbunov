package ru.job4j.fin;

import java.sql.Timestamp;

/**
 * Created on 11.10.17.
 * Entry bean.
 * @author Wamdue
 * @version 1.0
 */
public class Entry {
    /**
     * Site.
     */
    private String source;
    /**
     * Name of vacancy.
     */
    private String name;
    /**
     * Url of the vacancy.
     */
    private String url;
    /**
     * Time of post.
     */
    private Timestamp timestamp;

    /**
     * Get source site.
     * @return - current source site.
     */
    public String getSource() {
        return source;
    }

    /**
     * Set new source site.
     * @param source - source site.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Get current vacancy name.
     * @return - vacancy name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new vacancy name.
     * @param name - vacancy name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get current url.
     * @return - current url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set new url.
     * @param url - url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get current post time.
     * @return - post time.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Set new post time.
     * @param timestamp - post time.
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
