package ru.job4j.strategy;
/**
* Draw square as string.
*/
public class Square implements Shape {
    /**
    * @return square as string.
    */
    public String pic() {
	String s = "";
	for (int i = 0; i < 5; i++) {
	    s+= "XXXXX\n";
	}
	return s;
    }
}