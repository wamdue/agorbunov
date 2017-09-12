package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Created on 12.09.17
 * Thread safe realization of ArrayList.
 * @author Wamdue
 * @version 1.0
 */
@ThreadSafe
public class ThreadSafeList<E>  {

    private final Object lock = new Object();
    /**
     * Main array.
     */
    @GuardedBy("lock")
    private Object[] objects = new Object[10];
    /**
     * Current position(size).
     */
    private int position = 0;

    /**
     * Add new item to array.
     * If maximum size reached, then must extend by * 2.
     * @param e - item to add.
     */
    public void add(E e) {
        synchronized (lock) {
            if (objects.length == position) {
                Object[] temp = new Object[position * 2];
                System.arraycopy(objects, 0, temp, 0, position - 1);
                objects = temp;
            }
            objects[position++] = e;
        }
    }

    /**
     * Get item by index.
     * @param index index of the item.
     * @return E.
     */
    public E get(int index) {

        synchronized (lock) {
            return (E) objects[index];
        }
    }

    /**
     * @return internal realisation of the iterator.
     */
    public Iterator<E> iterator() {
        synchronized (lock) {
            Object[] temp = new Object[position];
            System.arraycopy(objects, 0, temp, 0, position - 1);
            return new SimpleIterator<E>(temp);
        }
    }
    public int size() {
        return position;
    }

    /**
     * Internal class for iterator.
     * @param <V>
     */
    private class SimpleIterator<V> implements Iterator<V>{
        /**
         * Array to interact.
         */
        private Object[] items;
        /**
         * Current position.
         */
        private int position = 0;
        /**
         * Main constructor.
         * @param items - source array.
         */
        public SimpleIterator(Object[] items) {
            this.items = items;
        }
        /**
         *
         * @return true if has more elements.
         */
        @Override
        public boolean hasNext() {
            return position < items.length;
        }
        /**
         *
         * @return elements and move to the next position.
         */
        @Override
        public V next() {
            return (V) items[position++];
        }
    }
}
