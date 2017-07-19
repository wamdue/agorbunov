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
	double ab = distance(a, b);
	double bc = distance(b, c);
	double ac = distance(a, c);

	if ((ab < bc + ac) && (ac < ab + bc) && (bc < ab + ac)) {
	    return Math.abs((Double.valueOf((a.getX() - b.getX()) * (c.getY() - b.getY()))
		- Double.valueOf((a.getY() - b.getY()) * (c.getX() - b.getX()))) / 2);
	}
	return -1D;
    }
    /**
    * Calculate line length between two points.
    * @param a - first point.
    * @param b - second point.
    * @return line length.
    */
    public double distance(Point a, Point b) {
	return Math.sqrt(Math.sqrt(b.getX() - a.getX()) + Math.sqrt(b.getY() - a.getY()));
    }
}