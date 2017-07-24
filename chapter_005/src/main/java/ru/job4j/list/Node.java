package ru.job4j.list;

/**
 * Created on 24.07.17
 * Stub class.
 * @author Wamdue
 * @version 1.0
 */
public class Node<T> {
    /**
     * current value.
     */
    T value;
    /**
     * Link to the next element in the list.
     */
    Node<T> next;

    /**
     * Main constructor.
     * @param value - current value.
     */
    public Node(T value) {
        this.value = value;
    }
}
