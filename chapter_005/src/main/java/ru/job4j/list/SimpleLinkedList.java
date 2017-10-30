package ru.job4j.list;

import java.util.Iterator;

/**
 * Created on 21.07.17.
 * Simple realisation of LinkedList.
 * @author Wamdue
 * @version 1.0
 * @param <E> - class to work with.
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * Reference to first element.
     */
    private Node<E> first;
    /**
     * Reference to last element.
     */
    private Node<E> last;
    /**
     * Number of elements.
     */
    private int size = 0;

    /**
     * Adding new element.
     * @param e - item to add.
     */
    @Override
    public void add(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(e, l, null);
        if (l == null) {
            first = newNode;
            last = newNode;
        } else {
            l.next = newNode;
            last = l.next;
        }
        size++;
    }

    /**
     * Getting element by index.
     * @param index index of the item.
     * @return item by the index.
     */
    @Override
    public E get(int index) {
        Node<E> node = first;
        if (index < size / 2) {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.previous;
            }
        }
        return node.item;
    }

    /**
     *
     * @return internal iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    /**
     *
     * @return number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Internal class for make links between elements.
     * @param <E> - generic parameter.
     */
    private class Node<E> {
        /**
         * Item to store.
         */
        private E item;
        /**
         * Link to previous node.
         */
        private Node previous;
        /**
         * Link to next node.
         */
        private Node next;

        /**
         * Main constructor.
         * @param item - item to store.
         * @param previous - reference to prevous element.
         * @param next - reference to next element.
         */
        Node(E item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    /**
     * Internal class to iterate through the links.
     */
    private class LinkedIterator implements Iterator<E> {
        /**
         * Last returned element.
         */
        private Node<E> lastReturned = first;
        /**
         * counter of the returned elements.
         */
        private int position = 0;

        /**
         * Checks, if have elements next to te position.
         * @return true if have, or false is not.
         */
        @Override
        public boolean hasNext() {
            return position < size;
        }

        /**
         * Gets next item of the list.
         * @return element, and move the position to the next.
         */
        @Override
        public E next() {
            Node<E> ret = null;

            if (position < size) {
                ret = lastReturned;
                lastReturned = lastReturned.next;
                position++;
            }
            return ret.item;
        }
    }
}
