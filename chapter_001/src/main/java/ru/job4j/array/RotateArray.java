package ru.job4j.array;
/**
* Class for manipulating with array.
*/
public class RotateArray {
	/**
	* @param array - source array.
	* @return rotated array.
	*/
	public int[][] rotate(int[][] array) {
        int tmp;
        int len = array.length - 1;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len - i; j++) {
                tmp = array[i][j];
                array[i][j] = array[len - j][i];
                array[len - j][i] = array[len - i][len - j];
                array[len + i][len - j] = array[i][len - j];
                array[j][len] = tmp;
            }
        }
	return array;
    }
}