package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 05.12.17.
 * Delete abuse words from stream.
 * @author Wamdue
 * @version 1.0
 */
public class StreamCensor {

    /**
     * Censoring input stream, delete abuse words from it.
     * @param in - input stream, where need to delete extra words.
     * @param out - output stream.
     * @param abuse - array of words, needed to delete from intput stream.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        Scanner scanner = new Scanner(in);
        List<String> list = Arrays.asList(abuse);
        String word;
        try {
            while (scanner.hasNext()) {
                word = scanner.next();
                if (!list.contains(word)) {
                    out.write(word.getBytes());
                    out.write(" ".getBytes());
                }
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
