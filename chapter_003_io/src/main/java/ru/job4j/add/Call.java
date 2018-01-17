package ru.job4j.add;

/**
 * Created on 17.01.18.
 * Call information.
 * @author Wamdue
 * @version 1.0
 */
public class Call implements Comparable<Call> {
    /**
     * Floor number.
     */
    private int floor;
    /**
     * Where call was made.
     */
    private char status;

    /**
     * Main constructor.
     * @param floor - floor number.
     * @param status - place of call.
     */
    public Call(int floor, char status) {
        this.floor = floor;
        this.status = status;
    }

    /**
     * Get floor number.
     * @return - floor number.
     */
    public int getFloor() {
        return this.floor;
    }

    /**
     * Get call place.
     * @return - place.
     */
    public char getStatus() {
        return this.status;
    }

    /**
     * Sorting by place of call.
     * @param o - item to compare.
     * @return - result.
     */
    @Override
    public int compareTo(Call o) {
        return this.getStatus() - o.getStatus();
    }
}
