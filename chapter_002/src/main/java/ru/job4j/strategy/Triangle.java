package ru.job4j.strategy;
/**
* Draw triangle.
*/
public class Triangle implements Shape {
    /**
    * @return triangle as string.
    */
    public String pic() {
	int height = 5;
	String result = "";
	for (int i = 0; i < height / 2 + 1; i++) {
	    for (int j = 0; j < height; j++) {
		if ((j >= (height / 2) - i) && (j <= (height / 2) + i)) {
		    result += "X";
		} else {
		    result += " ";
		}
	    }
	    result += "\n";
	}
	return result;
    }
}