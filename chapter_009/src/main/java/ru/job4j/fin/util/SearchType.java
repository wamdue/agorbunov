package ru.job4j.fin.util;

/**Container for search pattern
 * @author Wamdue
 * @version 1.0
 * @since 26.11.2017
 */
public class SearchType {
    /**
     * Type of search.
     */
    private final String type;
    /**
     * Search string.
     */
    private final String val;

    /**
     * Main constructor.
     * @param type - type of search.
     * @param val - search string.
     */
    public SearchType(String type, String val) {
        this.type = type;
        this.val = val;
    }

    /**
     * Get search type.
     * @return - search type.
     */
    public String getType() {
        return type;
    }

    /**
     * Search string.
     * @return - ger search string.
     */
    public String getVal() {
        return val;
    }
}
