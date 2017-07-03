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
	if ((calcSide(a, b) < calcSide(a, c) + calcSide(b, c))
	 && (calcSide(a, c) < calcSide(a, b) + calcSide(b, c))
	 && (calcSide(b, c) < calcSide(a, b) + calcSide(a, c))) {
	    return Math.abs((Double.valueOf((a.getX() - b.getX()) * (c.getY() - b.getY()))
		- Double.valueOf((a.getY() - b.getY()) * (c.getX() - b.getX()))) / 2);
	}
	return -1D;
    }
    /**
    * @param a - first point.
    * @param b - second side.
    * @return length of a side.
    */
    public double calcSide(Point a, Point b) {
	return Math.sqrt(Math.sqrt(b.getX() - a.getX()) + Math.sqrt(b.getY() - a.getY()));
    }
}