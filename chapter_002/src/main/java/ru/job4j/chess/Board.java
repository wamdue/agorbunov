package ru.job4j.chess;
import ru.job4j.chess.exceptions.*;
import ru.job4j.chess.figures.*;
/**
* Playing board.
*/
public class Board {
	/**
	* @param figures - main array of figures on the field;
	*/
    Figure[] figures;
    /**
    * @param HEIGHT - size of the board;
    */
    public static final int HEIGHT = 8;
	/**
	* @param source - source position of figure.
	* @param dist - desierable position.
	* @return true if we can move, otherwise false.
	* @throw exceptions if move cannot be done.
	*/
    public boolean move(Cell source, Cell dist)
    throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
    	Figure src = null;
    	Figure empty = null;
    	for (Figure f : figures) { // search figure in the cell.
			if (!(f instanceof NullFigure)
				&& f.position.getX() == source.getX()
				&& f.position.getY() == source.getY()) {
				src = f;
				break;
			}
    	}

    	if (src == null) {
			throw new FigureNotFoundException();
    	}
		int tmpX = source.getX();
		int tmpY = source.getY();
   	 	Cell[] route = src.way(dist);
    	int len = route.length;
    	
    		for (int i = 0; i < len; i++) {
    			Cell tmp = route[i];
    			for (int j = 0; j < figures.length; j++) {
    				Figure f = figures[j];
    				if (f.position.getX() == tmp.getX() && f.position.getY() == tmp.getY() && i == len - 1 && f.side != src.side) {
    					src.position.setX(dist.getX());
    					src.position.setY(dist.getY());
    					figures[j] = new NullFigure(new Cell(tmpX, tmpY), Side.EMPTY);
						return true;			 
	   				} else if (f.position.getX() == tmp.getX() && f.position.getY() == tmp.getY() 
    					&& (f.side == Side.EMPTY || src.side != f.side)) {
    					continue;
    				} else if (f.position.getX() == tmp.getX() && f.position.getY() == tmp.getY() && f.side == src.side) {
						return false;
    				}
    			}
    		}		    	
		return false;
    }
    public void init() {
    	figures = new Figure[64];
    	int id = 0;
		for (int y = 1; y <= HEIGHT; y++) {
			for (int x = 1; x <= HEIGHT; x++) {
				if (y == 1 || y == 8) {
					if (x == 1 || x == 8) {
						figures[id++] = new Rook(new Cell(x, y), getSide(y));
					} else if (x == 2 || x == 7) {
						figures[id++] = new Bishop(new Cell(x, y), getSide(y));
					} else if (x == 3 || x == 6) {
						figures[id++] = new Knight(new Cell(x, y), getSide(y));
					} else if (x == 4) {
						figures[id++] = new King(new Cell(x, y), getSide(y));
					} else if (x == 5) {
						figures[id++] = new Queen(new Cell(x, y), getSide(y));
					}
				} else if (y == 2 || y == 7) {
					figures[id++] = new Pawn(new Cell(x, y), getSide(y));
				} else {
					figures[id++] = new NullFigure(new Cell(x, y), Side.EMPTY);
				}
			}
		}   
	}
	/**
	* Method prints board in console.
	*/
	public void draw() {
	System.out.println("  A B C D E F G H");
		for (int y = 1; y <= HEIGHT; y++) {
		System.out.print(y + " ");
			for (int x = 1; x <= HEIGHT; x++) {
				for (Figure f : figures) {
					if (f.position.getX() == x && f.position.getY() == y) {
						System.out.print(f + " ");
					}
				}
			}
			System.out.println();
		}
	}
	/**
	* @param y - param.
	*/
	private Side getSide(int y) {
		if (y >= 2) {
			return Side.BLACK;
		} else {
			return Side.WHITE;
		}
	}
}
