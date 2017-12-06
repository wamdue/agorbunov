package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
* King figure.
*/
public class King extends Figure {
	/**
	* Main constructor.
	* @param position - current position.
	* @param side - set side of figure.
	*/
	public King(Cell position, Side side) {
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
		return "K";
	}
}
