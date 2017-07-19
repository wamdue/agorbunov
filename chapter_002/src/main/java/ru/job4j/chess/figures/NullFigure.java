package ru.job4j.chess.figures;
import ru.job4j.chess.exceptions.*;
import ru.job4j.chess.Cell;
/**
* Knight figure.
*/
public class NullFigure extends Figure {
	public NullFigure(Cell position, Side side) {
		super(position, side);
	}
	public Cell[] way(Cell dist) throws ImpossibleMoveException{
		throw new ImpossibleMoveException();
	}
	public String toString() {
		return " ";
	}
}
