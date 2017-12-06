package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
* Bishop figure.
*/
public class Bishop extends Figure {
	/**
	* Main constructor.
	* @param position - current position.
	* @param side - set side of figure.
	*/
	public Bishop(Cell position, Side side) {
		super(position, side);
	}
	/**
	* @param dist - desirable distination.
	* @return possible moves or throw ImpossibleMoveException.
	 * @throws ImpossibleMoveException - exception.
	*/
	public Cell[] way(Cell dist) throws ImpossibleMoveException {
		return null;
	}
	/**
	* @return name of figure on board.
	*/
	@Override
	public String toString() {
		return "B";
	}
}
