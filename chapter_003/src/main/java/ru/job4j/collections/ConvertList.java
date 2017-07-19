package ru.job4j.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class ConvertList {
    /**
     * Array converter to list.
     * @param array - array to convert in list.
     * @return array as list, or empty if array.length == 0
     */
    public List<Integer> toList (int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] arr : array) {
            for (int value : arr) {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * Converter list to array.
     * @param list - source list.
     * @param rows - number of rows in array.
     * @return converted array.
     */
    public int[][] toArray (List<Integer> list, int rows) {
        int width = list.size() % rows == 0 ? list.size() / rows : (list.size() / rows) + 1;
        int[][] array = new int[rows][width];
        int x = 0;
        int y = 0;
        for (int value : list) {
            if (x == width) {
                y++;
                x = 0;
            }
            array[y][x++] = value;
        }

        if (x != width) {
            do {
                array[y][x++] = 0;
            } while (x != width);
        }
        return array;
    }

    /**
     * Convert list of arrays to list.
     * @param list - list of arrays to convert.
     * @return list of values of list of arrays.
     */
    public List<Integer> convert (List<int[]> list) {
        List<Integer> arrayList = new ArrayList<>();
        for (int[] values : list) {
            for (int value : values) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }
}