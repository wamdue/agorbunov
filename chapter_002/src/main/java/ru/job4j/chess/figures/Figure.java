package ru.job4j.chess.figures;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.*;
/**
* Abstract class for chess figures.
*/
public abstract class Figure {
    /**
    * @param current position of the figure.
    */
    public final Cell position;
    /**
    * @param side - get current side.
    */
    public final Side side;
	/**
	* Main constructor.
	*/
	public Figure(Cell position, Side side) {
		this.position = position;
		this.side = side;
	}
    /**
    * @param dist - cell where you want to move figure.
    * @thow - if move cannot be done, throw exception.
    */
    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}
