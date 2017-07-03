package ru.job4j.task;
/**
* Class for work with strings.
*/
public class Contain {
    /**
    * Method checks, if one string contains substring.
    * @param origin - main string.
    * @param sub - what we want to find in main string.
    * @return - true if origin containts sub, otherwise false.
    */
    public boolean contains(String origin, String sub) {
	char temp = sub.charAt(0);
	int mainLen = origin.length();
	int subLen = sub.length();
	for (int i = 0; i < mainLen; i++) {
	    if (temp == origin.charAt(i)) {
		if (i + subLen > mainLen) {
		    return false;
		} else if (origin.substring(i, subLen + 1).equals(sub)) {
		    return true;
		} else {
		    continue;
		}
	    }
	}
	return false;
    }
}