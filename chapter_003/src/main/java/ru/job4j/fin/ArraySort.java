package ru.job4j.fin;

import java.util.*;

/**
 * @author Wamdue
 * @version 1.0
 * @since 24.07.2017
 */
public class ArraySort {
    private String[] strings = new String[]{"K1\\SK1",
            "K1\\SK2",
            "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2",
            "K2",
            "K2\\SK1\\SSK1",
            "K2\\SK1\\SSK2"};
    public ArrayList<String> sortedArray(String[] array, int method) {
        Set<String> set =  new TreeSet<>();

        for (int i = 0; i <strings.length ; i++) {
            String[] parts = strings[i].split("\\\\");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < parts.length; j++) {
                if ( j > 0) {
                    sb.append("\\\\");
                }
                sb.append(parts[j]);
                set.add(sb.toString());
            }
        }
        ArrayList<String> list = new ArrayList<>(set);
        if (method != 0) {
            Collections.sort(list, Collections.reverseOrder());
        }
        return list;
    }

    public static void main(String[] args) {
        ArraySort array = new ArraySort();
        System.out.println(array.sortedArray(array.strings, 1));
    }
}
