package ru.job4j.fin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sorting array.
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
        ArrayList<Dep> list = new ArrayList<>(arrayToSet(array));
        if (method != ArraySort.ASC) {
            list.sort(this.asc());
        } else {
            list.sort(this.desc());
        }

        ArrayList<String> result = new ArrayList<>();

        for (Dep d : list) {
            result.add(d.name);
        }

        return result;
    }

    /**
     *
     * @return result of comparing by ascending
     */
    private Comparator<Dep> asc() {
        return (o1, o2) -> o2.number - o1.number;
    }

    /**
     *
     * @return result of comparing by descending.
     */
    private Comparator<Dep> desc() {
        return Comparator.comparing(o -> o.name);
    }


    /**
     * Method to add absent items in set, and ascending sort.
     * @param array - source array.
     * @return - sorted set.
     */
    private Set<Dep> arrayToSet(String[] array) {
        Set<Dep> set =  new TreeSet<>();

        for (String string : array) {
            String[] parts = string.split("\\\\");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < parts.length; j++) {
                if (j > 0) {
                    sb.append("\\");
                }
                sb.append(parts[j]);
                String val = sb.toString().replaceAll("[^0-9]", "");
                val = val.length() == 1 ? val + "99" : val.length() == 2 ? val + "9" : val;
                set.add(new Dep(sb.toString(), Integer.valueOf(val)));
            }
        }
        return set;
    }

    /**
     * Internal class to store values, need for sort.
     */
    private class Dep implements Comparable<Dep> {
        /**
         * Line.
         */
        private String name;
        /**
         * Number.
         */
        private int number;

        /**
         * Main constructor.
         * @param name - line.
         * @param number - number.
         */
        Dep(String name, int number) {
            this.name = name;
            this.number = number;
        }

        /**
         * Overriding equals method.
         * @param o - class to compare.
         * @return result of compare.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Dep dep = (Dep) o;

            if (number != dep.number) {
                return false;
            }
            return name.equals(dep.name);
        }

        /**
         * Calculating new hashcode.
         * @return hashcode.
         */
        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + number;
            return result;
        }

        /**
         * Comparing with another object.
         * @param o - object to compare.
         * @return result of compare.
         */
        @Override
        public int compareTo(Dep o) {
            return this.number - o.number;
        }
    }
}