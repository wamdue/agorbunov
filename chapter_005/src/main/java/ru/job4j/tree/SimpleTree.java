package ru.job4j.tree;

/**
 * Created on 10.08.17
 *
 * @author Wamdue
 * @version 1.0
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return result.
     */
    boolean add(E parent, E child);
}