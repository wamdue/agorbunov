package ru.job4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created on 05.12.17.
 * Sort file by string length.
 *
 * @author Wamdue
 * @version 1.0
 */
public class FileEntrySort {
    /**
     * Storage of temp files.
     */
    private final File[] files = new File[2];
    /**
     * Sorting file.
     *
     * @param source   - file to analize.
     * @param distance - save sorted data to this file.
     */
    public void sort(File source, File distance) {
        try {
            this.files[0] = File.createTempFile("first", "01");
            this.files[1] = File.createTempFile("second", "01");
            boolean complete;
            this.fillTempFiles(source);
            complete = !this.mergeTempFiles(distance);
            while (!complete) {
                this.fillTempFiles(distance);
                complete = !this.mergeTempFiles(distance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fill two temp files, from array.
     * @param source - source file, to fill temp files.
     */
    private void fillTempFiles(File source) {
        try (RandomAccessFile raf = new RandomAccessFile(source, "r");
        FileWriter out1 = new FileWriter(files[0]);
        FileWriter out2 = new FileWriter(files[1])) {
            String cur = "";
            String prev = null;
            boolean queue = true;
            while ((cur = raf.readLine()) != null) {
                if (prev == null) {
                    out1.write(String.format("%s\n", cur));
                } else if (prev.length() <= cur.length()) {
                    if (queue) {
                        out1.write(String.format("%s\n", cur));
                    } else {
                        out2.write(String.format("%s\n", cur));
                    }
                } else {
                    queue = !queue;
                    if (queue) {
                        out1.write(String.format("%s\n", cur));
                    } else {
                        out2.write(String.format("%s\n", cur));
                    }
                }
                prev = cur;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Merge temp files to one file, with some kind of sort inside.
     * @param target - file to merger temp files.
     * @return - true if merge was completed, of false if no need of merge.
     */
    private boolean mergeTempFiles(File target) {
        boolean cont = true;
        boolean result = true;
        if (files[0].length() > 0 && files[1].length() > 0) {
            try (RandomAccessFile rafOne = new RandomAccessFile(files[0], "r");
            RandomAccessFile rafTwo = new RandomAccessFile(files[1], "r");
            FileWriter writer = new FileWriter(target)) {
                String lineOne = rafOne.readLine();
                String lineTwo = rafTwo.readLine();
                while (cont) {
                    if (lineOne == null) {
                        writer.write(String.format("%s\n", lineTwo));
                        lineTwo = rafTwo.readLine();
                    } else if (lineTwo == null) {
                        writer.write(String.format("%s\n", lineOne));
                        lineOne = rafOne.readLine();
                    } else {
                        if (lineOne.length() <= lineTwo.length()) {
                            writer.write(String.format("%s\n", lineOne));
                            lineOne = rafOne.readLine();
                        } else {
                            writer.write(String.format("%s\n", lineTwo));
                            lineTwo = rafTwo.readLine();
                        }
                    }

                    if (lineOne == null && lineTwo == null) {
                        cont = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result = false;
        }
        return result;
    }
}