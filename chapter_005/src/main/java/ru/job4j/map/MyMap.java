package ru.job4j.map;

import java.util.Iterator;

/**
 * Created on 04.08.17
 * My realization of map.
 * @author Wamdue
 * @version 1.0
 */
public class MyMap<K, V> {
    /**
     * Main array.
     */
    private Node<K, V>[] objects;
    /**
     * current items in array.
     */
    private int size = 0;

    /**
     * main constructor.
     */
    public MyMap() {
        objects = new Node[16];
    }

    /**
     * method to insert item in map
     * @param key - key value.
     * @param value - value.
     * @return true if cell in array if free, false if not.
     */
    public boolean insert(K key, V value) {

        if (size >= objects.length * 0.8) {
            extend();
        }

        Node<K, V> newNode = new Node<>(key, value);
        boolean result = false;
        int index = hash(key) & (objects.length - 1);

        if (objects[index] == null) {
            objects[index] = newNode;
            size++;
            result = true;
        }
        return result;
    }

    /**
     *
     * @return current size.
     */
    public int size() {
        return size;
    }

    /**
     *
     * @param key - key to find in map
     * @return - value of the key.
     */
    public V get (K key) {
        return objects[getIndex(key)].value;
    }

    /**
     *
     * @param key - key to delete item from.
     * @return true if item was deleted, false if not.
     */
    public boolean delete(K key) {
        boolean result = true;
        int index = getIndex(key);
        if (objects[index] == null) {
            result = false;
        } else {
            objects[index] = null;
            size--;
        }
        return result;
    }

    /**
     * calculare hash of the item.
     * @param key - key value.
     * @return hash number.
     */
    private int hash(K key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * extends main array of objects.
     */
    private void extend() {
        Node<K, V>[] temp = new Node[objects.length * 2];
        System.arraycopy(objects, 0, temp, 0, objects.length - 1);
        objects = temp;
    }

    /**
     *
     * @param key - key value to search.
     * @return - index in array.
     */
    private int getIndex(K key) {
        return hash(key) & (objects.length - 1);
    }

    /**
     * abstract class for iterators.
     */
    abstract class MyIterator {
        /**
         * current position.
         */
        private int position = 0;

        /**
         *
         * @return true if has more items.
         */
        public boolean hasNext() {
            return id() != -1;
        }

        /**
         *
         * @return next item
         */
        public Node<K, V> nextNode() {
            position = id();
            return objects[position++];
        }

        /**
         *
         * @return next available position in array.
         */
        private int id() {
            int id = position;
            while (true) {
                if (id == objects.length) {
                    id = -1;
                    break;
                }
                if (objects[id] != null) {
                    break;
                } else {
                    id++;
                }
            }
            return id;
        }
    }

    /**
     * key iterator class.
     */
    private class KeyIterator extends MyIterator implements Iterator<K> {

        public K next() {
            return nextNode().key;
        }
    }

    /**
     * value iterator class.
     */
    public class ValueIterator extends MyIterator implements Iterator<V> {

        public V next() {
            return nextNode().value;
        }
    }

    /**
     * class to store key and value.
     * @param <K> - key.
     * @param <V> - value.
     */
    private class Node<K, V> {
        K key;
        V value;
        Node (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     *
     * @return - key iterator.
     */
    public Iterator<K> keyIterator() {
        return new KeyIterator();
    }

    /**
     *
     * @return - value iterator.
     */
    public Iterator<V> valueIterator() {
        return new ValueIterator();
    }
}
