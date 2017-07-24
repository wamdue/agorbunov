package ru.job4j.set;

import java.util.Iterator;

/**
 * Created on 24.07.17
 * Simple set realization.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * Main array of objects.
     */
    private Object[] objects;
    /**
     * Current number of objects in array.
     */
    private int size = 0;

    /**
     * Main constructor.
     */
    public SimpleSet() {
        objects = new Object[10];
    }

    /**
     *
     * @param e - item to add in array.
     */
    public void add(E e) {
        boolean match = false;
        for (int i = 0; i < size; i++) {
            if (((E)objects[i]).equals(e)) {
                match = true;
                objects[i] = e;
                break;
            }
        }
        if (!match) {
            if (objects.length == size) {
                Object[] temp = new Object[size * 2];
                System.arraycopy(objects, 0, temp, 0, size);
                objects = temp;
            }
            objects[size++] = e;
        }
    }

    /**
     *
     * @return iterator for set.
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int position = 0;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                return (E) objects[position++];
            }
        };
    }

    /**
     *
     * @return - current number of elements in array.
     */
    public int size() {
        return size;
    }
}
