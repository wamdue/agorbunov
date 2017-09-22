package ru.job4j.fin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 22.09.17
 * Raw class for characters in the game
 * @author Wamdue
 * @version 1.0
 */
public abstract class AbstractChar {
    private final ReentrantLock[][] board;
    private Cell cell;

    public AbstractChar(ReentrantLock[][] board, Cell cell) {
        this.board = board;
        this.cell = cell;
    }

    /**
     * Move method.
     * @param move direction to move.
     * @return if it possible to move in this direction.
     */
    public boolean moveChar(Move move) {
        int curX = cell.getX();
        int curY = cell.getY();
        boolean result = false;
        try {
            if (move == Move.LEFT) {
                if (curX > 0) {
                    result = board[curY][curX - 1].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        cell.setX(curX - 1);
                    }
                }
            } else if (move == Move.RIGHT) {
                if (curX < board.length) {
                    result = board[curY][curX + 1].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        cell.setX(curX + 1);
                    }
                }
            } else if (move == Move.UP) {
                if (curY > 0) {
                    result = board[curY - 1][curX].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        cell.setY(curY - 1);
                    }
                }
            } else if (move == Move.DOWN) {
                if (curY < board.length) {
                    result = board[curY + 1][curX].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        cell.setY(curY + 1);
                    }
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (result) {
            board[curY][curX].unlock();
        }
        return result;
    }

    public Cell getCell() {
        return cell;
    }
}
