package ru.job4j.chess.exceptions;
/**
* Move exception, when try to move out of the board, or move is incorrect.
*/
public class ImpossibleMoveException extends RuntimeException {
	/**
	 * Main constructor.
	 */
	public ImpossibleMoveException() {
		super("This move cannot be done.");
	}
}
