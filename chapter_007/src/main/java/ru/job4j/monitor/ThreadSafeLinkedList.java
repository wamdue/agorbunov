package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Created on 12.09.17
 * Thread safe realisation of LinkedList.
 * @author Wamdue
 * @version 1.0
 */
@ThreadSafe
public class ThreadSafeLinkedList<E>  {
    private final Object lock = new Object();
    /**
     * Reference to first element.
     */
    @GuardedBy("lock")
    private Node<E> first;
    /**
     * Reference to last element.
     */
    @GuardedBy("lock")
    private Node<E> last;
    /**
     * Number of elements.
     */
    @GuardedBy("lock")
    private int size = 0;

    /**
     * Adding new element.
     * @param e - item to add.
     */
    public void add(E e) {
        synchronized (lock) {
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
    }

    /**
     * Getting element by index.
     * @param index index of the item.
     * @return item by the index.
     */
    public E get(int index) {
        synchronized (lock) {
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
    }

    /**
     *
     * @return internal iterator.
     */
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    /**
     *
     * @return number of elements.
     */
    public int size() {
        synchronized (lock) {
            return size;
        }
    }

    /**
     * Internal class for make links between elements.
     * @param <E> - generic parameter.
     */
    private class Node<E> {
        E item;
        Node previous;
        Node next;

        /**
         *
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
            synchronized (lock) {
                return position < size;
            }
        }

        /**
         *
         * @return element, and move the position to the next.
         */
        @Override
        public E next() {
            Node<E> ret = null;
            synchronized (lock) {
                if (position < size) {
                    ret = lastReturned;
                    lastReturned = lastReturned.next;
                    position++;
                }
                return ret.item;
            }
        }
    }
}
