package ru.job4j.io;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Check input stream for even number.
 * @author Wamdue
 * @version 1.0
 * @since 04.12.2017
 */
public class ByteStream {
    /**
     * Check inputstream for even number.
     * @param in - input stream to analize.
     * @return true if stream have even number.
     */
    public boolean isNumber(InputStream in) {
        boolean result = false;

        Scanner scan = new Scanner(in);
        if (scan.hasNextInt()) {
            int val = scan.nextInt();
            if (val % 2 == 0) {
                result = true;
            }
        }

        return result;
    }
}
