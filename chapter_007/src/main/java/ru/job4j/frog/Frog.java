package ru.job4j.frog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 27.09.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class Frog {
    private int[][] maze = new int[10][16];
    private int[][] labyrinth;
    private List<Point> buf = new ArrayList<>();

    public Frog (int[][] labyrinth) {
        this.labyrinth = labyrinth;
    }

    private void push(Point p, int x) {
        if (maze[p.getY()][p.getX()] <= x) return;
        maze[p.getY()][p.getX()] = x;
        buf.add(p);
    }

    private Point pop() {
        if (buf.isEmpty()) return null;
        return buf.remove(0);
    }

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

            if ((p.getY() + 1  < labyrinth.length) && labyrinth[p.getY() + 1][this.decimal(p.getX() + 2)] != 0) {
                this.push(new Point( this.decimal(p.getX() + 2), p.getY() + 1), n);
            }
            if ((p.getY() + 2 < labyrinth.length) && labyrinth[p.getY() + 2][this.decimal(p.getX() + 1)] != 0) {
                this.push(new Point(this.decimal(p.getX() + 1), p.getY() + 2), n);
            }
            if ((p.getY() - 2 >= 0) && labyrinth[p.getY() - 2][this.decimal(p.getX() + 1)] != 0) {
                this.push(new Point(this.decimal(p.getX() + 1), p.getY() - 2), n);
            }
            if ((p.getY() - 1 >= 0) && labyrinth[p.getY() - 1][this.decimal(p.getX() + 2)] != 0) {
                this.push(new Point(this.decimal(p.getX() + 2), p.getY() - 1), n);
            }
            if (p.getY() == finish.getY() && labyrinth[p.getY()][this.decimal(p.getX() + 3)] != 0) {
                this.push(new Point(this.decimal(p.getX() + 3), p.getY()), n);
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

    private int positive(int val) {
        return val >= 0 ? val : 16 + val;
    }

    private int decimal (int val) {
        return val > 15? (val - 16): val;
    }

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