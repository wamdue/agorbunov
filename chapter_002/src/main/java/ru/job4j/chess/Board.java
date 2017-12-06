package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Bishop;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.King;
import ru.job4j.chess.figures.Knight;
import ru.job4j.chess.figures.NullFigure;
import ru.job4j.chess.figures.Pawn;
import ru.job4j.chess.figures.Queen;
import ru.job4j.chess.figures.Rook;
import ru.job4j.chess.figures.Side;

/**
 * Playing board.
 */
public class Board {
    /**
     * Main array of figures on the field.
     */
    private Figure[] figures;
    /**
     * Size of the board.
     */
    public static final int HEIGHT = 8;

    /**
     * @param source - source position of figure.
     * @param dist   - desierable position.
     * @throws ImpossibleMoveException - cannot move exception.
     * @throws OccupiedWayException - cell is budy exception.
     * @throws FigureNotFoundException - figure not found exception.
     * @return true if we can move, otherwise false.
     *
     */
    public boolean move(Cell source, Cell dist)
            throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure src = getFigure(source);

        if (src == null) {
            throw new FigureNotFoundException();
        }

        int tmpX = source.getX();
        int tmpY = source.getY();
        Cell[] route = src.way(dist);
        int idDist = getFigureId(dist);
        int idSource = getFigureId(source);

        if (freeRoute(route, src)) {
            figures[idSource].getPosition().setX(dist.getX());
            figures[idSource].getPosition().setY(dist.getY());
            figures[idDist] = new NullFigure(new Cell(tmpX, tmpY), Side.EMPTY);
        } else {
            throw new OccupiedWayException();
        }

        return true;
    }

    /**
     * Init method, to fill figures.
     */
    public void init() {
        figures = new Figure[64];
        int id = 0;
        for (int y = 1; y <= HEIGHT; y++) {
            for (int x = 1; x <= HEIGHT; x++) {
                if (y == 1 || y == 8) {
                    if (x == 1 || x == 8) {
                        figures[id++] = new Rook(new Cell(x, y), getSide(y));
                    } else if (x == 2 || x == 7) {
                        figures[id++] = new Bishop(new Cell(x, y), getSide(y));
                    } else if (x == 3 || x == 6) {
                        figures[id++] = new Knight(new Cell(x, y), getSide(y));
                    } else if (x == 4) {
                        figures[id++] = new King(new Cell(x, y), getSide(y));
                    } else {
                        figures[id++] = new Queen(new Cell(x, y), getSide(y));
                    }
                } else if (y == 2 || y == 7) {
                    figures[id++] = new Pawn(new Cell(x, y), getSide(y));
                } else {
                    figures[id++] = new NullFigure(new Cell(x, y), Side.EMPTY);
                }
            }
        }
    }

    /**
     * Method prints board in console.
     */
    public void draw() {
        System.out.println("  A B C D E F G H");
        for (int y = 1; y <= HEIGHT; y++) {
            System.out.print(y + " ");
            for (int x = 1; x <= HEIGHT; x++) {
                for (Figure f : figures) {
                    if (f.getPosition().getX() == x && f.getPosition().getY() == y) {
                        System.out.print(f + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * @param y - param.
     * @return - side of the figure.
     */
    private Side getSide(int y) {
        if (y >= 2) {
            return Side.BLACK;
        } else {
            return Side.WHITE;
        }
    }

    /**
     * Get figure in cell.
     *
     * @param source - primary coords of the cell.
     * @return - figure or null if not found any figure.
     */
    private Figure getFigure(Cell source) {
        return figures[getFigureId(source)];
    }

    /**
     *
     * @param source - source cell.
     * @return - index of the figure in figures.
     */
    private int getFigureId(Cell source) {
        int result = -1;
        for (int i = 0; i < figures.length; i++) {
            Figure f = figures[i];
            if (f.getPosition().getX() == source.getX()
                    && f.getPosition().getY() == source.getY()) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Checks if route is free to move.
     * @param route - possible route.
     * @param f - figure that want to move.
     * @return - true if possible, false if not.
     */
    private boolean freeRoute(Cell[] route, Figure f) {
        boolean result = true;
        int len = route.length;
        for (int i = 0; i < len; i++) {
            Figure figure = getFigure(route[i]);
            if (figure != null) {
                if (i == len - 1 && figure.getSide() == f.getSide()) {
                    result = false;
                } else if (figure.getSide() != Side.EMPTY && i < len - 1) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Link to figures.
     * @return - all figures.
     */
    public Figure[] getFigures() {
        return figures;
    }
}
