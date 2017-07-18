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
    public Set<User> sort (List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Sorting by name length.
     * @param list - source list to sort.
     * @return sorted list by name length.
     */
    public List<User> sortNameLength (List<User> list) {
        ArrayList<User> arrayList = new ArrayList<>(list);
        arrayList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() > o2.getName().length() ? 1 :
                        o1.getName().length() < o2.getName().length() ? -1 : 0;
            }
        });
        return arrayList;
    }

    /**
     * Sorting by name length, then by age.
     * @param list - source list to compare.
     * @return sorted list.
     */
    public List<User> sortByAllFields (List<User> list) {
        ArrayList<User> arrayList = new ArrayList<>(list);
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = 0;

                if (o1.getName().length() > o2.getName().length()) {
                    result = 1;
                } else if (o1.getName().length() == o2.getName().length()) {
                    result = o1.getAge() > o2.getAge() ? 1 : o1.getAge() == o2.getAge() ? 0 : -1;
                } else {
                    result = -1;
                }
                return result;
            }
        };
        arrayList.sort(comparator);
        return arrayList;
    }
}