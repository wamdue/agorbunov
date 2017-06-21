package ru.job4j.array;
/**
* Buble sort class.
*/
public class BubleSort {
    /**
    * @param array - array for sort.
    * @return sorted array.
    */
    public int[] sort(int[] array) {
	int len = array.length - 1;
	int tmp;
	boolean isChanged = true;
	while (isChanged) {
	    isChanged = false;
	    for (int i = 0; i < len; i++) {
		if (array[i] > array[i + 1]) {
		    tmp = array[i];
		    array[i] = array[i + 1];
		    array[i + 1] = tmp;
		    isChanged = true;
		}
	    }
	}
	return array;
    }
}