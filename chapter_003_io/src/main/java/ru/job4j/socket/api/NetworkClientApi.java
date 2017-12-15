package ru.job4j.socket.api;

import java.io.File;

/**
 * Created on 14.12.17.
 * Network client api.
 * @author Wamdue
 * @version 1.0
 */
public interface NetworkClientApi {
    /**
     * Receive file from server.
     * @param name - file to receive.
     */
    void receiveFile(String name);

    /**
     * Send file to server.
     * @param file - file to send.
     */
    void sendFile(File file);

    /**
     * Start client application.
     */
    void startClient();
}

