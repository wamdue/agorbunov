package ru.job4j.wait;


/**
 * Created on 13.09.17
 * Blocking queue realization.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleBlockingQueue<E> {

    private Node<E> first = null;
    private Node<E> last = null;
    private final Object lockTake = new Object();
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
     * @return
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
     * @param <E>
     */
    private class Node<E> {
        Node<E> next;
        E item;

        Node(E item) {
            this.item = item;
        }
    }
}
