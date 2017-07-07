package ru.job4j.tracker;
/**
* Custom runtime exception.
*/
public class MenuOutException extends RuntimeException {
    /**
    * Main constructor to throw message.
    */
    public MenuOutException(String message) {
	super(message);
    }
}