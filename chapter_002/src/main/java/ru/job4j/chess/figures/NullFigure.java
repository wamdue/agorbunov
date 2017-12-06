package ru.job4j.chess.figures;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
* Knight figure.
*/
public class NullFigure extends Figure {
	/**
	 * Main constructor.
	 * @param position - position of the figure.
	 * @param side - side.
	 */
	public NullFigure(Cell position, Side side) {
		super(position, side);
	}

	/**
	 * Way of the figure.
	 * @param dist - cell where you want to move figure.
	 * @return - possible way.
	 * @throws ImpossibleMoveException - exception if cannot move.
	 */
	public Cell[] way(Cell dist) throws ImpossibleMoveException {
		throw new ImpossibleMoveException();
	}

	/**
	 * Sign of the figure.
	 * @return - name on board.
	 */
	public String toString() {
		return " ";
	}
}
