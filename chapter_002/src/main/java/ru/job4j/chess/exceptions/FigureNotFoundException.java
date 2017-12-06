package ru.job4j.chess.exceptions;
/**
* The move cannot be done becose of other figures on the line.
*/
public class FigureNotFoundException extends RuntimeException {
	/**
	 * Main constructor.
	 */
	public FigureNotFoundException() {
		super("Cannot find figure.");
	}
}
