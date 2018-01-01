package ru.job4j.socket;

/**
 * @author Wamdue
 * Extra class to manage requests.
 * @version 1.0
 * @since 01.01.2018
 */
public class Answer {
    /**
     * Command in line.
     */
    private final String command;
    /**
     * Dir name in command line.
     */
    private final String dir;
    /**
     * Full command line.
     */
    private final String fullLine;

    /**
     * Main constructor.
     * @param line - request line.
     */
    public Answer(String line) {

        this.fullLine = line;
        if (line.split(" ").length > 0) {
            this.command = line.split(" ")[0];
            this.dir = line.substring(line.indexOf(" ") + 1, line.length());
        } else {
            this.dir = "";
            this.command = "";
        }
    }

    /**
     * Get dir name.
     * @return - dir name.
     */
    public String getDir() {
        return dir;
    }

    /**
     * Get command name.
     * @return command name.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Get full request line.
     * @return - full request.
     */
    public String getFullLine() {
        return fullLine;
    }
}
