package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created on 12.12.17.
 * Client realization.
 * @author Wamdue
 * @version 1.0
 */
public class BotClient {
    /**
     * Connection socket.
     */
    private final Socket socket;

    /**
     * Main constructor.
     * @param socket - connection socket.
     */
    public BotClient(Socket socket) {
        this.socket = socket;
    }

    /**
     * Main chat class.
     * @param inputStream - stream to enter data.
     */
    public void connect(InputStream inputStream) {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            Scanner console = new Scanner(inputStream);
            String str;
            String ask;
            do {
                ask = console.nextLine();
                out.println(ask);
                str = in.readLine();
                while (!str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!ask.equals("пока"));
            out.println("exit");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Start client.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        try (final Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5000)) {
            BotClient client = new BotClient(socket);
            client.connect(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
