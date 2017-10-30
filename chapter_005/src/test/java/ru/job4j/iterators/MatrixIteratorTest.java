package ru.job4j.iterators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 19.07.17.
 * Testing MatrixIterator class.
 * @author Wamdue
 * @version 1.0
 */
public class MatrixIteratorTest {
    /**
     * Test hasNext().
     * Have matrix {0, 1}
     *             {2, 3}
     * Call next() 4 times.
     * call hasNext() expect: false.
     */
    @Test
    public void whenMatrixEndsThenReturnFalse() {
        boolean expect = false;
        Integer[][] matrix = new Integer[][] {
                {0, 1},
                {2, 3}
        };
        MatrixIterator iterator = new MatrixIterator(matrix);

        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(expect));
    }

    /**
     * Test next().
     *  Have matrix  {0, 1, 2},
     *               {3, 4, 5},
     *               {6, 7, 8}
     * 4 times call next.
     * expect value = 3.
     */
    @Test
    public void whenHaveMoreThenOneColumnThenIterateNext() {
        Integer[][] matrix = new Integer[][] {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11}
        };
        MatrixIterator iterator = new MatrixIterator(matrix);
        int expect = 4;

        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

        assertThat(iterator.next(), is(expect));
    }

}