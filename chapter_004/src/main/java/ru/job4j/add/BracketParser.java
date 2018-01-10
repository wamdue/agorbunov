package ru.job4j.add;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created on 10.01.18.
 * Parsing brackets in source string.
 * @author Wamdue
 * @version 1.0
 */
public class BracketParser {
    /**
     * Bracket map, open/close.
     */
    private final Map<Character, Character> map = new HashMap<>();

    /**
     * Main constructor.
     */
    public BracketParser() {
        this.init();
    }

    /**
     * Load default bracket pairs.
     */
    private void init() {
        this.map.put('<', '>');
        this.map.put('(', ')');
        this.map.put('{', '}');
        this.map.put('[', ']');
    }

    /**
     * Add new pair to map.
     * @param open - open bracket.
     * @param close - close bracket.
     */
    public void addPair(char open, char close) {
        this.map.put(open, close);
    }

    /**
     * Remove pair from check.
     * @param open - open bracket.
     */
    public void removePair(char open) {
        this.map.remove(open);
    }

    /**
     * Get of brackets in string.
     * @param line - source string.
     * @return list, or empty list if have not paired brackets.
     */
    private List<BracketPair> parse(String line) {
        Deque<BracketPair> close = new LinkedList<>();
        char[] chars = line.toCharArray();
        List<BracketPair> pairs = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (this.map.containsKey(c)) {
                BracketPair bracket = new BracketPair(c, this.map.get(c));
                bracket.setOpenPos(i);
                pairs.add(bracket);

                close.push(bracket);
                continue;
            } else if (close.size() > 0 && c == close.peek().getClose()) {
                BracketPair pair = close.pop();
                pair.setClosePos(i);
            } else if (this.map.containsValue(c) && close.peek().getClose() != c) {
                break;
            }
        }
        if (pairs.size() > 0 && close.size() > 0) {
            pairs.clear();
        }
        return pairs;
    }

    /**
     * Shows result string to user.
     * @param line - source line.
     * @return - result string.
     */
    public String check(String line) {
        List<BracketPair> pairs = this.parse(line);
        String resultString;
        if (pairs.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (BracketPair b : pairs) {
                sb.append(b).append("\n");
            }
            resultString = sb.toString();
        } else {
            resultString = "No bracket in string, or have not paired brackets.";
        }

        return resultString;
    }

    /**
     * Get map of pairs.
     * @return unmodifiable map.
     */
    public Map<Character, Character> getMap() {
        return Collections.unmodifiableMap(map);
    }

}
