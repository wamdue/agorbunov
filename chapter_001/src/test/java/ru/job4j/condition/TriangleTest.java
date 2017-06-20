package ru.job4j.condition;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
/**
* Testing for evaluation area of the triangle.
*/
public class TriangleTest {
    /**
    * a(1, 1), b(1, 3), c(3, 3) = 2.0.
    */
    @Test
    public void whenPointsNotInLineThenTrue() {
	Point a = new Point(1, 1);
	Point b = new Point(1, 3);
	Point c = new Point(3, 3);
	Triangle triangle = new Triangle(a, b, c);
	double expected = 2.0D;
	assertThat(triangle.area(), closeTo(expected, 0.01D));
    }
    /**
    * a(1, 1), b(1, 3), c(1, 3) = -1.
    */
    @Test
    public void whenPointsIdInLineThenFalse() {
	Point a = new Point(1, 1);
	Point b = new Point(1, 3);
	Point c = new Point(1, 3);
	Triangle triangle = new Triangle(a, b, c);
	double expected = -1D;
	assertThat(triangle.area(), closeTo(expected, 0.01D));
    }
    /**
    * a(1, 1), b(1, 1), c(3, 3) = 0.
    */
    @Test
    public void whenTwoSamePointsThenFalse() {
	Point a = new Point(1, 1);
	Point b = new Point(1, 1);
	Point c = new Point(3, 3);
	Triangle triangle = new Triangle(a, b, c);
	double expected = 0D;
	assertThat(triangle.area(), closeTo(expected, 0.01D));
    }
}