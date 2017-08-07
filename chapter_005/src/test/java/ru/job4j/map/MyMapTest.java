package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 04.08.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class MyMapTest {
    /**
     * Attempting insert 20 items.
     * trying to resolve item with key "8".
     */
    @Test
    public void whenHaveValuesThenInsertToFreeCells() {
        MyMap<String, Integer> map = new MyMap<>();
        for (int i = 0; i < 20; i++) {
            map.insert(String.valueOf(i), i);
        }
        int expect = 8;
        assertThat(map.get("8"), is(expect));
    }

    /**
     * Making key iterator.
     */
    @Test
    public void whenWantToDeleteItemFromMapTheDoIt() {
        MyMap<String, Integer> map = new MyMap<>();
        int expect = 0;
        for (int i = 0; i < 20; i++) {
           if (map.insert(String.valueOf(i), i)) {
               expect++;
           }
        }
        map.delete("8");
        assertThat(map.size(), is(expect - 1));

    }

    /**
     * Making value iterator.
     */
    @Test
    public void whenUserIteratorByKeyThenCanIterate() {
        MyMap<String, Integer> map = new MyMap<>();
        for (int i = 0; i < 20; i++) {
            map.insert(String.valueOf(i), i);
        }
        Iterator<String> iterator = map.keyIterator();
        String expect = "1";
        iterator.next();
        assertThat(iterator.next(), is(expect));
    }
    @Test
    public void whenUserIteratorByValueThenCanIterate() {
        MyMap<String, Integer> map = new MyMap<>();
        for (int i = 0; i < 20; i++) {
            map.insert(String.valueOf(i), i);
        }
        Iterator<Integer> iterator = map.valueIterator();
        int expect = 1;
        iterator.next();
        assertThat(iterator.next(), is(expect));
    }

}