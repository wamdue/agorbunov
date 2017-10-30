package ru.job4j.list;

/**
 * Created on 24.07.17.
 * Stub class.
 * @author Wamdue
 * @version 1.0
 * @param <T> - class to work with.
 */
public class Node<T> {
    /**
     * Current value.
     */
    private T value;
    /**
     * Link to the next element in the list.
     */
    private Node<T> next;

    /**
     * Main constructor.
     * @param value - current value.
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Get value of node.
     * @return - value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Set value for node.
     * @param value - new value.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Get link to next node.
     * @return - next node.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Set link to next node.
     * @param next - next node.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
