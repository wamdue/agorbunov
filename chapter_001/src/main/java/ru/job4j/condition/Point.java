package ru.job4j.condition;
/**
* Package for finding point on line.
*/
public class Point {
    /**
    *	@param x first param.
    */
    private int x;
    /**
    *	@param y second param.
    */
    private int y;
    /**
    * Base constructor.
    * @param x x.
    * @param y y.
    */
    public Point(int x, int y) {
	this.x = x;
	this.y = y;
    }
    /**
    * @return x.
    */
    public int getX() {
	return this.x;
    }
    /**
    * @return y.
    */
    public int getY() {
	return this.y;
    }
    /**
    * @param a first variable in expression.
    * @param b second variable in expression.
    * @return result of comparing variable.
    */
    public boolean is(int a, int b) {
	return this.y == this.x * a + b;
    }
}