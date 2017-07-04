package ru.job4j.tracker;
import java.util.Scanner;
/**
* Class for work with console.
*/
public class ConsoleInput implements Input {
    /**
    * @param scanner - input variable.
    */
    private Scanner scanner = new Scanner(System.in);
    /**
    * @param question - message to console.
    * @return - returning input from console.
    */
    public String ask(String question) {
	System.out.println(question);
	return scanner.next();
    }
}
