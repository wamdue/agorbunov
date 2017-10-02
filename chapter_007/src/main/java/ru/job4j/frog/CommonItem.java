package ru.job4j.frog;

/**
 * Created on 27.09.17
 *
 * @author Wamdue
 * @version 1.0
 */
public abstract class CommonItem implements Item {
    private int x;
    private int y;

    public CommonItem(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
