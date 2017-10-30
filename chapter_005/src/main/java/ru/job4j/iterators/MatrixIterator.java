package ru.job4j.iterators;

import java.util.Iterator;

/**
 * Created on 19.07.17.
 * Class for iterate matrix.
 * @author Wamdue
 * @version 1.0
 */
public class MatrixIterator implements Iterator {
    /**
     *  Main matrix for iterate.
     */
    private Integer[][] matrix;
    /**
     * Column number.
     */
    private int col = 0;
    /**
     * Row number.
     */
    private int row = 0;

    /**
     * Main constructor.
     * @param matrix - source matrix.
     */
    public MatrixIterator(Integer[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Check, if can move forward.
     * @return true if can, or false if it can.
     */
    @Override
    public boolean hasNext() {
        return col < matrix.length && row < matrix[col].length;
    }

    /**
     * Return number, and move bracket to the next position.
     * @return matrix cell.
     */
    @Override
    public Integer next() {
        if (col >= matrix[row].length) {
            col = 0;
            row++;
        }
        Integer value = matrix[row][col++];
        return value;
    }
}
