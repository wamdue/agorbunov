package ru.job4j.add;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28.02.18.
 * Entry view.
 * @author Wamdue
 * @version 1.0
 */
public class Entry {
    /**
     * Entry name.
     */
    private String name;
    /**
     * Is entry directory.
     */
    private boolean isDirectory;
    /**
     * List of child parents.
     */
    private List<Entry> entries = new ArrayList<>();
    /**
     * Root entry.
     */
    private Entry root;

    /**
     * Main constructor.
     */
    public Entry() {
    }

    /**
     * Constructor with entry name.
     * @param name - name.
     */
    public Entry(String name) {
        this.name = name;
        this.isDirectory = true;
    }

    /**
     * Constructor with entry name and directory sign.
     * @param name - name.
     * @param isDirectory - is entry directory.
     */
    public Entry(String name, boolean isDirectory) {
        this(name);
        this.isDirectory = isDirectory;
    }

    /**
     * Get entry name.
     * @return - get name.
     */
    String getName() {
        return name;
    }

    /**
     * Set entry new name.
     * @param name - new name.
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Directory status.
     * @return - status.
     */
    boolean isDirectory() {
        return isDirectory;
    }

    /**
     * Set directory status.
     * @param directory - new status.
     */
    void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    /**
     * Add new child, and set parent as this.
     * @param entry - child entry.
     */
    void addEntry(Entry entry) {
        entry.root = this;
        this.entries.add(entry);
    }

    /**
     * Get list of children.
     * @return - list.
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * Get parent entry.
     * @return - link to parent.
     */
    public Entry getRoot() {
        return root;
    }

    /**
     * Text.
     * @return - path.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        Entry parent = this.root;
        while (parent != null) {
            sb.insert(0, parent.getName());
            parent = parent.root;
        }
        return sb.toString();
    }
}
