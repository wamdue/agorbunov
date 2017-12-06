package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.NullFigure;
import ru.job4j.chess.figures.Side;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test class for rook move.
 */
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
		for (Figure f : board.getFigures()) {
		    if (f.getPosition().getX() == 1 && f.getPosition().getY() == 7) {
			f = new NullFigure(new Cell(1, 7), Side.EMPTY);
		    } else if (f.getPosition().getX() == 1 && f.getPosition().getY() == 8) {
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
		for (Figure f : board.getFigures()) {
		    if (f.getPosition().getX() == 1 && f.getPosition().getY() == 2) {
			f = new NullFigure(new Cell(1, 2), Side.EMPTY);
		    } else if (f.getPosition().getX() == 1 && f.getPosition().getY() == 1) {
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
		for (int i = 0; i < board.getFigures().length; i++) {
			Figure f = board.getFigures()[i];
			if (f.getPosition().getX() == 1 && f.getPosition().getY() == 1) {
				victim = board.getFigures()[i];
			}
			if (f.getPosition().getX() == 1 && f.getPosition().getY() == 2) {
				board.getFigures()[i] = new NullFigure(new Cell(1, 2), Side.EMPTY);
				break;
			}
		}
		board.move(new Cell(1, 1), new Cell(1, 5));
		assertThat(victim.getPosition(), is(expect));
	}
}
