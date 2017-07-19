package ru.job4j.loop;
/**
* Class for paint ChessBoard.
*/
public class Board {
    /**
    * @param width - chessboard width.
    * @param height - chessboard height.
    * @return result - filled chessboard.
    */
    public String paint(int width, int height) {
	if (width > 0 && height > 0) {
	    StringBuilder sb = new StringBuilder();
	    for (int y = 0; y < height; y++) {
		for (int x = 0; x < width; x++) {
		    if ((y + x) % 2 == 0) {
			sb.append("X");
		    } else {
		        sb.append(" ");
		    }
		}
		sb.append("\n");
	    }
	    return sb.toString();
	}
	return " ";
    }
}