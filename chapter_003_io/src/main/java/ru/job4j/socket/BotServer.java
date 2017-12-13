package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 12.12.17.
 * Oracle bot.
 * @author Wamdue
 * @version 1.0
 */
public class BotServer {
    /**
     * Server socket.
     */
    private final Socket socket;

    /**
     * Main constructor.
     * @param socket - server socket.
     */
    public BotServer(Socket socket) {
        this.socket = socket;
    }

    /**
     * Main start method.
     * @throws IOException - exception.
     */
    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (!"exit".equals(ask)) {
                out.println("I don`t understand.");
                out.println();
            }
        } while (!"exit".equals(ask));

    }

    /**
     * Start server.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        try (final Socket socket = new ServerSocket(5000).accept()) {
            BotServer server = new BotServer(socket);
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
