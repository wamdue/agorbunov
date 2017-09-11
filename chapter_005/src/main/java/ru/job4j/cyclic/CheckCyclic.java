package ru.job4j.cyclic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 11.09.17
 * Check cyclic entries in file.
 * @author Wamdue
 * @version 1.0
 */
public class CheckCyclic {
    /**
     * file path
     */
    private String filePath;

    /**
     * Main constructor.
     * @param filePath source file.
     */
    public CheckCyclic(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checking file for cyclic entries.
     * @return list of cyclic entries or empty list, if not found.
     */
    public List<String> checkCyclic() {
        List<String> list = new ArrayList<>();
        try {
            StringBuilder builder = new StringBuilder();
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            for (String line : allLines) {
                if (builder.length() == 0) {
                    builder.append(line);
                } else {
                    if (line.equals(builder.reverse().toString())) {
                        builder.insert(0, line.charAt(line.length() - 1) + " ");
                        list.add(builder.toString());
                        builder.delete(0, builder.length());
                    } else {
                        builder.delete(0, builder.length());
                        builder.append(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Main void.
     * @param args - first parameter is file path.
     */
    public static void main(String[] args) {
        CheckCyclic cc = new CheckCyclic(args[0]);
        for (String s : cc.checkCyclic()) {
            System.out.println(s);
        }
    }
}
