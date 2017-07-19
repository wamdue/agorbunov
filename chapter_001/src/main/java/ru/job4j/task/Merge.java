package ru.job4j.task;
/**
* Class for work with arrays.
*/
public class Merge {
    /**
    * @param first - first sorted array .
    * @param second - second sorted array.
    * @return - merged array.
    */
    public int[] merge(int[] first, int[] second) {
	int firstLen = first.length;
	int secondLen = second.length;
	int[] result = new int[firstLen + secondLen];
	if (first[firstLen - 1] <= second[0]) {
	    System.arraycopy(first, 0, result, 0, firstLen);
	    System.arraycopy(second, 0, result, firstLen, secondLen);
	} else {
	    int firstIndex = 0;
	    int secondIndex = 0;
	    int resultIndex = 0;
	    while (true) {
		if (firstIndex == firstLen) {
		    System.arraycopy(second, secondIndex, result, resultIndex, secondLen - secondIndex);
		    resultIndex = result.length;
		} else if (secondIndex == secondLen) {
		    System.arraycopy(first, firstIndex, result, resultIndex, firstLen - firstIndex);
		    resultIndex = result.length;
	    	}
		if (resultIndex == result.length) {
		    break;
		}
		if (first[firstIndex] < second[secondIndex]) {
		    result[resultIndex++] = first[firstIndex++];
		} else if (first[firstIndex] == second[secondIndex]) {
		    result[resultIndex++] = first[firstIndex++];
		    result[resultIndex++] = second[secondIndex++];
		} else {
		    result[resultIndex++] = second[secondIndex++];
		}
	    }
        }
	return result;
    }
}