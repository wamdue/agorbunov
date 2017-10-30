package ru.job4j.list;

/**
 * Created on 24.07.17.
 * Simple stack realization.
 * @author Wamdue
 * @version 1.0
 * @param <E> - class to work with.
 */
public class SimpleStack<E> extends SimpleLinkedList<E> {
    /**
     * Link to the last element.
     */
    private Node<E> first;
    /**
     * Link to the first element.
     */
    private Node<E> last;
    /**
     * Current size.
     */
    private int size = 0;

    /**
     * Shows first element.
     * @return first element.
     */
    public E peek() {
        return this.first.item;
    }

    /**
     * Instert new first element.
     * @param e - new element to add in the head of the list.
     */
    public void push(E e) {
        Node<E> newNode = new Node<E>(e, null, this.first);
        this.first = newNode;
        this.size++;
    }

    /**
     * Takes first element from list, and remove it.
     * @return first element in the list.
     */
    public E pop() {
        Node<E> temp = this.first;
        if (this.size > 0) {
            this.first = this.first.next;
            this.size--;
        }
        return temp.item;
    }
    /**
     * Private class to store elements in list.
     *
     * @param <E> - class to store.
     */
    private class Node<E> {
        /**
         * main element.
         */
        private E item;
        /**
         * Link to previous item.
         */
        private Node<E> previous;
        /**
         * link ot next item.
         */
        private Node next;

        /**
         * Main constructor.
         * @param item     - main item.
         * @param previous - link to previous item.
         * @param next     - link to next item.
         */
        Node(E item, Node<E> previous, Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }
}