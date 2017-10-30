package ru.job4j.wait;


/**
 * Created on 13.09.17.
 * Blocking queue realization.
 * @author Wamdue
 * @version 1.0
 * @param <E> - class to store.
 */
public class SimpleBlockingQueue<E> {

    /**
     * Link to first node.
     */
    private Node<E> first = null;
    /**
     * Link to last node.
     */
    private Node<E> last = null;
    /**
     * Object monitor.
     */
    private final Object lockTake = new Object();
    /**
     * Size of queue.
     */
    private int size = 0;

    /**
     * insert item in linked list.
     * @param item - source item.
     */
    public void put(E item) {
        synchronized (lockTake) {
            Node<E> newNode = new Node<>(item);
            if (first == null) {
                first = newNode;
                last = newNode;
            } else {
                last.next = newNode;
                last = last.next;
            }
            lockTake.notify();
            size++;
        }
    }

    /**
     * take item from list.
     * if list is empty, waits until item will appear.
     * @return - value of the node.
     */
    public E take() {
        E item = null;
        synchronized (lockTake) {
            while (size == 0) {
                try {
                    lockTake.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            item = first.item;
            first = first.next;
            size--;
        }
        return item;
    }

    /**
     * Internal class to hold item, and ref.
     * @param <E> - class to store.
     */
    private class Node<E> {
        /**
         * Link to next element.
         */
        private Node<E> next;
        /**
         * Link to stored class.
         */
        private E item;

        /**
         * Main constructor.
         * @param item - class to store.
         */
        Node(E item) {
            this.item = item;
        }
    }
}
