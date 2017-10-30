package ru.job4j.iterators;

import java.util.Iterator;

/**
 * Created on 20.07.17
 * Prime items.
 * @author Wamdue
 * @version 1.0
 */
public class PrimeIt implements Iterator {
    /**
     * Source array.
     */
    private int[] numbers;
    /**
     * Current bracket position.
     */
    private int position = 0;
    /**
     * Main constructor.
     * @param numbers - source array.
     */
    public PrimeIt(final int[] numbers) {
        this.numbers = numbers;
    }

    /**
     *
     * @return true if prime number exist next from bracket.
     */
    @Override
    public boolean hasNext() {
        return checkNumber() != -1;
    }

    /**
     *
     * @return prime number.
     * @throws IndexOutOfBoundsException if prime number cannot be found.
     */
    @Override
    public Object next() {
        int result = checkNumber();
        if (result > -1) {
            position = result + 1;
            return numbers[result];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Internal method to check array.
     * @return index of prime number in array or -1 if it cannot be found.
     */
    private int checkNumber() {
        int result = -1;
        if (position < numbers.length) {
            for (int i = position; i < numbers.length; i++) {
                if (numbers[i] == 1) {
                    result = i;
                    break;
                } else {
                    int counter = 2;

                    while (counter <= numbers[i]) {
                        if (numbers[i] % counter == 0) {
                            break;
                        }
                        counter++;
                    }

                    if (counter == numbers[i]) {
                        result = i;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
