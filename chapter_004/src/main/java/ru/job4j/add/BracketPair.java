package ru.job4j.add;

/**
 * Created on 10.01.18.
 * Pair information.
 * @author Wamdue
 * @version 1.0
 */
class BracketPair {
    /**
     * Open char.
     */
    private final char open;
    /**
     * Open char position..
     */
    private int openPos;
    /**
     * Close char.
     */
    private final char close;
    /**
     * Close char position.
     */
    private int closePos;

    /**
     * Main constructor.
     * @param open - open char.
     * @param close - close char.
     */
    BracketPair(char open, char close) {
        this.open = open;
        this.close = close;
    }

    /**
     * Set open char position.
     * @param openPos - position.
     */
    void setOpenPos(int openPos) {
        this.openPos = openPos;
    }

    /**
     * Set close char position.
     * @param closePos - position.
     */
    void setClosePos(int closePos) {
        this.closePos = closePos;
    }

    /**
     * Get open position.
     * @return position.
     */
    public int getOpenPos() {
        return openPos;
    }

    /**
     * Get close position.
     * @return position.
     */
    public int getClosePos() {
        return closePos;
    }
    /**
     * Get close char.
     * @return close char.
     */
    char getClose() {
        return close;
    }

    /**
     * Comparing open char and openPos..
     * @param o - object to compare.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        }
        if (o == null || getClass() != o.getClass()) {
            result = false;
        }

        BracketPair that = (BracketPair) o;

        if (open != that.open) {
            result =  false;
        }

        result = openPos == that.openPos;
        return result;
    }

    /**
     * Hashcode calculation.
     * @return result.
     */
    @Override
    public int hashCode() {
        int result = (int) open;
        result = 31 * result + openPos;
        return result;
    }

    /**
     * Show info about pair.
     * @return string info.
     */
    @Override
    public String toString() {
        return String.format("open %s at %d, close %s at %d",
                this.open, this.openPos, this.close, this.closePos);
    }
}
