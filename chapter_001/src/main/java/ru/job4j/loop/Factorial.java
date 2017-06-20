package ru.job4j.loop;
/**
* Class for calculating factorial.
*/
public class Factorial {
    /**
    * @param n factorial.
    * @return result.
    */
    public int calc(int n) {
	int result = 1;
	if (n == 0 || n < 0) {
	    return 1;
	}
	for (int i = 1; i <= n; i++) {
	    result *= i;
	}
	return result;
    }
}