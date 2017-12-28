package ru.job4j.tdd;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 28.12.17.
 * Simple generator realisation.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleGenerator implements Template {
    /**
     * Pattern for fill key in string.
     */
    private static final String TEMP = "${%s}";
    /**
     * Pattern for get key in string.
     */
    private static final String PATT = "\\$\\{\\w*\\}";
    /**
     * Pattern for remove extra symbols from key.
     */
    private static final String OUT = "[${}]";

    /**
     * Generating string from template.
     * @param template - template string.
     * @param data - array of data to insert.
     * @return - result string.
     */
    @Override
    public String generate(String template, Map<String, String> data) {

        if (!template.contains("${") || data.size() == 0) {
            throw new UnsupportedOperationException();
        }

        Pattern pattern = Pattern.compile(PATT);
        Set<String> phrases = new HashSet<>();
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            phrases.add(matcher.group());
        }

        for (String s : phrases) {
            if (!data.containsKey(s.replaceAll(OUT, ""))) {
                throw new UnsupportedOperationException(String.format("No '%s' key in map", s));
            }
        }

        for (Map.Entry<String, String> map : data.entrySet()) {
            if (!template.contains(String.format(TEMP, map.getKey()))) {
                throw new UnsupportedOperationException(String.format("No '%s' key in template String", map.getKey()));
            }
        }

        String line = template;

        for (String s : phrases) {
            line = line.replace(s, data.get(s.replaceAll(OUT, "")));
        }

        return line;
    }
}
