package ru.job4j.generics;

/**
 * Created on 20.07.17.
 * Generic example, simple collection.
 * @author Wamdue
 * @version 1.0
 * @param <T> - class to work with.
 */
public class SimpleArray<T> {
    /**
     * Array of the elements.
     */
    private Object[] objects;
    /**
     * Current position.
     */
    private int position = 0;

    /**
     * Main constructor.
     * @param size - maximum size of array.
     */
    public SimpleArray(int size) {
        objects = new Object[size];
    }

    /**
     *
     * @param t - add new item in array.
     */
    public void  add(T t) {
        objects[position++] = t;
    }

    /**
     * Update item.
     * @param index - index of item.
     * @param t - new item.
     */
    public void update(int index, T t) {
        objects[index] = t;
    }

    /**
     * Delete item from array.
     * @param t - item that you want to delete.
     */
    public void delete(T t) {
        for (int i = 0; i < position; i++) {
            if (objects[i].equals(t)) {
                Object[] temp = new Object[--position];
                System.arraycopy(objects, 0, temp, 0, i);
                System.arraycopy(objects, i + 1, temp, i, position - i - 1);
                objects = temp;
                break;
            }
        }
    }

    /**
     * Get item from array.
     * @param index - index of item in array.
     * @return item
     */
    public T get(int index) {
        return (T) objects[index];
    }

    /**
     * Get array size.
     * @return number of entered items.
     */
    public int size() {
        return position;
    }

    /**
     * Get id of the item.
     * @param t - item to work with.
     * @return - id of the item.
     */
    public int getId(T t) {
        int result = -1;
        for (int i = 0; i < position; i++) {
            if (objects[i].equals(t)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
