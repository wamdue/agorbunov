package ru.job4j.chess;
import ru.job4j.chess.figures.*;

/**
* Runtime test.
*/
public class Run {
	public static void main(String[] args) {
		Board board = new Board();
		board.init();
		board.draw();
		for (int i = 0; i < board.figures.length; i++) {
			Figure f = board.figures[i];
			if (f.position.getX() == 1 && f.position.getY() == 2) {
				board.figures[i] = new NullFigure(new Cell(1, 2), Side.EMPTY);
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
