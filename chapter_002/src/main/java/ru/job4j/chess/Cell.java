package ru.job4j.chess;
/**
* Cell of the playing board.
*/
public class Cell {
	/**
	* Horizontal position.
	*/
	private int x;
	/**
	* Vertical position.
	*/
	private int y;
	/**
	* Main constructor.
	 * @param x - position by axis x.
	 * @param y - position by axis y.
	*/
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	* @return current x.
	*/
	public int getX() {
		return x;
	}
	/**
	* @return current y.
	*/
	public int getY() {
		return y;
	}
	/**
	* @param x - get new horizontal position.
	*/
	public void setX(int x) {
		this.x = x;
	}
	/**
	* @param y - get new vertical position.
	*/
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return String.format("x = %d, y = %d", this.x, this.y);
	}
	@Override
	public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return x == cell.x && y == cell.y;
    }

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
