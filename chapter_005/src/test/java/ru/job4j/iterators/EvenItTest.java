package ru.job4j.iterators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Wamdue.
 * @version 1.0
 * @since 19.07.2017
 */
public class EvenItTest {
    /**
     * Testing if not have any evens.
     * have array: 4, 2, 1, 1.
     * call two times next() then has next.
     * expect false;
     */
    @Test
    public void whenDoNotHaveMoreEvenNumbersThenReturnFalse() {
        int[] evens = new int[] {4, 2, 1, 1};
        EvenIt evenIt = new EvenIt(evens);
        boolean expect = false;
        evenIt.next();
        evenIt.next();
        assertThat(evenIt.hasNext(), is(expect));
    }

    /**
     * Testing return next even value.
     * have array: 4, 1, 1, 2.
     * call next().
     * Expect: 2.
     */
    @Test
    public void whenHaveEvensInArrayThenReturn() {
        int[] evens = new int[] {4, 1, 1, 2};
        EvenIt evenIt = new EvenIt(evens);
        int expect = 2;
        evenIt.next();
        assertThat(evenIt.next(), is(expect));
    }

    /**
     * Testing if have any evens.
     * have array: 4, 1, 1, 6.
     * call next() then hasNext()
     * expect true;
     */
    @Test
    public void whenHaveMoreEvenNumbersThenReturnTrue() {
        int[] evens = new int[] {4, 1, 1, 6};
        EvenIt evenIt = new EvenIt(evens);
        boolean expect = true;
        evenIt.next();
        assertThat(evenIt.hasNext(), is(expect));
    }

}