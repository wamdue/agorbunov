package ru.job4j.socket;

import ru.job4j.socket.api.NetworkServerApi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

/**
 * Created on 13.12.17.
 * Network file manager.
 * @author Wamdue
 * @version 1.0
 */
public class NetworkManager implements NetworkServerApi {
    /**
     * Current directory.
     */
    private File currentDir;
    /**
     * Connection socket.
     */
    private Socket socket;

    /**
     * Map of the actions.
     */
    private Map<String, Function<String, Boolean>> map = new HashMap<>();

    /**
     * Basic constructor.
     * Read port number from file.
     * @param currentDir - set current server dir.
     */
    public NetworkManager(File currentDir) {
        this.currentDir = currentDir;
        this.loadProperties();
        this.init();
    }
    /**
     * Main constructor.
     * @param currentDir - current directory.
     * @param socket - connection information.
     */
    public NetworkManager(File currentDir, Socket socket) {
        this(currentDir);
        this.socket = socket;
    }

    /**
     * List of files in current directory.
     */
    @Override
    public void listOfFiles() {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            for (File f : currentDir.listFiles()) {
                out.println(f.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save file in current directory.
     * @param file - file to load on server in currentDir;
     * @return - true if file saved.
     */
    @Override
    public boolean receiveFile(File file) {
        boolean result = false;
        try (FileOutputStream out = new FileOutputStream(file)) {
            DataInputStream in = new DataInputStream(this.socket.getInputStream());
            long available = in.readLong();
            int size;
            byte[] buffer = new byte[1024 * 8];
            size = in.read(buffer);
            while (available > 0) {
                out.write(buffer, 0, size);
                available -= size;
                if (available > 0) {
                    size = in.read(buffer);
                }
            }
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Send chosen file.
     * @param file - file to load.
     * @return - true if send was successfully.
     */
    @Override
    public boolean sendFile(File file) {
        boolean result = false;
        try (FileInputStream in = new FileInputStream(file)) {
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            out.writeLong(file.length());
            byte[] buffer = new byte[1024 * 8];
            int size = in.read(buffer);
            while (size >= 0) {
                out.write(buffer, 0, size);
                size = in.read(buffer);
            }
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Show current directory.
     * @return - current directory.
     */
    @Override
    public String getCurrentDir() {
        return this.currentDir.getName();
    }

    /**
     * Set new current directory.
     * @param currentDir - new directory.
     */
    @Override
    public void setCurrentDir(String currentDir) {
        File file = new File(this.getPath(currentDir));

        if (file.isDirectory()) {
            this.currentDir = file;
        }
    }

    /**
     * Set current directory to parent, if possible.
     */
    @Override
    public void setToParentDir() {
        String parent = this.currentDir.getParent();
        if (parent != null) {
            this.currentDir = new File(parent);
        }
    }

    /**
     * Starting server.
     * @param port - port where to start.
     */
    @Override
    public void startServer(int port) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintStream writer = new PrintStream(this.socket.getOutputStream(), true)) {
            System.out.println("Server starts.");
            String request = "";
            while (!STOP.equals(request)) {
                request = reader.readLine();
                System.out.println(request);
                if (STOP.equals(request)) {
                    System.out.println("Server stopped.");
                } else if (CURRENT.equals(request)) {
                    writer.println(this.getCurrentDir());
                } else {
                    if (map.containsKey(request.split(" ")[0])) {
                        map.get(request.split(" ")[0]).apply(request);
                    } else {
                        writer.println("Unknown command");
                    }
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Combines two string to make full path to file..
     * @param name - name of file.
     * @return - new combine string.
     */
    private String getPath(String name) {
        return String.format("%s/%s", currentDir.getAbsolutePath(), name);
    }

    /**
     * Load properties from file.
     */
    private void loadProperties() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("app.properties")) {
            props.load(in);
            this.socket = new ServerSocket(Integer.valueOf(props.getProperty("port"))).accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * List of files and directories.
     * Adapting executing via function.
     * @return true.
     */
    private Function<String, Boolean> list() {
        return msg -> {
            this.listOfFiles();
            return true;
        };
    }

    /**
     * Move to parent dir.
     * Adapting executing via function.
     * @return true.
     */
    private Function<String, Boolean> parentDir() {
        return msg -> {
            this.setToParentDir();
            return true;
        };
    }

    /** Set new current dir.
     * Adapting executing via function.
     * @return true.
     */
    private Function<String, Boolean> setDir() {
        return msg -> {
            this.setCurrentDir(msg.split(" ")[1]);
            return true;
        };
    }

    /**
     * Send file.
     * Adapting executing via function.
     * @return true.
     */
    private Function<String, Boolean> send() {
        return msg -> {
            File file = new File(this.getPath(msg.split(" ")[1]));
            this.sendFile(file);
            return true;
        };
    }

    /**
     * Receive file.
     * Adapting executing via function.
     * @return true.
     */
    private Function<String, Boolean> receive() {
        return msg -> {
            File file = new File(this.getPath(msg.split(" ")[1]));
            this.receiveFile(file);
            return true;
        };
    }

    /**
     * Fill map with possible moves.
     */
    private void init() {
        this.map.put(LIST, this.list());
        this.map.put(PARENT, this.parentDir());
        this.map.put(SET, this.setDir());
        this.map.put(SAVE, this.send());
        this.map.put(LOAD, this.receive());
    }

    /**
     * Main start method.
     * @param args - not in use.
     */
    public static void main(String[] args) {

        try (Socket socket = new ServerSocket(5000).accept()) {
            NetworkManager manager = new NetworkManager(new File("/home/alexey/Java/"));
            manager.startServer(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
