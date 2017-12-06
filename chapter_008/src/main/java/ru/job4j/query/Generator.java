package ru.job4j.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 02.10.17.
 * Query generator.
 * @author Wamdue
 * @version 1.0
 */
public class Generator {
    /**
     * Dispatcher.
     */
    private Map<Action, String> actions = new HashMap<>();

    /**
     * Init map actions with possible actions, end response on them.
     */
    private void init() {
        actions.put(Action.CONTAIN, "LIKE");
        actions.put(Action.EQUAL, "=");
        actions.put(Action.NOT_EQUAL, "!=");
        actions.put(Action.LESS, "<");
        actions.put(Action.LESS_OR_EQUAL, "<=");
        actions.put(Action.MORE, ">");
        actions.put(Action.MORE_OR_EQUAL, ">=");
        actions.put(Action.NULL, "IS NULL");
        actions.put(Action.NOT_NULL, "IS NOT NULL");
        actions.put(Action.IN, "IN");
    }

    /**
     * Default constructor.
     */
    public Generator() {
        this.init();
    }

    /**
     * Generator method.
     * @param begin - beginning of the query.
     * @param lines - lines of the filter to convert to query.
     * @return target query to fileter.
     */
    public String generateSelect(String begin, List<Line> lines) {
        StringBuilder sb = new StringBuilder();
        sb.append(begin);
        for (Line l : lines) {
            sb.append(getLine(l));
        }
        return sb.toString();
    }

    /**
     *
     * @param line - parameter line to convert.
     * @return - target string to append to query.
     */
    private String getLine(Line line) {
        StringBuilder sb = new StringBuilder();
        if (line.getBehave() != Behave.NULL) {
            sb.append(" ").append(line.getBehave());
        }
        sb.append(" ").append(line.getField());
        sb.append(" ").append(actions.get(line.getAction()));
        if (line.getAction() != Action.NULL || line.getAction() != Action.NOT_NULL) {
            if (line.getAction() == Action.CONTAIN) {
                sb.append(" '%").append(line.getValue()).append("%'");
            } else if (line.getAction() == Action.IN) {
                sb.append(" (").append(line.getValue()).append(")");
            } else {
                sb.append(" ").append(line.getValue());
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
