package ru.job4j.frog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 27.09.17
 * Wave algorithm, find shortest way to finish.
 * @author Wamdue
 * @version 1.0
 */
public class Frog {
    private static final int WALL = 0;
    /**
     * Main matrix.
     */
    private int[][] labyrinth;
    /**
     * clone of the main matrix, to move frog.
     */
    private int[][] maze;
    /**
     * List of possible moves.
     */
    private List<Point> buf = new ArrayList<>();

    /**
     * Main constructor.
     * @param labyrinth - matrix to analyze.
     */
    public Frog (int[][] labyrinth) {
        this.labyrinth = labyrinth;
        maze = new int[labyrinth.length][labyrinth[0].length];
    }

    /**
     * Adding point to the list.
     * @param p - point to move.
     * @param x - current step.
     */
    private void push(Point p, int x) {
        if (maze[p.getY()][p.getX()] <= x) return;
        maze[p.getY()][p.getX()] = x;
        buf.add(p);
    }

    /**
     * @return - remove point from the list, and return it.
     */
    private Point pop() {
        if (buf.isEmpty()) return null;
        return buf.remove(0);
    }

    /**
     *
     * @param start - start position.
     * @param finish - place where to go.
     * @return - array of the moves.
     */
    private Point[] find(Point start, Point finish) {
        int n = 0;
        int t = 0;
        Point p;

        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(maze[i], Integer.MAX_VALUE);
        }

        this.push(start, 0);

        while ((p = this.pop()) != null) {
            if (p.equals(finish)) {
                System.out.println("Found path");
                System.out.println(n);
            }

            n = maze[p.getY()][p.getX()] + labyrinth[p.getY()][p.getX()];

            if ((p.getY() + 1  < labyrinth.length) && labyrinth[p.getY() + 1][this.positive(p.getX() + 2)] != WALL) {
                this.push(new Point( this.positive(p.getX() + 2), p.getY() + 1), n);
            }
            if ((p.getY() + 2 < labyrinth.length) && labyrinth[p.getY() + 2][this.positive(p.getX() + 1)] != WALL) {
                this.push(new Point(this.positive(p.getX() + 1), p.getY() + 2), n);
            }
            if ((p.getY() - 2 >= 0) && labyrinth[p.getY() - 2][this.positive(p.getX() + 1)] != WALL) {
                this.push(new Point(this.positive(p.getX() + 1), p.getY() - 2), n);
            }
            if ((p.getY() - 1 >= 0) && labyrinth[p.getY() - 1][this.positive(p.getX() + 2)] != WALL) {
                this.push(new Point(this.positive(p.getX() + 2), p.getY() - 1), n);
            }
            if (p.getY() == finish.getY() && labyrinth[p.getY()][this.positive(p.getX() + 3)] != WALL) {
                this.push(new Point(this.positive(p.getX() + 3), p.getY()), n);
            }
        }

        if (maze[finish.getY()][finish.getX()] == -1) {
            System.out.println("Path cannot be found");
            return null;
        } else {
            System.out.println("Search completed");
        }

        List<Point> path = new ArrayList<>();
        path.add(finish);

        int x = finish.getX();
        int y = finish.getY();
        int tx = 0;
        int ty = 0;

        n = Integer.MAX_VALUE;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.printf(" %d",maze[i][j]);
            }
            System.out.println();
        }

        while (x != start.getX() || y != start.getY()) {
            if ((y + 1 < maze.length) && maze[y + 1][positive(x - 2)] < n) {
                tx = positive(x - 2);
                ty = y + 1;
                t = maze[y + 1][positive(x - 2)];
            }
            if ((y + 2 < maze.length) && maze[y + 2][positive(x - 1)] < n) {
                tx = positive(x - 1);
                ty = y + 2;
                t = maze[y + 1][positive(x - 2)];
            }
            if ((y - 1 >= 0) && maze[y - 1][positive(x - 2)] < n) {
                tx = positive(x - 2);
                ty = y - 1;
                t = maze[y - 1][positive(x - 2)];
            }
            if ((y - 2 >= 0) && maze[y - 2][positive(x - 1)] < n) {
                tx = positive(x - 1);
                ty = y - 2;
                t = maze[y - 2][positive(x - 1)];
            }
            if (maze[y][positive(x - 3)] < n) {
                tx = positive(x - 3);
                ty = y;
                t = maze[y][positive(x - 3)];
            }
            x = tx;
            y = ty;
            n = t;
            path.add(new Point(x, y));
        }

        Point[] result = new Point[path.size()];
        t = path.size();
        for (Point point : path) {
            result[--t] = point;
        }
        return result;
    }

    /**
     * Emulation of the infinite matrix by the axis x.
     * @param val - desirable coord.
     * @return correct value, through length of matrix
     */
    private int positive(int val) {
        int result;
        if (val < 0) {
            result = labyrinth[0].length + val;
        } else {
            result = val > labyrinth[0].length - 1 ? (val - labyrinth[0].length): val;
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
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},

        };

        Frog frog = new Frog(labyrinth);
        Point start = new Point(10, 6);
        Point finish = new Point(8, 9);
        Point[] result = frog.find(start, finish);
        for (Point p : result) {
            System.out.println(p);
        }
    }

}