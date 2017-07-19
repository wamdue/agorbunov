package ru.job4j.array;
import java.util.Arrays;
/**
* Class for remove duplicates in array.
*/
public class ArrayDuplicate {
    /**
    * @param array source array with duplicates.
    * @return array without duplicates.
    */
    public String[] remove(String[] array) {
	int match = 0;
	int id = 0;
	int len = array.length;
	for (int i = 0; i < len - 1; i++) {
	    for (int j = i + 1; j < len - match; j++) {
		if (array[j].equals(array[i])) {
		    match++;
		    if (id == 0) {
			id = len - 1;
		    } else {
			id--;
		    }
		    array[j] = array[id];
		}
	    }
	}
	return Arrays.copyOf(array, len - match);
    }
}