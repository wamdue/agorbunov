package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
     * expected first Vova.
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
        assertThat(sortUser.sort(list).iterator().next(), is(vova));
    }

    /**
     * Have users : age 25 name Sergey.
     *              age 30 name Ivan.
     *              age 40 name Yan.
     * expected first Yan.
     */
    @Test
    public void whenSortingByNameLengthThenSort() {
        List<User> list = new ArrayList<>();
        User sergey = new User(25, "Sergey");
        User ivan = new User(30, "Ivan");
        User yan = new User(40, "Yan");
        list.add(sergey);
        list.add(ivan);
        list.add(yan);
        SortUser sortUser = new SortUser();
        assertThat(sortUser.sortNameLength(list).get(0), is(yan));
    }
    /**
     * Have users : age 25 name Sergey.
     *              age 30 name Ivan.
     *              age 40 name Yan.
     * expected first Yan.
     */
    @Test
    public void whenSortingByAllFieldsThenSort() {
        List<User> list = new ArrayList<>();
        User sergey = new User(25, "Sergey");
        User ivan = new User(25, "Ivan");
        User sergey20 = new User(20, "Sergey");
        User ivan30 = new User(30, "ivan");
        list.add(sergey);
        list.add(ivan);
        list.add(sergey20);
        list.add(ivan30);
        SortUser sortUser = new SortUser();
        assertThat(sortUser.sortByAllFields(list).get(0), is(ivan));
    }
}
