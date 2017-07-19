package ru.job4j.loop;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test painting chessboard.
*/
public class BoardTest {
    /**
    * width = 4.
    * height = 4.
    * X X
    *  X X
    * X X
    *  X X
    */
    @Test
    public void whenWidthIsFourAndHeightIsFourThenPaint() {
	Board board = new Board();
	StringBuilder expected = new StringBuilder();
	expected.append("X X \n");
	expected.append(" X X\n");
	expected.append("X X \n");
	expected.append(" X X\n");
	assertThat(board.paint(4, 4), is(expected.toString()));
    }
    /**
    * width = 4.
    * height = -1.
    * result null;
    */
    @Test
    public void whenWidthIsFourAndHeightIsMinusThenNull() {
	Board board = new Board();
	assertThat(board.paint(4, -1), is(" "));
    }
    /**
    * width = 5.
    * height = 4.
    * X X X
    *  X X
    * X X X
    *  X X
    */
    @Test
    public void whenWidthIsFiveAndHeightIsFourThenPaint() {
	Board board = new Board();
	StringBuilder sb = new StringBuilder();
	sb.append("X X X\n");
	sb.append(" X X \n");
	sb.append("X X X\n");
	sb.append(" X X \n");
	assertThat(board.paint(5, 4), is(sb.toString()));
    }
}