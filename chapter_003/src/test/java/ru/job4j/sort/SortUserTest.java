package ru.job4j.sort;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.*;

/**
 * Created on 18.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class SortUserTest {
    /**
     * Have users : age 10 name Vasya.
     *              age 5 name Vova.
     *              age 30 name Dima.
     */
    @Test
    public void whenHaveUnsortedListThenSort() {
        List<User> list = new ArrayList<>();
        User vasya = new User(10, "Vasya");
        User vova = new User(5, "Vova");
        User dima = new User(30, "Dime");
        list.add(vasya);
        list.add(vova);
        list.add(dima);
        SortUser sortUser = new SortUser();
        assertThat(sortUser.sort(list).iterator().next(), is (vova));

    }
}
