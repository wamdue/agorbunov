package ru.job4j.fin;

/**
 * Created on 22.09.17.
 * Cell class, position on the board.
 * @author Wamdue
 * @version 1.0
 */
public class Cell {
    /**
     * Coordinate of x.
     */
    private int x;
    /**
     * Coordinate of y.
     */
    private int y;

    /**
     * Main constructor.
     * @param x - axis x.
     * @param y - axis y.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get axis x.
     * @return - x.
     */
    public int getX() {
        return x;
    }

    /**
     * Set new value of x.
     * @param x - axis x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get axis y.
     * @return - y.
     */
    public int getY() {
        return y;
    }

    /**
     * Set new value of y.
     * @param y - axis y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Overriding equals method.
     * @param o - object to compare.
     * @return true if equals, or false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        if (x != cell.x) {
            return false;
        }
        return y == cell.y;
    }

    /**
     * Calculating new hashcode.
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
