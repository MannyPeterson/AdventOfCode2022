public class AoCPoint<T> {

	private int col;

	private int cols;

	private int row;

	private int rows;

	public AoCPoint(AoCPoint<T> target) {
		this(target.rows, target.cols, target.row, target.col);
	}

	public AoCPoint(int rows, int cols, int row, int col) {
		this.setRow(0);
		this.setRows(0);
		this.setCol(0);
		this.setCols(0);

		if ((rows > 0) && (cols > 0)) {
			this.setRows(rows);
			this.setCols(cols);
			if (this.isInBounds(row, col)) {
				this.setRow(row);
				this.setCol(col);
			}
		}
	}

	public int col() {
		return this.getCol();
	}

	public boolean down() {
		boolean ret = false;
		if (this.isInBounds(this.getRow() + 1, this.getCol())) {
			this.setRow(this.getRow() + 1);
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
		AoCPoint<T> other = (AoCPoint<T>) o;
		if ((this.getRow() != other.getRow()) || (this.getCol() != other.getCol()) || this.getRows() != other.getCols()
				|| (this.getCols() != other.getCols())) {
			return false;
		}
		return true;
	}

	public T getAt(T[][] grid) {
		return grid[this.getRow()][this.getCol()];
	}

	private int getCol() {
		return this.col;
	}

	private int getCols() {
		return this.cols;
	}

	public int getDirectionTo(AoCPoint<T> target) {
		int ret = 0;

		ret = ((int) (270.0
				- Math.toDegrees(Math.atan2(this.getRow() - target.getRow(), this.getCol() - target.getCol())))) % 360;

		return ret;
	}

	public int getDistanceTo(AoCPoint<T> target) {
		int ret = 0;

		// Manhattan Distance
		// ret = Math.abs(this.getRow() - target.getRow()) + Math.abs(this.getCol() -
		// target.getCol());

		// Chebyshev Distance
		ret = Math.max(Math.abs(this.getRow() - target.getRow()), Math.abs(this.getCol() - target.getCol()));

		return ret;

	}

	public AoCPoint<T> getDown() {

		AoCPoint<T> ret = null;

		if (this.isInBounds(this.getRow() + 1, this.getCol())) {
			ret = new AoCPoint<T>(this.getRows(), this.getCols(), this.getRow() + 1, this.getCol());
		}

		return ret;
	}

	public AoCPoint<T> getLeft() {

		AoCPoint<T> ret = null;

		if (this.isInBounds(this.getRow(), this.getCol() - 1)) {
			ret = new AoCPoint<T>(this.getRows(), this.getCols(), this.getRow(), this.getCol() - 1);
		}

		return ret;
	}

	public AoCPoint<T> getRight() {

		AoCPoint<T> ret = null;

		if (this.isInBounds(this.getRow(), this.getCol() + 1)) {
			ret = new AoCPoint<T>(this.getRows(), this.getCols(), this.getRow(), this.getCol() + 1);
		}

		return ret;
	}

	private int getRow() {
		return this.row;
	}

	private int getRows() {
		return this.rows;
	}

	public AoCPoint<T> getUp() {

		AoCPoint<T> ret = null;

		if (this.isInBounds(this.getRow() - 1, this.getCol())) {
			ret = new AoCPoint<T>(this.getRows(), this.getCols(), this.getRow() - 1, this.getCol());
		}

		return ret;
	}

	private boolean isInBounds(int row, int col) {
		boolean ret = false;
		if ((row > -1) && (row < this.getRows()) && (col > -1) && (col < this.getCols())) {
			ret = true;
		}
		return ret;
	}

	public boolean left() {
		boolean ret = false;
		if (this.isInBounds(this.getRow(), this.getCol() - 1)) {
			this.setCol(this.getCol() - 1);
			ret = true;
		}
		return ret;
	}

	public boolean move(int rows, int cols) {
		boolean ret = false;
		if (this.isInBounds(this.getRow() + rows, this.getCol() + cols)) {
			this.setRow(this.getRow() + rows);
			this.setCol(this.getCol() + cols);
			ret = true;
		}
		return ret;
	}

	public boolean right() {
		boolean ret = false;
		if (this.isInBounds(this.getRow(), this.getCol() + 1)) {
			this.setCol(this.getCol() + 1);
			ret = true;
		}
		return ret;
	}

	public int row() {
		return this.getRow();
	}

	public void setCol(int col) {
		if(this.isInBounds(this.getRow(), col)) {
			this.col = col;
		}
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setRow(int row) {
		if(this.isInBounds(row, this.getCol())) {
			this.row = row;
		}
	}

	private void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("[ ");

		ret.append(this.getRow());

		ret.append(", ");

		ret.append(this.getCol());

		ret.append(" ]");

		return ret.toString();
	}

	public boolean up() {
		boolean ret = false;
		if (this.isInBounds(this.getRow() - 1, this.getCol())) {
			this.setRow(this.getRow() - 1);
			ret = true;
		}
		return ret;
	}
}
