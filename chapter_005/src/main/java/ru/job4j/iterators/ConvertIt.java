package ru.job4j.iterators;

import java.util.Iterator;

/**
 * Created on 20.07.17.
 * Convert iterator of itertor, to iterator.
 * @author Wamdue
 * @version 1.0
 */
public class ConvertIt {
    /**
     * Method to convert.
     * @param it - iterator to convert.
     * @return - result iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> current = it.next();
            @Override
            public boolean hasNext() {
                boolean state = false;
                if (!current.hasNext()) {
                    state = it.hasNext();
                } else {
                    state = current.hasNext();
                }
                return state;
            }

            @Override
            public Integer next() {
                if (!current.hasNext()) {
                    current = it.next();
                }
                return current.next();
            }
        };
    }
}
