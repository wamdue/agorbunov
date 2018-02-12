package ru.job4j.add;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created on 12.02.18.
 * Emulating cmd.
 * @author Wamdue
 * @version 1.0
 */
public class Shell {
    /**
     * Current dir.
     */
    private File currentDir;
    /**
     * Regular expression to analyze path.
     */
    private static final String TEMPLATE = "((/+)?(\\w+))|(/+\\w+/+?)";

    /**
     * Main constructor.
     */
    public Shell() {
        this.currentDir = File.listRoots()[0];
    }

    /**
     * Changing path.
     * @param path - path to change.
     * @return - this.
     */
    public Shell cd(final String path) {
        List<File> files = Arrays.asList(Objects.requireNonNull(this.currentDir.listFiles()));
        if (files.size() > 0) {
            if (path.equals("..")) {
                String parent = this.currentDir.getParent();
                if (parent != null) {
                    this.currentDir = new File(parent);
                }
            } else if (path.matches(TEMPLATE)) {
                String file = path.replaceAll("/", "");
                this.currentDir = (File) files.stream().filter(x -> x.getName().equals(file)).toArray()[0];
            }
        }
        return this;
    }

    /**
     * Show path.
     * @return - current path.
     */
    public String path() {
        return this.currentDir.getAbsolutePath();
    }
}
