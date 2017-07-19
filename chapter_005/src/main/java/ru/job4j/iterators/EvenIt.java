package ru.job4j.iterators;

import java.util.Iterator;

/**
 * Iterator for even values.
 * @author Wamdue
 * @version 1.0
 * @since 19.07.2017
 */
public class EvenIt implements Iterator {
    private int[] even;
    private int position = 0;

    /**
     * Main constructor.
     * @param even - source array.
     */
    public EvenIt(int[] even) {
        this.even = even;
    }

    /**
     *
     * @return true if array have even value next from current position.
     */
    @Override
    public boolean hasNext() {
        boolean state = false;
        if (position < even.length) {
            for (int i = position; i < even.length; i++) {
                if (even[i] % 2 == 0) {
                    state = true;
                    break;
                }
            }
        }
        return state;
    }

    /**
     *
     * @return even value, if don`t have, then -1.
     */
    @Override
    public Object next() {
        int result = -1;
        for (int i = position; i < even.length; i++) {
            if (even[i] % 2 == 0) {
                result = even[i];
                position = i + 1;
                break;
            }
        }
        return result;
    }
}
