package ru.job4j.max;
/**
* Класс для поиска максимального значения.
*/
public class Max {
	/**
	* @param first - first value to compare.
	* @param second - second value to compare.
	* @return max value.
	*/
	public int max(int first, int second) {
		return first > second ? first : first == second ? first : second;
	}
}
