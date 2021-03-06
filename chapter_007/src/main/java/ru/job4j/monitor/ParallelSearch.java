package ru.job4j.monitor;

import net.jcip.annotations.ThreadSafe;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created on 12.09.17.
 * Thread safe parallel file search.
 * @author Wamdue
 * @version 1.0
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * Constant to stop loop.
     */
    public static final  String STOP_WORD = "STOP";
    /**
     * Storage to store full file path.
     */
    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    /**
     * Storage for result files.
     */
    private List<String> result = new ArrayList<>();
    /**
     * Start point of search.
     */
    private String root;
    /**
     * Text to search.
     */
    private String text;
    /**
     * List of extensions to analise.
     */
    private List<String> exts;

    /**
     * Main contructor.
     * @param root - directory to start search.
     * @param text - text to search in files.
     * @param exts - list of extensions to analise.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Starts to thread to filter files.
     * @return list of corrected files.
     */
    public List<String> result() {
        PickFile files = new PickFile();
        Visitor filter = new Visitor();
        Thread fileFilter = new Thread(files);
        Thread textSearch = new Thread(filter);
        fileFilter.start();
        textSearch.start();
        try {
            while (fileFilter.isAlive() && textSearch.isAlive()) {
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Class to filter files by extension.
     */
    private class PickFile implements Runnable {
        /**
         * Array of files in directory.
         */
        private File[] files;

        /**
         * Main constructor.
         */
        PickFile() {
            files = new File(root).listFiles();
        }

        /**
         * Method to start file filter.
         */
        @Override
        public void run() {
            searchFiles(files);
            queue.offer(ParallelSearch.STOP_WORD);
        }

        /**
         * Method to fill storage of files paths.
         * @param listFiles - array of files to check.
         */
        private void searchFiles(File[] listFiles) {
            for (File f : listFiles) {
                if (f.isDirectory()) {
                    searchFiles(f.listFiles());
                } else {
                    String ext = f.getName().contains(".") ? f.getName().substring(f.getName().indexOf('.') + 1) : " ";
                    if (exts.contains(ext)) {
                            queue.offer(f.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * Class to analyze files for source text.
     */
    private class Visitor implements Runnable {
        /**
         * Checks files for searched text.
         */
        @Override
        public void run() {
            List<String> lines;
            Path p;
            String file;
            while (true) {
                try {
                    file = queue.poll();
                    if (file == null) {
                        Thread.sleep(50);
                        continue;
                    }
                    if (file.equals(ParallelSearch.STOP_WORD)) {
                        System.out.println(result.size());
                        break;
                    }


                    p = Paths.get(file);
                    lines = Files.readAllLines(p, Charset.defaultCharset());
                    for (String line : lines) {
                        if (line.contains(text)) {
                            result.add(p.toFile().getAbsolutePath());
                            break;
                        }
                    }
                } catch (InterruptedException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Main method to demonstrate program.
     * @param args - array of parameters.
     */
    public static void main(String[] args) {
        List<String> exts = new ArrayList<>();
        exts.add("java");
        String root = "/home/alexey/IdeaProjects/";
        String text = "main";

        ParallelSearch search = new ParallelSearch(root, text, exts);
        for (String s : search.result()) {
            System.out.println(s);
        }
    }
}
