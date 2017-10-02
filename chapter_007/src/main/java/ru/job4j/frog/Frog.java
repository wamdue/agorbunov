package ru.job4j.frog;

/**
 * Created on 27.09.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class Frog {
    private static final int TREE = 1;
    private static final int STAR = 2;
    private static final int FROG = 9;
//    private int[][] field = new int[10][16];
    private Cell[][] field = new Cell[10][16];
    private int startX = 10;
    private int startY = 6;
    private int targetX = 8;
    private int targetY = 9;

    private void init() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell(j, i, 0);
            }
        }

        field[8][13].setVal(TREE);
        field[7][4].setVal(TREE);
        field[9][8].setVal(STAR);
        field[6][10].setVal(FROG);
    }

    public Frog() {
        init();
    }

    public void write() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf(" %d ", field[i][j].getVal());
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        new Frog().write();
    }
}
