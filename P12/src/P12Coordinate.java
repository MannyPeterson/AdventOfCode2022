public class P12Coordinate {

	enum direction {
		EAST, NONE, NORTH, NORTHEAST, NORTHWEST, SOUTH, SOUTHEAST, SOUTHWEST, WEST
	}

	int col;
	int cols;
	int row;
	int rows;
	
	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		
		P12Coordinate target = (P12Coordinate) o;
		
		if((this.row == target.row) && (this.col == target.col)) {
			ret = true;
		}
		
		return ret;
	}

	public P12Coordinate(P12Coordinate target) {
		this.row = target.row;
		this.col = target.col;
		this.rows = target.rows;
		this.cols = target.cols;
	}
	public P12Coordinate(int rows, int cols, int row, int col) {
		this.row = row;
		this.col = col;
		this.rows = rows;
		this.cols = cols;
	}

	public int col() {
		return this.col;
	}

	public int getDirectionTo(P12Coordinate target) {
		int ret = 0;

		ret = ((int) (270.0 - Math.toDegrees(Math.atan2(this.row - target.row, this.col - target.col)))) % 360;

		return ret;
	}

	public int getMapAt(int[][] map) {
		return map[this.row][this.col];
	}

	public P12Coordinate[] getNeighbors() {

		P12Coordinate[] ret = new P12Coordinate[4];

		if (this.inBounds(this.row - 1, this.col)) {
			ret[0] = new P12Coordinate(this.rows, this.cols, this.row - 1, this.col);
		}

		if (this.inBounds(this.row, this.col + 1)) {
			ret[1] = new P12Coordinate(this.rows, this.cols, this.row, this.col + 1);
		}

		if (this.inBounds(this.row + 1, this.col)) {
			ret[2] = new P12Coordinate(this.rows, this.cols, this.row + 1, this.col);
		}

		if (this.inBounds(this.row, this.col - 1)) {
			ret[3] = new P12Coordinate(this.rows, this.cols, this.row, this.col - 1);
		}

		return ret;
	}

	/*
	 * public direction getDirectionTo(P12Coordinate target) { if ((target.row <
	 * this.row) && (target.col < this.col)) { return direction.NORTHWEST; } else if
	 * ((target.row == this.row) && (target.col < this.col)) { return
	 * direction.WEST; } else if ((target.row > this.row) && (target.col <
	 * this.col)) { return direction.SOUTHWEST; } else if ((target.row > this.row)
	 * && (target.col == this.col)) { return direction.SOUTH; } else if ((target.row
	 * > this.row) && (target.col > this.col)) { return direction.SOUTHEAST; } else
	 * if ((target.row == this.row) && (target.col > this.col)) { return
	 * direction.EAST; } else if ((target.row < this.row) && (target.col ==
	 * this.col)) { return direction.NORTH; } else if ((target.row < this.row) &&
	 * (target.col > this.col)) { return direction.NORTHEAST; } return
	 * direction.NONE; }
	 */

	private boolean inBounds(int row, int col) {
		boolean ret = false;
		if ((row > -1) && (row < this.rows) && (col > -1) && (col < this.cols)) {
			ret = true;
		}
		return ret;
	}

	public int row() {
		return this.row;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("[ ");

		ret.append(this.row);

		ret.append(", ");

		ret.append(this.col);

		ret.append(" ]");

		return ret.toString();
	}
}
