package ru.job4j.fin;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wamdue
 * @version 1.0
 * @since 24.07.2017
 */
public class ArraySort {
    /**
     * Constant to ascending order.
     */
    public static final int ASC = 0;
    /**
     * Constant to descending order.
     */
    public static final int DESC = 1;

    /**
     * Sort array in two ways.
     * @param array - source array.
     * @param method - sort method.
     * @return sorted list.
     */
    public ArrayList<String> sortedArray(String[] array, int method) {
        ArrayList<String> list = new ArrayList<>(arrayToSet(array));
        if (method != ArraySort.ASC) {
            list.sort((o1, o2) -> {
                String[] s1 = o1.split("\\\\");
                String[] s2 = o2.split("\\\\");
                int result;
                if (s1.length == s2.length) {
                    result = o2.compareTo(o1);
                }
                else {
                    result = s2[0].compareTo(s1[0]);
                }
                return result;
            });
        }
        return list;
    }

    /**
     * Method to add absent items in set, and ascending sort.
     * @param array - source array.
     * @return - sorted set.
     */
    private Set<String> arrayToSet(String[] array) {
        Set<String> set =  new TreeSet<>();

        for (String string : array) {
            String[] parts = string.split("\\\\");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < parts.length; j++) {
                if (j > 0) {
                    sb.append("\\");
                }
                sb.append(parts[j]);
                set.add(sb.toString());
            }
        }
        return set;
    }
}
