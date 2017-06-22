package ru.job4j.condition;
/**
    Class for calculating area of triangle.
*/
public class Triangle {
    /**
	@param a first point of triangle.
    */
    private Point a;
    /**
    * @param b second point of triangle.
    */
    private Point b;
    /**
    * @param c third point of triangle.
    */
    private Point c;
    /**
    * Base constructor for initiate points.
    * @param a first point.
    * @param b second point.
    * @param c third point.
    */
    public Triangle(Point a, Point b, Point c) {
	this.a = a;
	this.b = b;
	this.c = c;
    }
    /**
    * @return area of triangle if possible, or -1 if it cannot be.
    */
    public double area() {
	if ((a.getX() == b.getX() && a.getX() == c.getX())
	|| (a.getY() == b.getY() && a.getY() == c.getY())) {
	    return -1;
	}
	return Math.abs((Double.valueOf((a.getX() - b.getX()) * (c.getY() - b.getY()))
		- Double.valueOf((a.getY() - b.getY()) * (c.getX() - b.getX()))) / 2);
    }
}