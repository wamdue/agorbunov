package ru.job4j.chess.exceptions;
/**
* The move cannot be done becose of other figures on the line.
*/
public class OccupiedWayException extends RuntimeException {
	public OccupiedWayException() {
		super("The way is not free.");
	}
}
