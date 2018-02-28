package ru.job4j.add;

/**
 * Created on 12.02.18.
 * Emulating cmd.
 *
 * @author Wamdue
 * @version 1.0
 */
public class Shell {
    /**
     * Current dir.
     */
    private Entry currentDir;
    /**
     * Regular expression to analyze path.
     */
    private static final String TEMPLATE = "((/+)?(\\w+))|(/+\\w+/+?)";

    /**
     * Main constructor.
     * @param e - Main entry.
     */
    public Shell(Entry e) {
        this.currentDir = e;
    }

    /**
     * Changing path.
     *
     * @param path - path to change.
     * @return - this.
     */
    public Shell cd(final String path) {

        if (path.equals("..")) {
            if (this.currentDir.getRoot() != null) {
                this.currentDir = this.currentDir.getRoot();
            }
        } else if (path.matches(TEMPLATE)) {
            String file = path.replaceAll("/", "");
            if (this.currentDir.getEntries().size() > 0) {
                this.currentDir = this.currentDir.getEntries().stream().filter(x -> x.getName().replace("/", "").equals(file)).distinct().findFirst().get();
            }
        }
        return this;
    }

    /**
     * Show path.
     *
     * @return - current path.
     */
    public String path() {
        return this.currentDir.toString();
    }
}
