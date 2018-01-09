package ru.job4j.fin;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 08.01.18.
 * Testing crosses.
 * @author Wamdue
 * @version 1.0
 */
public class CrossTest {
    /**
     * Making board with size 3.
     * Making moves -1 1.
     * Expect: draw empty board and exit.
     */
    @Test
    public void whenStartGameEndExitGameThenReturnGoodBye() {
        this.testCase("-1 1\n", "- - - \n- - - \n- - - \n");
    }

    /**
     * Making board with size 3.
     * Making moves: 1 1, 1 2, 2 1, 1 3, 3 1.
     * Expect: cross player must win, and game stopped.
     */
    @Test
    public void whenHaveWinnerThenShowIt() {
        String moves = "1 1\n1 2\n2 1\n1 3\n3 1";
        String expect = "- - - \n- - - \n- - - \nX - - \n- - - \n- - - \nX - - \nO - - \n- - - \nX X - \nO - - \n- - - \nX X - \nO - - \nO - - \nPlayer X is winner!\nX X X \nO - - \nO - - \n";
        this.testCase(moves, expect);
    }

    /**
     * Making board with size 3.
     * Making moves: 1 1, 2 1, 2 2, 3 3, 1 2, 1 3, 2 3, 3 2, 3 1.
     * Expect: no free cells, expecting warning message and stop the game.
     */
    @Test
    public void whenNoMoreFreeCellsThenShowItAndStop() {
        String moves = "1 1\n2 1\n2 2\n3 3\n1 2\n1 3\n2 3\n3 2\n3 1\n";
        String expect = "- - - \n- - - \n- - - \nX - - \n- - - \n- - - \nX O - \n- - - \n- - - \nX O - \n- X - \n- - - \nX O - \n- X - \n- - O \nX O - \nX X - \n- - O \nX O - \nX X - \nO - O \nX O - \nX X - \nO X O \nX O - \nX X O \nO X O \nX O X \nX X O \nO X O \nNo more moves.\n";
        this.testCase(moves, expect);
    }

    /**
     * Making board with input string.
     * @param inputText - input text.
     * @param expect expected result.
     */
    private void testCase(String inputText, String expect) {
        ByteArrayInputStream in = new ByteArrayInputStream(inputText.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(out);
        System.setOut(stream);
        System.setIn(in);

        Cross cross = new Cross();
        cross.game();

        assertThat(out.toString(), is(expect));
    }

}