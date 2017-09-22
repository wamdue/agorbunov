package ru.job4j.fin;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 22.09.17
 * Monster class.
 * @author Wamdue
 * @version 1.0
 */
public class Monster extends AbstractChar implements Runnable {

    private Random random = new Random();

    public Monster(ReentrantLock[][] board, Cell cell) {
        super(board, cell);
        board[cell.getY()][cell.getX()].lock();
    }

    @Override
    public void run() {
        boolean result = false;
        try {
            while (true) {
                result = moveChar(Move.values()[random.nextInt(3)]);
                if (result) {
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
