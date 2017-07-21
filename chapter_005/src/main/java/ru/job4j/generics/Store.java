package ru.job4j.generics;

/**
 * Created on 20.07.17
 * Store interface.
 * @author Wamdue
 * @version 1.0
 */
public interface Store<T extends Base> {
    /**
     * Adding new item in array.
     * @param t - item.
     */
    void add(T t);

    /**
     * Update old item.
     * @param old - old item.
     * @param updated - new item.
     */
    void update(T old, T updated);

    /**
     * Delete item.
     * @param t - source item.
     */
    void delete(T t);
}
