package ru.job4j.iterators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 20.07.17.
 * Testing PrimeIt class.
 * @author Wamdue
 * @version 1.0
 */
public class PrimeItTest {
    /**
     * Testing if not have any prime numbers.
     * have array: 1, 4, 6, 8, 10.
     * call  next() then hasNext().
     * expect false;
     */
    @Test
    public void whenDoNotHaveMorePrimeNumbersThenReturnFalse() {
        int[] primes = new int[] {1, 4, 6, 8, 10};
        PrimeIt evenIt = new PrimeIt(primes);
        boolean expect = false;
        evenIt.next();
        assertThat(evenIt.hasNext(), is(expect));
    }

    /**
     * Testing return next prime value.
     * have array: 4, 6, 7, 5.
     * call next().
     * Expect: 5.
     */
    @Test
    public void whenHavePrimesInArrayThenReturn() {
        int[] primes = new int[] {4, 6, 7, 5};
        PrimeIt primeIt = new PrimeIt(primes);
        int expect = 5;
        primeIt.next();
        assertThat(primeIt.next(), is(expect));
    }

    /**
     * Testing if have any prime numbers.
     * have array: 4, 1, 1, 6.
     * call next() then hasNext()
     * expect true;
     */
    @Test
    public void whenHaveMoreEvenNumbersThenReturnTrue() {
        int[] primes = new int[] {4, 1, 1, 6};
        PrimeIt primeIt = new PrimeIt(primes);
        boolean expect = true;
        primeIt.next();
        assertThat(primeIt.hasNext(), is(expect));
    }
}