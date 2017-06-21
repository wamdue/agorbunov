package ru.job4j.loop;
/**
* Painting piramid.
*/
public class Paint {
    /**
    * @param n height of piramid.
    * @return simple picture of piramid.
    */
    public String piramid(int n) {
	if (n > 1) {
	    int width = (n * 2) - 1;
	    int shift = n - 1;
	    StringBuilder sb = new StringBuilder();
	    for (int y = 0; y < n; y++) {
		for (int x = 1; x <= width; x++) {
		    if (x > shift && x < (width + 1) - shift) {
			sb.append("^");
		    } else {
			sb.append(" ");
		    }
		}
		shift--;
		sb.append("\n");
	    }
	    return sb.toString();
	}
	return "^";
    }
}