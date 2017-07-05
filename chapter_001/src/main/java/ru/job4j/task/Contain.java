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
	boolean match = false;
	for (int i = 0; i < mainLen; i++) {
	    if (temp == origin.charAt(i)) {
		if (i + subLen > mainLen) {
		    return false;
		}
		match = true;
		for (int j = 0; j < subLen; j++) {
		    if (origin.charAt(i + j) != sub.charAt(j)) {
			match = false;
			break;
		    }
		}
		if (match) {
		    return true;
		}
	    }
	}
	return false;
    }
}