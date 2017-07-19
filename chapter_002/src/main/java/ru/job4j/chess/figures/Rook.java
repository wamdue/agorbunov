package ru.job4j.chess.figures;
import ru.job4j.chess.*;
import ru.job4j.chess.exceptions.*;
/**
* Rook figure.
*/
public class Rook extends Figure {
	/**
	* Main constructor.
	* @param position - current position.
	* @param side - set side of figure.
	*/
	public Rook (Cell position, Side side) {
		super(position, side);
	}
	/**
	* @param dist - desirable distination.
	* @return possible moves or throw ImpossibleMoveException.
	*/
	public Cell[] way(Cell dist) throws ImpossibleMoveException {
		if (dist.getX() < 1 && dist.getX() > 8 && dist.getY() < 1 && dist.getY() > 8) { // out of range.
			throw new ImpossibleMoveException();
		} else if (dist.getX() != this.position.getX() && dist.getY() != this.position.getY()) { // only one coordinate must be different
			throw new ImpossibleMoveException();
		} else {
			int size = 0;
			Cell[] road;
			int shift = 0;
			if (this.position.getX() != dist.getX()) {
			// moving horizontal.
				size = position.getX() - dist.getX();
				if (size < 0) {
					road = new Cell[size * (-1)];
					for (int i = position.getX() + 1; i <= dist.getX(); i++) {
						road[shift++] = new Cell(i, this.position.getY());
					}
				} else {
					road = new Cell[size];
					for (int i = this.position.getX() - 1; i >= dist.getX(); i--) {
						road[shift++] = new Cell(i, this.position.getY());
					} 
				}
			} else if (this.position.getY() != dist.getY()) {
			// moving vertical.
				size = this.position.getY() - dist.getY();
				if (size < 0) {
					road = new Cell[size * (-1)];
					for (int i = this.position.getY() + 1; i <= dist.getY(); i++) {
						road[shift++] = new Cell(this.position.getX(), i);
					}
				} else {
					road = new Cell[size];
					for (int i = this.position.getY() - 1; i >= dist.getY(); i--) {
						road[shift++] = new Cell(this.position.getX(), i);
					} 
				}				
			} else {
				road = new Cell[0];
			}
			return road;
		}
	}
	/**
	* @return name of figure on board.
	*/
	@Override
	public String toString() {
		return "R";
	}
}
