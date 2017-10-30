package ru.job4j.set;

import java.util.Iterator;

/**
 * Created on 27.07.17.
 * Simple set based on linked list.
 * @author Wamdue
 * @version 1.0
 * @param <E> - class to store.
 */
public class LinkedSet<E> implements Iterable<E> {
    /**
     * Link to the first element.
     */
    private Node<E> first;
    /**
     * Link to the last element.
     */
    private Node<E> last;

    /**
     * Method to add item to linked list.
     * @param e - main value.
     */
    public void add(E e) {
        Node<E> newNode = new Node<E>(e, null);
        Node<E> temp = last;
        if (temp == null) {
            first = newNode;
            last = newNode;
        } else {
            Node<E> current = first;
            boolean match = false;
            while (current != null) {
                if (current.item.equals(e)) {
                    match = true;
                    break;
                } else {
                    current = current.next;
                }
            }
            if (!match) {
                last.next = newNode;
                last = last.next;
            }
        }
    }

    /**
     * @return iterator to look for the elements.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    /**
     * private class for Elements to link.
     * @param <E> - base class.
     */
    private class Node<E> {
        /**
         * Item to store.
         */
        private E item;
        /**
         * Link to next node.
         */
        private Node<E> next;

        /**
         * Main constructor.
         * @param item - value.
         * @param next - link to next node.
         */
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
