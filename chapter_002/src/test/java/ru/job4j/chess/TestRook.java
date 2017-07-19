package ru.job4j.chess;
/**
* Test class for rook move.
*/
import org.junit.Test;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.NullFigure;
import ru.job4j.chess.figures.Side;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
public class TestRook {
    /**
    * Making route.
    * Moving up.
    * from 1 8, to 1, 5
    * expect array 1,7; 1,6; 1,5.
    */
    @Test
    public void whenMoveUpThenDoIt() {
		Board board = new Board();
		Cell[] expect = new Cell[] {new Cell(1, 7), new Cell(1, 6), new Cell(1, 5)};
		board.init();
		Figure victim = null;
		for (Figure f : board.figures) {
		    if (f.position.getX() == 1 && f.position.getY() == 7) {
			f = new NullFigure(new Cell(1, 7), Side.EMPTY);
		    } else if (f.position.getX() == 1 && f.position.getY() == 8) {
			victim = f;
	    }
	}
		try {
			assertThat(victim.way(new Cell(1, 5)), is(expect));
		} catch (ImpossibleMoveException ex) {
		System.out.println("Cannot move");
		ex.printStackTrace();
		}
    }
    /**
    * Making route.
    * Moving down.
    * from 1, 1, to 1, 5
    * expect array 1,2; 1,3; 1,4; 1,5.
    */
    @Test
    public void whenMoveDownThenDoIt() {
		Board board = new Board();
		Cell[] expect = new Cell[] {new Cell(1, 2), new Cell(1, 3), new Cell(1, 4), new Cell(1, 5)};
		board.init();
		Figure victim = null;
		for (Figure f : board.figures) {
		    if (f.position.getX() == 1 && f.position.getY() == 2) {
			f = new NullFigure(new Cell(1, 2), Side.EMPTY);
		    } else if (f.position.getX() == 1 && f.position.getY() == 1) {
			victim = f;
	    }
	}
		try {
			assertThat(victim.way(new Cell(1, 5)), is(expect));
		} catch (ImpossibleMoveException ex) {
		System.out.println("Cannot move");
		ex.printStackTrace();
		}
    }
    /**
    * Moving down.
    * from 1, 1, to 1, 5
    * expect new position (1, 5).
    */
    @Test
    public void whenMoveDownThenMove() {
		Board board = new Board();
		board.init();
		Cell expect = new Cell(1, 5);
		Figure victim = null;
		for (int i = 0; i < board.figures.length; i++) {
			Figure f = board.figures[i];
			if (f.position.getX() == 1 && f.position.getY() == 1) {
				victim = board.figures[i];
			}
			if (f.position.getX() == 1 && f.position.getY() == 2) {
				board.figures[i] = new NullFigure(new Cell(1, 2), Side.EMPTY);
				break;
			}
		}
		board.move(new Cell(1, 1), new Cell(1, 5));
		assertThat(victim.position, is(expect));
	}
}
