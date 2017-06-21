package ru.job4j.array;
/**
* Class for reverse array.
*/
public class Turn {
    /**
    * Taking array, and return reversed array.
    * @param array to reverse.
    * @return reversed array.
    */
    public int[] back(int[] array) {
	int tmp;
	int len = array.length - 1;
	for (int i = 0; i < array.length / 2; i++) {
	    tmp = array[len - i];
	    array[len - i] = array[i];
	    array[i] = tmp;
	}
	return array;
    }
}