package ru.job4j.socket.api;

import java.io.File;

/**
 * Created on 13.12.17.
 * Network file manager.
 * @author Wamdue
 * @version 1.0
 */
public interface NetworkServerApi {
    /**
     * Stop server.
     */
    String STOP = "stop";
    /**
     * Save file from server.
     */
    String SAVE = "save";
    /**
     * Load file on server.
     */
    String LOAD = "load";
    /**
     * List of files in current directory.
     */
    String LIST = "ls";
    /**
     * Set current directory as paren directory.
     */
    String PARENT = "parent";
    /**
     * Show current directory.
     */
    String CURRENT = "current";
    /**
     * Set new current directory.
     */
    String SET = "set";
    /**
     * Get list of files in current dir.
     */
    void listOfFiles();

    /**
     * Load file to server.
     * @param file - file to load on server in currentDir;
     * @return - true if can load to server.
     */
    boolean receiveFile(File file);

    /**
     * Save file from server.
     * @param file - file to load.
     * @return - true if can save file.
     */
    boolean sendFile(File file);

    /**
     * Get current dir.
     * @return - current dir.
     */
    String getCurrentDir();

    /**
     * Set new current dir.
     * @param currentDir - new directory.
     */
    void setCurrentDir(String currentDir);

    /**
     * Move link of current dir, to parent dir.
     */
    void setToParentDir();

    /**
     * Starting server.
     * @param port - port where to start.
     */
    void startServer(int port);

}
