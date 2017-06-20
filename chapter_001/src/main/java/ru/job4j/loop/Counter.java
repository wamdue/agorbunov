package ru.job4j.loop;
/**
* Class for summ even numbers.
*/
public class Counter {
    /**
    * Counting all even numbers.
    * @param start - from number.
    * @param finish - last number.
    * @return result - sum of even numbers.
    */
    public int add(int start, int finish) {
	int result = 0;
	if (start > finish || start == finish) {
	    return 0;
	}
	for (int i = start; i <= finish; i++) {
	    if (i % 2 == 0) {
		result += i;
	    }
	}
	return result;
    }
}