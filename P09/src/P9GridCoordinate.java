
public class P9GridCoordinate {
	enum direction {
		EAST, NONE, NORTH, NORTHEAST, NORTHWEST, SOUTH, SOUTHEAST, SOUTHWEST, WEST
	}

	private int col;
	private int row;

	public P9GridCoordinate() {
		this.row = 0;
		this.col = 0;
	}

	public P9GridCoordinate(int row, int col) {

		this.row = row;
		this.col = col;

	}

	public P9GridCoordinate(P9GridCoordinate coord) {
		this.row = coord.row;
		this.col = coord.col;
	}

	public int getCol() {
		return this.col;
	}

	public int getDistanceTo(P9GridCoordinate target) {
		// Manhattan Distance
		//return Math.abs(this.row - target.row) + Math.abs(this.col - target.col);
		
		// Chebyshev Distance
		return Math.max(Math.abs(this.row - target.row), Math.abs(this.col - target.col));
	}

	public P9GridCoordinate.direction getDirectionTo(P9GridCoordinate target) {
		if ((target.row < this.row) && (target.col < this.col)) {
			return direction.NORTHWEST;
		} else if ((target.row == this.row) && (target.col < this.col)) {
			return direction.WEST;
		} else if ((target.row > this.row) && (target.col < this.col)) {
			return direction.SOUTHWEST;
		} else if ((target.row > this.row) && (target.col == this.col)) {
			return direction.SOUTH;
		} else if ((target.row > this.row) && (target.col > this.col)) {
			return direction.SOUTHEAST;
		} else if ((target.row == this.row) && (target.col > this.col)) {
			return direction.EAST;
		} else if ((target.row < this.row) && (target.col == this.col)) {
			return direction.NORTH;
		} else if ((target.row < this.row) && (target.col > this.col)) {
			return direction.NORTHEAST;
		}
		return direction.NONE;
	}

	public int getRow() {
		return this.row;
	}

	public void moveEast() {

		this.col++;

	}

	public void moveNorth() {

		this.row--;

	}

	public void moveNortheast() {

		this.col++;
		this.row--;

	}

	public void moveNorthwest() {

		this.col--;
		this.row--;

	}

	public void moveSouth() {

		this.row++;

	}

	public void moveSoutheast() {
		this.col++;
		this.row++;
	}

	public void moveSouthwest() {

		this.col--;
		this.row++;
	}

	public void moveWest() {

		this.col--;

	}

	public void setPostion(int row, int col) {

		this.row = row;
		this.col = col;

	}

	public String toString() {
		return new String("[ " + this.row + ", " + this.col + " ]");
	}

}
