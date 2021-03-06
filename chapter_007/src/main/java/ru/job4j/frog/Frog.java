package ru.job4j.frog;

/**
 * Created on 27.09.17
 * Wave algorithm, find shortest way to finish.
 * @author Wamdue
 * @version 1.0
 */
public class Frog {
    /**
     * Limit of recursion depth.
     */
    private static final int LIMIT = 5;
    /**
     * Win constant.
     */
    private static final int WIN = 2;
    /**
     * Constant of tree.
     */
    private static final int TREE = 0;

    /**
     * Main matrix.
     */
    private int[][] labyrinth;
    /**
     * Size of axis x.
     */
    private final int sizeX;
    /**
     * Size of axis y.
     */
    private final int sizeY;
    /**
     * Possible moves.
     */
    private final Step[] directs = {
            new Step(1, -2),
            new Step(1, 2),
            new Step(2, -1),
            new Step(2, 1),
            new Step(3, 0)
    };
    /**
     * Main constructor.
     * @param labyrinth - matrix to analyze.
     */
    public Frog(int[][] labyrinth) {
        this.labyrinth = labyrinth;
        this.sizeX = labyrinth.length;
        this.sizeY = labyrinth[0].length;
    }

    /**
     * Class to make moves.
     */
    private static final class Step {
        /**
         * Coord x.
         */
        private final int x;
        /**
         * Coord y.
         */
        private final int y;

        /**
         * Main constructor.
         * @param x - coord x.
         * @param y - coord y.
         */
        Step(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Class to store result of the step.
     */
    private  class Way {
        /**
         * Count of moves.
         */
        private final int size;
        /**
         * State of way, ended or not.
         */
        private final boolean win;

        /**
         * Main constructor.
         * @param win - state of way.
         * @param size - count of moves.
         */
        Way(final boolean win, final int size) {
            this.size = size;
            this.win = win;
        }

        /**
         * Get count of moves.
         * @return - count of moves.
         */
        int getSize() {
            return this.size;
        }

        /**
         * Get current state.
         * @return - state of way.
         */
        boolean isWin() {
            return this.win;
        }
    }


    /**
     * Starting coords method.
     * @param x - x axis
     * @param y - y axis.
     * @return count of moves, if it can be reached.
     */
    public Way isWinner(int x, int y) {
        return isWinner(x, y, 0);
    }

    /**
     * Recursive method to find way to the finish.
     * @param x - x axis.
     * @param y - y axis.
     * @param stepCount - step number.
     * @return result.
     */
    private Way isWinner(int x, int y, int stepCount) {
        Way result = new Way(false, stepCount);
        if (stepCount <= LIMIT && x >= 0 && y >= 0 && y < this.sizeY) {
            if (x >= this.sizeX) {
                x -= this.sizeX;
            }

            if (this.labyrinth[x][y] == WIN) {
                result = new Way(true, stepCount);
            } else if (this.labyrinth[x][y] != TREE) {
                for (Step step : this.directs) {
                    Way way = this.isWinner(x + step.x, y + step.y, stepCount + 1);
                    if (way.isWin() && (!result.isWin() || result.getSize() > way.getSize())) {
                        result = way;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Raw test.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        int[][] labyrinth = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1},

        };

        Frog frog = new Frog(labyrinth);
        Way way = frog.isWinner(10, 6);
        System.out.println(way.getSize() + " " + way.isWin());
    }

}