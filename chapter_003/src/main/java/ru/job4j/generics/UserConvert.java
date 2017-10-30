package ru.job4j.generics;

import java.util.HashMap;
import java.util.List;

/**
 * Created on 17.07.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class UserConvert {
    /**
     * Method to convert list to map.
     * @param list - source list to convert in map.
     * @return map.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
