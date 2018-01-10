package ru.job4j.fin;

/**
 * Created on 09.01.18.
 * Player information class.
 * @author Wamdue
 * @version 1.0
 */
public class Player {
    /**
     * Player or computer.
     */
    private final Side side;
    /**
     * Figure to play.
     */
    private final char figure;
    /**
     * Board reference.
     */
    private final char[][] board;
    /**
     * Number of player wins.
     */
    private int wins = 0;

    /**
     * Default constructor.
     * @param side - side to play.
     * @param figure - figure to play.
     * @param board - playing board.
     */
    public Player(Side side, char figure, char[][] board) {
        this.side = side;
        this.figure = figure;
        this.board = board;
    }

    /**
     * Get current side.
     * @return side.
     */
    public Side getSide() {
        return side;
    }

    /**
     * Get current figure.
     * @return figure.
     */
    public char getFigure() {
        return figure;
    }

    /**
     * Making move on the boord.
     * @param x - coord of x.
     * @param y - coord of y.
     */
    public void makeMove(int x, int y) {
        if (this.board[y][x ] == Cross.FREE) {
            this.board[y][x] = this.figure;
        }
    }

    /**
     * Auto make move, in first empty cell.
     */
    public void makeMove() {
        boolean flag = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (this.board[i][j] == Cross.FREE) {
                    this.board[i][j] = this.figure;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * Add win.
     */
    public void win() {
        this.wins++;
    }

    /**
     * Get number of wins.
     * @return number of wins.
     */
    public int getWins() {
        return wins;
    }
}
