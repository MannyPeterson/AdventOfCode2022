public class Point {

	public int col;

	public int row;

	public Point() {

	}

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getDistanceTo(Point point) {

		return Math.abs(this.row - point.row) + Math.abs(this.col - point.col);

	}

}
