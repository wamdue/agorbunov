package ru.job4j.chess;

import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.NullFigure;
import ru.job4j.chess.figures.Side;

/**
* Runtime test.
*/
public class Run {
	/**
	 * Main start class.
	 * @param args - not in use.
	 */
	public static void main(String[] args) {
		Board board = new Board();
		board.init();
		board.draw();
		for (int i = 0; i < board.getFigures().length; i++) {
			Figure f = board.getFigures()[i];
			if (f.getPosition().getX() == 1 && f.getPosition().getY() == 2) {
				board.getFigures()[i] = new NullFigure(new Cell(1, 2), Side.EMPTY);
				break;
			}
		}
		System.out.println(board.move(new Cell(1, 1), new Cell(1, 3)));
		System.out.println();
		System.out.println();
		System.out.println("****************");
		board.draw();
		System.out.println(board.move(new Cell(1, 3), new Cell(3, 3)));
		System.out.println();
		System.out.println();
		System.out.println("****************");
		board.draw();
		System.out.println(board.move(new Cell(3, 3), new Cell(3, 4)));
		System.out.println();
		System.out.println();
		System.out.println("****************");
		board.draw();
		System.out.println(board.move(new Cell(3, 4), new Cell(1, 4)));
		System.out.println();
		System.out.println();
		System.out.println("****************");
		board.draw();
		System.out.println(board.move(new Cell(1, 4), new Cell(1, 1)));
		System.out.println();
		System.out.println();
		System.out.println("****************");
		board.draw();
	}
}
