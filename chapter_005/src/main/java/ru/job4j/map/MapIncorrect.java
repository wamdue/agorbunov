package ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 28.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class MapIncorrect {
    public static void main(String[] args) {
        User vasya = new User("Vasya", new GregorianCalendar(2000, 5, 20), 0);
        User vasya2 = new User("Vasya", new GregorianCalendar(2000, 5, 20), 0);
        Map<User, String> map = new HashMap<>();
        map.put(vasya, vasya.getName());
        map.put(vasya2, vasya2.getName());
        System.out.println(map);
    }
}
