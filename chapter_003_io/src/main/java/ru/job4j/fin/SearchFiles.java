package ru.job4j.fin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created on 15.12.17.
 * Search file by mask.
 * @author Wamdue
 * @version 1.0
 */
public class SearchFiles {
    /**
     * Directory where start search.
     */
    private final String sourceDir;
    /**
     * Possible file name.
     */
    private final String searchString;
    /**
     * Filter type.
     * -f - full equality.
     * -m - using * in search
     * -r - user regular expressions.
     */
    private final String filter;
    /**
     * Output file name.
     */
    private final String outputFile;
    /**
     * List of founded files.
     */
    private final List<String> result = new ArrayList<>();
    /**
     * Reg exp compilation.
     */
    private Pattern pattern;

    /**
     * Main constructor.
     * @param sourceDir - Directory where start search.
     * @param searchString - Possible file name.
     * @param filter - filter type.
     * @param outputFile - output file name.
     */
    public SearchFiles(String sourceDir, String searchString, String filter, String outputFile) {
        this.sourceDir = sourceDir;
        this.searchString = searchString;
        this.filter = filter;
        this.outputFile = outputFile;
    }

    /**
     * Recursive visit all files.
     * @param dir - where to start searching.
     */
    private void searchFiles(String dir) {
        File file = new File(dir);
        if (file.listFiles() != null) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    searchFiles(f.getAbsolutePath());
                } else {
                    if (this.match(f.getName())) {
                        this.result.add(f.getAbsolutePath());
                    }
                }
            }
        }
        this.saveResult();
    }

    /**
     * Save result in file.
     */
    private void saveResult() {
        if (result.size() > 0) {
            try (PrintStream stream = new PrintStream(new FileOutputStream(new File("outputFile")))) {
                for (String s : result) {
                    stream.println(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Compare search string with filename, in case of search type.
     * @param fileName - file name to compare.
     * @return - true if matches.
     */
    private boolean match(String fileName) {
        boolean result = false;
        if ("-f".equals(this.filter)) {
            if (fileName.equals(this.searchString)) {
                result = true;
            }
        } else if (pattern != null) {
            if (pattern.matcher(fileName).matches()) {
                result = true;
            }
        } else if ("-m".equals(this.filter)) {
            int index = this.searchString.indexOf("*");
            if (index == 0) {
                result = fileName.endsWith(searchString.replace("*", ""));
            } else if (index == this.searchString.length() - 1) {
                result = fileName.startsWith(this.searchString.replace("*", ""));
            } else {
                result = fileName.contains(this.searchString.replace("*", " "));
            }
        }
        return result;
    }

    /**
     * Start searching.
     */
    public void start() {
        if ("-r".equals(this.filter)) {
            this.pattern = Pattern.compile(this.searchString);
        }
        this.searchFiles(this.sourceDir);

    }

    /**
     * Main start method.
     * @param args -
     * example string -d <dir start> -n <search string> <search type -m - max, -f - full compare, -r - regexp> -o <output file name>
     */
    public static void main(String[] args) {
        String error = "not enough parameters, example string: -d <search path> -n <file mask, "
                + "file name or regular expression> <tipe of mask -m mask, -f full compare, -r - regular expression> "
                + "-o <result file name>";
        if (args.length < 7 || args.length > 7) {
            System.out.println(error);
        } else if (args[0].equals("-d") && args[2].equals("-n") && args[5].equals("-o")
                && (args[4].equals("-m") || args[4].equals("-f") || args[4].equals("-r"))) {
            String searchDir = args[1];
            String searchString = args[3];
            String searchType = args[4];
            String outputFile = args[6];
            SearchFiles files = new SearchFiles(searchDir, searchString, searchType, outputFile);
            files.start();
        } else {
            System.out.println(error);
        }
    }

}
