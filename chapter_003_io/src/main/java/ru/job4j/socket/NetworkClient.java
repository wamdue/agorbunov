package ru.job4j.socket;

import ru.job4j.socket.api.NetworkClientApi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created on 14.12.17.
 * Client implementation.
 * @author Wamdue
 * @version 1.0
 */
public class NetworkClient implements NetworkClientApi {
    /**
     * Connection socket.
     */
    private Socket socket;

    /**
     * Main constructor.
     * @param socket - connection information.
     */
    public NetworkClient(Socket socket) {
        this.socket = socket;
    }

    /**
     * Default constructor.
     */
    public NetworkClient() {
        this.loadProperties();
    }

    /**
     * Receive file from server.
     * @param name - file to receive.
     */
    @Override
    public void receiveFile(String name) {
        File file = new File(name);
        System.out.println("File created : " + name);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            DataInputStream in = new DataInputStream(this.socket.getInputStream());
            byte[] buffer = new byte[1024 * 8];
            long available = in.readLong();
            int size;
            size = in.read(buffer);
            while (available > 0) {
                fos.write(buffer, 0, size);
                available -= size;
                if (available > 0) {
                    size = in.read(buffer);
                }
            }
            fos.flush();

            System.out.println(String.format("File downloaded successfully : %s", file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send file to server.
     * @param file - file to send.
     */
    @Override
    public void sendFile(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            out.writeLong(file.length());
            byte[] buffer = new byte[1024 * 8];
            int size = in.read(buffer);
            while (size > 0) {
                out.write(buffer, 0, size);
                size = in.read(buffer);
            }
            System.out.println(String.format("File uploaded successfully: %s", file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start client method.
     */
    public void startClient() {
        try (Scanner console = new Scanner(System.in);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            Answer answer = new Answer("");
            String str;
            while (!NetworkManager.STOP.equals(answer.getFullLine())) {
                answer =  new Answer(console.nextLine());
                if (NetworkManager.SAVE.equals(answer.getCommand())) {
                    out.println(answer.getFullLine());
                    this.receiveFile(answer.getDir());
                } else if (NetworkManager.LOAD.equals(answer.getCommand())) {
                    String name = answer.getDir();
                    File file = new File(name);
                    if (file.exists()) {
                        out.println(String.format("%s %s", NetworkManager.LOAD, file.getName()));
                        this.sendFile(file);
                        in.readLine();
                    } else {
                        System.out.println("File not found.");
                    }
                } else {
                    out.println(answer.getFullLine());
                    str = in.readLine();
                    if (str != null) {
                        while (!str.isEmpty()) {
                            System.out.println(str);
                            str = in.readLine();
                        }
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load properties from file.
     */
    private void loadProperties() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("app.properties")) {
            props.load(in);
            String url = props.getProperty("address");
            int port = Integer.valueOf(props.getProperty("port"));

            this.socket = new Socket(url, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Main method to start client.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            NetworkClient client = new NetworkClient();
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
