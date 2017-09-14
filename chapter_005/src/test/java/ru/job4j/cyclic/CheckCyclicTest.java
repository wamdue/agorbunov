package ru.job4j.cyclic;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 14.09.17
 * Test class for cyclic file.
 * @author Wamdue
 * @version 1.0
 */
public class CheckCyclicTest {
    /**
     * Have file with lines:
     * 1 2
     * 2 1
     * З 4
     * 5 6
     * 6 5
     * expect return list with entries:
     * 1 2 1
     * 5 6 5
     */
    @Test
    public void whenHaveFileWithCyclicThenReturnSomeEntries() {
        FileWriter writer = null;
        try {
            File file = File.createTempFile("testFile", "");
            writer = new FileWriter(file);
            writer.write("1 2\n");
            writer.write("2 1\n");
            writer.write("3 4\n");
            writer.write("5 6\n");
            writer.write("6 5");
            writer.flush();

            CheckCyclic cyclic = new CheckCyclic(file.getAbsolutePath());
            List<String> expect = new ArrayList<>();
            expect.add("1 2 1");
            expect.add("5 6 5");
            assertThat(cyclic.checkCyclic(), is(expect));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Have file with lines:
     * 1 2
     * З 4
     * 5 6
     * 7 8
     * 9 10
     * expect return empty list.
     */
    @Test
    public void whenHaveFileWithoutCyclicThenReturnSomeEntries() {
        FileWriter writer = null;
        try {
            File file = File.createTempFile("testFile", "");
            writer = new FileWriter(file);
            writer.write("1 2\n");
            writer.write("3 4\n");
            writer.write("5 6\n");
            writer.write("7 8\n");
            writer.write("9 10");
            writer.flush();

            CheckCyclic cyclic = new CheckCyclic(file.getAbsolutePath());
            List<String> expect = new ArrayList<>();
            assertThat(cyclic.checkCyclic(), is(expect));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}