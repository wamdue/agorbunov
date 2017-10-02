package ru.job4j.query;

import java.util.List;

/**
 * Created on 02.10.17
 * Query generator
 * @author Wamdue
 * @version 1.0
 */
public class Generator {
    /**
     * generator method
     * @param begin - beginning of the query
     * @param lines - lines of the filter to convert to query
     * @return target query to fileter
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
     * @return - target string to append to query
     */
    private String getLine(Line line) {
        StringBuilder sb = new StringBuilder();
        if (line.getBehave() != Behave.NULL) {
            sb.append(" ").append(line.getBehave());
        }
        sb.append(" ").append(line.getField());
        sb.append(" ").append(getAction(line.getAction()));
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

    /**
     * Convert method
     * @param action enum action
     * @return readable action, to insert into the query
     */
    private String getAction(Action action) {
        String temp;
        if (action == Action.CONTAIN) {
            temp = "LIKE";
        } else if (action == Action.EQUAL) {
            temp = "=";
        } else if (action == Action.NOT_EQUAL) {
            temp = "!=";
        }  else if (action == Action.LESS) {
            temp = "<";
        } else if (action == Action.LESS_OR_EQUAL) {
            temp = "<=";
        } else if (action == Action.MORE) {
            temp = ">";
        } else if (action == Action.MORE_OR_EQUAL) {
            temp = ">=";
        } else if (action == Action.NULL) {
            temp = "IS NULL";
        } else if (action == Action.NOT_NULL) {
            temp = "IS NOT NULL";
        } else {
            temp = action.toString();
        }
        return temp;
    }
}
