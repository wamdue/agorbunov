package ru.job4j.strategy;
/**
* Class that uses strategy.
*/
public class Paint {
    /**
    * Move string to console.
    */
    public void draw(Shape shape) {
	System.out.print(shape.pic());
    }
}