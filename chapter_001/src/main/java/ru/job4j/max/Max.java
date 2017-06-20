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
    /**
    * @param first - first value to compare.
    * @param second - second value to compare.
    * @param third - third value to compare.
    * @return max value of three values.
    */
    public int max(int first, int second, int third) {
	int temp = max(first, second);
	int result = max(temp, third);
	return result;
    }
}
