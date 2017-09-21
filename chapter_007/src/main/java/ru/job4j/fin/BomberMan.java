package ru.job4j.fin;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 21.09.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class BomberMan {
    private final ReentrantLock[][] board = new ReentrantLock[SIZE][SIZE];
    private final AtomicInteger x = new AtomicInteger(0);
    private final AtomicInteger y = new AtomicInteger(0);
    private static final int SIZE = 10;
    private final Random random = new Random();

    public BomberMan() {
        this.init();
    }

    private void init() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        board[0][0].lock();
    }

    public void move() {
        int counter = 0;
        try {
            while (counter < 10) {
                moveChar(Move.values()[random.nextInt(3)]);
                System.out.printf(" x = %d, y = %d\n", x.get(), y.get());
                Thread.sleep(1000);
                counter++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private boolean moveChar(Move move) {
        int curX = x.get();
        int curY = y.get();
        boolean result = false;
        try {
            if (move == Move.LEFT) {
                if (curX > 0) {
                    result = board[curY][curX - 1].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        x.decrementAndGet();
                    }
                }
            } else if (move == Move.RIGHT) {
                if (curX < SIZE) {
                    result = board[curY][curX + 1].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        x.incrementAndGet();
                    }
                }
            } else if (move == Move.UP) {
                if (curY > 0) {
                    result = board[curY - 1][curX].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        y.decrementAndGet();
                    }
                }
            } else if (move == Move.DOWN) {
                if (curY > 0) {
                    result = board[curY + 1][curX].tryLock(500, TimeUnit.MILLISECONDS);
                    if (result) {
                        y.incrementAndGet();
                    }
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (result) {
            board[curY][curX].unlock();
        } else {
            moveChar(Move.values()[random.nextInt(3)]);
        }
        return result;
    }
}