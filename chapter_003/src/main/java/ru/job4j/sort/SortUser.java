package ru.job4j.sort;

import java.util.*;

/**
 * Created on 18.07.17
 * Class for sorting user lists.
 * @author Wamdue
 * @version 1.0
 */
public class SortUser {
    /**
     * Sorting by age.
     * @param list - source list to convert in sorted set.
     * @return - sorted set.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Sorting by name length.
     * @param list - source list to sort.
     * @return sorted list by name length.
     */
    public List<User> sortNameLength(List<User> list) {
        ArrayList<User> arrayList = new ArrayList<>(list);
        arrayList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() -  o2.getName().length();
            }
        });
        return arrayList;
    }

    /**
     * Sorting by name length, then by age.
     * @param list - source list to compare.
     * @return sorted list.
     */
    public List<User> sortByAllFields(List<User> list) {
        ArrayList<User> arrayList = new ArrayList<>(list);
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().length() - o2.getName().length();
                result = result == 0 ? o1.getAge() - o2.getAge() : result;
                return result;
            }
        };
        arrayList.sort(comparator);
        return arrayList;
    }
}