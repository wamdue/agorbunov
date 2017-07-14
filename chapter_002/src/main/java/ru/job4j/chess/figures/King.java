package ru.job4j.chess.figures;
import ru.job4j.chess.*;
import ru.job4j.chess.exceptions.*;
/**
* King figure.
*/
public class King extends Figure {
	/**
	* Main constructor.
	* @param cell - current position.
	* @param side - set side of figure.
	*/
	public King (Cell position, Side side) {
		super(position, side);
	}
	/**
	* @param dist - desirable distination.
	* @return possible moves or throw ImpossibleMoveException.
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
