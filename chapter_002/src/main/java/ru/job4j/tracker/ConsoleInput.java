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
	return scanner.nextLine();
    }
    /**
    * @param question - message to console.
    * @param range - range of values.
    * @return - digit input from console.
    */
    public int ask(String question, int[] range) throws MenuOutException{
	int key = Integer.valueOf(this.ask(question));
	boolean exist = false;
	for (int value : range) {
	    if (key == value) {
		exist = true;
	    }
	}
	if (exist) {
	    return key;
	} else {
	    throw new MenuOutException("Menu option out of range.");
	}
	
    }
}
