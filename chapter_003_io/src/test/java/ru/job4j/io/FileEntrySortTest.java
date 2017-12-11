package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 11.12.17.
 * Testing external sort.
 *
 * @author Wamdue
 * @version 1.0
 */
public class FileEntrySortTest {
    /**
     * Testing.
     * Have file with lines:
     * one
     * two
     * three
     * four
     * five
     * six
     * seven
     * eight
     * nine
     * ten
     *
     * Making external sort, and expect file with lines:
     * one
     * two
     * six
     * ten
     * four
     * five
     * nine
     * three
     * seven
     * eight
     */
    @Test
    public void whenHaveUnsortedFileThenSortIt() {
        File temp = null;
        File target = null;
        try {
            temp = File.createTempFile("temp", "tmp");
            target = File.createTempFile("target", "");
            this.fillTemp(temp);
            FileEntrySort sort = new FileEntrySort();
            sort.sort(temp, target);
            String expected = this.expectedFile();
            try (FileReader reader = new FileReader(target);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                StringBuilder targ = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    targ.append(line);
                }
                assertThat(targ.toString(), is(expected));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fill file with data.
     * @param f - file to fill.
     */
    private void fillTemp(File f) {
        try (FileWriter writer = new FileWriter(f);
             BufferedWriter out = new BufferedWriter(writer)) {
            out.write("one");
            out.newLine();
            out.write("two");
            out.newLine();
            out.write("three");
            out.newLine();
            out.write("four");
            out.newLine();
            out.write("five");
            out.newLine();
            out.write("six");
            out.newLine();
            out.write("seven");
            out.newLine();
            out.write("eight");
            out.newLine();
            out.write("nine");
            out.newLine();
            out.write("ten");
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fill expected String for testing.
     * @return - expecting string.
     */
    private String expectedFile() {
        StringBuilder sb = new StringBuilder();
        sb.append("one");
        sb.append("two");
        sb.append("six");
        sb.append("ten");
        sb.append("four");
        sb.append("five");
        sb.append("nine");
        sb.append("three");
        sb.append("seven");
        sb.append("eight");
        return sb.toString();
    }

}