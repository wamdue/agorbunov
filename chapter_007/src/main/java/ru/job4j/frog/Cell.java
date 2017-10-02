package ru.job4j.frog;

/**
 * Created on 27.09.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class Cell {
    private int x;
    private int y;
    private int val;

    public Cell(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
