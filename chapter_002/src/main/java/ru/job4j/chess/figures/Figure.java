package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Abstract class for chess figures.
 */
public abstract class Figure {
    /**
     * Position of the figure.
     */
    private final Cell position;
    /**
     * Get current side.
     */
    private final Side side;

    /**
     * Main constructor.
     *
     * @param position - current position.
     * @param side     - current side.
     */
    public Figure(Cell position, Side side) {
        this.position = position;
        this.side = side;
    }

    /**
     * Possible way to destination.
     *
     * @param dist - cell where you want to move figure.
     * @throws ImpossibleMoveException - if move cannot be done, throw exception.
     * @return - possible way.
     */
    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Get current position.
     * @return - current position.
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Get side.
     * @return - current side.
     */
    public Side getSide() {
        return side;
    }
}
