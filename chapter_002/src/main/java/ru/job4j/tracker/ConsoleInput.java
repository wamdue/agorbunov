package ru.job4j.tracker;
import java.util.Scanner;
/**
* Class for work with console.
*/
public class ConsoleInput {
    /**
    * @param scanner - input variable.
    */
    private Scanner scanner = new Scanner(System.in);
    /**
    * @return input number.
    */
    public int readValue() {
	return scanner.nextInt();
    }
    /**
    * @return input String.
    */
    public String readString() {
	return scanner.next();
    }
}
