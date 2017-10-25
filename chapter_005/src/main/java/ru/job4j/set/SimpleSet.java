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
        objects = new Object[16];
    }

    /**
     *
     * @param e - item to add in array.
     */
    public void add(E e) {
        boolean match = false;
        for (int i = 0; i < size; i++) {
            if (((E) objects[i]).equals(e)) {
                match = true;
                objects[i] = e;
                break;
            }
        }
        if (!match) {
            if (objects.length == size) {
                extendArray();
            }
            objects[size++] = e;
        }
    }

    public void addHash(E e) {
        if (size >= objects.length * 0.8) {
            extendArray();
        }

        int index = (objects.length - 1) & getHash(e);
        Node<E> newNode = new Node(e, null);
        if (objects[index] == null) {
            objects[index] = new Node<E>(e, null);
            size++;
        } else {
            Node<E> current = (Node<E>) objects[index];
            if (!current.item.equals(e)) {
                if (current.next == null) {
                    current.next = newNode;
                    size++;
                } else {
                    current = current.next;
                    while (true) {
                        if (current.item.equals(e)) {
                            break;
                        }
                        if (current.next == null) {
                            current.next = newNode;
                            size++;
                            break;
                        }
                    }
                }
            }
        }
    }

    private int getHash(Object object) {
        int h = object.hashCode();
        return (object == null) ? 0 : h ^ (h >>> 16);
    }

    private void extendArray() {
        Object[] temp = new Object[objects.length * 2];
        System.arraycopy(objects, 0, temp, 0, size);
        objects = temp;
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

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     *
     * @return - current number of elements in array.
     */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SimpleSet<Integer> simple = new SimpleSet<>();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            simple.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Simple insert time: " + (end - begin));
        System.out.println("Simple size: " + simple.size);

        SimpleSet<Integer> optimum = new SimpleSet<>();
        begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            optimum.addHash(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Optimum insert time: " + (end - begin));
        System.out.println("Optimum size: " + optimum.size);
    }
}