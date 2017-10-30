package ru.job4j.fin;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 22.09.17.
 * Hero class.
 * @author Wamdue
 * @version 1.0
 */
public class Hero extends AbstractChar implements Runnable {
    /**
     * Main constructor.
     * @param board - board, where to create.
     * @param cell - cell where to place.
     */
    public Hero(ReentrantLock[][] board, Cell cell) {
        super(board, cell);
        board[cell.getY()][cell.getX()].lock();
    }

    /**
     * here must be moves.
     */
    @Override
    public void run() {

    }
}
