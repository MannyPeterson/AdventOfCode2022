public class AoCCoordinate<T> {

	int col;
	int cols;
	int row;
	int rows;

	public AoCCoordinate(AoCCoordinate<T> target) {
		this(target.rows, target.cols, target.row, target.col);
	}

	public AoCCoordinate(int rows, int cols, int row, int col) {
		this.rows = 0;
		this.cols = 0;
		this.row = 0;
		this.col = 0;
		if ((rows > 0) && (cols > 0)) {
			this.rows = rows;
			this.cols = cols;
			if (this.isInBounds(row, col)) {
				this.row = row;
				this.col = col;
			}
		}
	}

	public int col() {
		return this.col;
	}

	public boolean down() {
		boolean ret = false;
		if (this.isInBounds(this.row + 1, this.col)) {
			this.row++;
			ret = true;
		}
		return ret;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		AoCCoordinate<T> other = (AoCCoordinate<T>) o;
		if ((this.row != other.row) || (this.col != other.col)) {
			return false;
		}
		return true;
	}

	public T getAt(T[][] grid) {
		return grid[this.row][this.col];
	}

	public int getDirectionTo(AoCCoordinate<T> target) {
		int ret = 0;

		ret = ((int) (270.0 - Math.toDegrees(Math.atan2(this.row - target.row, this.col - target.col)))) % 360;

		return ret;
	}

	public int getDistanceTo(AoCCoordinate<T> target) {
		int ret = 0;

		// Manhattan Distance
		// ret = Math.abs(this.row - target.row) + Math.abs(this.col - target.col);

		// Chebyshev Distance
		ret = Math.max(Math.abs(this.row - target.row), Math.abs(this.col - target.col));

		return ret;

	}

	public AoCCoordinate<T> getDown() {

		AoCCoordinate<T> ret = null;

		if (this.isInBounds(this.row + 1, this.col)) {
			ret = new AoCCoordinate<T>(this.rows, this.cols, this.row + 1, this.col);
		}

		return ret;
	}

	public AoCCoordinate<T> getLeft() {

		AoCCoordinate<T> ret = null;

		if (this.isInBounds(this.row, this.col - 1)) {
			ret = new AoCCoordinate<T>(this.rows, this.cols, this.row, this.col - 1);
		}

		return ret;
	}

	public AoCCoordinate<T> getRight() {

		AoCCoordinate<T> ret = null;

		if (this.isInBounds(this.row, this.col + 1)) {
			ret = new AoCCoordinate<T>(this.rows, this.cols, this.row, this.col + 1);
		}

		return ret;
	}

	public AoCCoordinate<T> getUp() {

		AoCCoordinate<T> ret = null;

		if (this.isInBounds(this.row - 1, this.col)) {
			ret = new AoCCoordinate<T>(this.rows, this.cols, this.row - 1, this.col);
		}

		return ret;
	}

	private boolean isInBounds(int row, int col) {
		boolean ret = false;
		if ((row > -1) && (row < this.rows) && (col > -1) && (col < this.cols)) {
			ret = true;
		}
		return ret;
	}

	public boolean left() {
		boolean ret = false;
		if (this.isInBounds(this.row, this.col - 1)) {
			this.col--;
			ret = true;
		}
		return ret;
	}

	public boolean move(int rows, int cols) {
		boolean ret = false;
		if (this.isInBounds(this.row + rows, this.col + cols)) {
			this.row += rows;
			this.col += cols;
			ret = true;
		}
		return ret;
	}

	public boolean right() {
		boolean ret = false;
		if (this.isInBounds(this.row, this.col + 1)) {
			this.col++;
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

	public boolean up() {
		boolean ret = false;
		if (this.isInBounds(this.row - 1, this.col)) {
			this.row--;
			ret = true;
		}
		return ret;
	}
}
