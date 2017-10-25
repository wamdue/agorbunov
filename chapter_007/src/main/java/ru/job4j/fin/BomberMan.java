package ru.job4j.fin;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 21.09.17
 * BomberMan
 * @author Wamdue
 * @version 1.0
 */
public class BomberMan {
    private final ReentrantLock[][] board = new ReentrantLock[SIZE][SIZE];
    private static final int SIZE = 10;
    private final Random random = new Random();
    private final int numMonsters;
    private final int numBlocks;
    private final List<Monster> monsters = new ArrayList<>();
    private final Set<Cell> allCells = new HashSet<>();

    public BomberMan(int numMonsters, int numBlocks) {
        this.numMonsters = numMonsters;
        this.numBlocks = numBlocks;

        this.init();
        this.initBlocks();
        this.initHero();
        this.initMonsters();

        for (Monster m : monsters) {
            new Thread(m).start();
        }
    }

    /**
     * fills board.
     */
    private void init() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * generating monsters.
     */
    private void initMonsters() {
        Set<Cell> cells = new HashSet<>();
        while (cells.size() < numMonsters) {
            cells.add(new Cell(random.nextInt(SIZE - 1), random.nextInt(SIZE - 1)));
        }

        for (Cell c : cells) {
            Monster m = new Monster(board, c);
            monsters.add(m);
        }

    }

    /**
     * generating blocks.
     */
    private void initBlocks() {
        Set<Cell> cells = new HashSet<>();
        while (cells.size() < numBlocks) {
            cells.add(new Cell(random.nextInt(SIZE - 1), random.nextInt(SIZE - 1)));
        }
        allCells.addAll(cells);
        for (Cell c : cells) {
            board[c.getY()][c.getX()].lock();
        }
    }

    /**
     * creating hero.
     */
    private void initHero() {
        Cell cell;
        while (true) {
            cell = new Cell(random.nextInt(SIZE - 1), random.nextInt(SIZE - 1));
            if (!allCells.contains(cell)) {
                new Thread(new Hero(board, cell)).start();
                break;
            }
        }
    }
}