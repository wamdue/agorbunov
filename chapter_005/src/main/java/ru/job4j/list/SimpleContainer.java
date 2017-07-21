package ru.job4j.list;

/**
 * Created on 21.07.17
 * Interface to explain main methods of the SimpleList
 * @author Wamdue
 * @version 1.0
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Adding item to collection.
     * @param e - item to add.
     */
    void add(E e);

    /**
     * Get item by index.
     * @param index index of the item.
     * @return E.
     */
    E get(int index);
}
