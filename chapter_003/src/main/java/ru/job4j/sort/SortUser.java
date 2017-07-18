package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created on 18.07.17
 * Class for sorting user lists.
 * @author Wamdue
 * @version 1.0
 */
public class SortUser {
    /**
     *
     * @param list - source list to convert in sorted set.
     * @return - sorted set.
     */
    public Set<User> sort (List<User> list) {
        return new TreeSet<>(list);
    }
}
