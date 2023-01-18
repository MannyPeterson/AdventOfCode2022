
public class P9Grid {
	public final static int GRID_COLS = 10000;
	public final static int GRID_ROWS = 10000;
	public final static int TAIL_DIST = 2;
	public final static int KNOTS = 10;
	int grid[][];
	P9GridCoordinate head;
	P9GridCoordinate tail;
	P9GridCoordinate[] knot;

	public P9Grid() {
		this.head = new P9GridCoordinate(P9Grid.GRID_ROWS / 2, P9Grid.GRID_COLS / 2);
		this.tail = new P9GridCoordinate(P9Grid.GRID_ROWS / 2, P9Grid.GRID_COLS / 2);

		this.grid = new int[P9Grid.GRID_ROWS][P9Grid.GRID_COLS];

		for (int row = 0; row < P9Grid.GRID_ROWS; row++) {
			for (int col = 0; col < P9Grid.GRID_COLS; col++) {
				this.grid[row][col] = 0;
			}
		}

		this.knot = new P9GridCoordinate[P9Grid.KNOTS];

		for (int i = 0; i < P9Grid.KNOTS; i++) {
			this.knot[i] = new P9GridCoordinate(P9Grid.GRID_ROWS / 2, P9Grid.GRID_COLS / 2);
		}

		this.grid[this.tail.getRow()][this.tail.getCol()] = 1;
	}

	public void printResults() {
		int visited = 0;
		for (int row = 0; row < P9Grid.GRID_ROWS; row++) {
			for (int col = 0; col < P9Grid.GRID_COLS; col++) {
				if (this.grid[row][col] == 1) {
					visited++;
				}
			}
		}

		System.out.println(visited);
	}

	public void applyMovePart1(byte direction, int distance) {

		for (int i = distance; i > 0; i--) {
			System.out.print("[" + (char) direction + ":" + i + "]");

			if ((char) direction == 'U') {
				this.head.moveNorth();

			} else if ((char) direction == 'D') {
				this.head.moveSouth();

			} else if ((char) direction == 'L') {
				this.head.moveWest();

			} else if ((char) direction == 'R') {
				this.head.moveEast();

			}

			if (this.tail.getDistanceTo(this.head) >= P9Grid.TAIL_DIST) {
				if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.NORTH) {
					this.tail.moveNorth();
					System.out.println(" -> N");
				} else if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.NORTHEAST) {
					this.tail.moveNortheast();
					System.out.println(" -> NE");
				} else if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.EAST) {
					this.tail.moveEast();
					System.out.println(" -> E");
				} else if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.SOUTHEAST) {
					this.tail.moveSoutheast();
					System.out.println(" -> SE");
				} else if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.SOUTH) {
					this.tail.moveSouth();
					System.out.println(" -> S");
				} else if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.SOUTHWEST) {
					this.tail.moveSouthwest();
					System.out.println(" -> SW");
				} else if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.WEST) {
					this.tail.moveWest();
					System.out.println(" -> W");
				} else if (this.tail.getDirectionTo(this.head) == P9GridCoordinate.direction.NORTHWEST) {
					this.tail.moveNorthwest();
					System.out.println(" -> NW");
				}
				this.grid[this.tail.getRow()][this.tail.getCol()] = 1;
			}

		}

	}

	public void applyMovePart2(byte direction, int distance) {

		for (int i = distance; i > 0; i--) {

			if ((char) direction == 'U') {
				this.knot[0].moveNorth();

			} else if ((char) direction == 'D') {
				this.knot[0].moveSouth();

			} else if ((char) direction == 'L') {
				this.knot[0].moveWest();

			} else if ((char) direction == 'R') {
				this.knot[0].moveEast();

			}

			for (int j = 1; j < P9Grid.KNOTS; j++) {
				if (this.knot[j].getDistanceTo(this.knot[j - 1]) >= P9Grid.TAIL_DIST) {
					if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.NORTH) {
						this.knot[j].moveNorth();
					} else if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.NORTHEAST) {
						this.knot[j].moveNortheast();
					} else if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.EAST) {
						this.knot[j].moveEast();
					} else if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.SOUTHEAST) {
						this.knot[j].moveSoutheast();
					} else if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.SOUTH) {
						this.knot[j].moveSouth();
					} else if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.SOUTHWEST) {
						this.knot[j].moveSouthwest();
					} else if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.WEST) {
						this.knot[j].moveWest();
					} else if (this.knot[j].getDirectionTo(this.knot[j - 1]) == P9GridCoordinate.direction.NORTHWEST) {
						this.knot[j].moveNorthwest();
					}
					if(j == 9) {
						this.grid[this.knot[j].getRow()][this.knot[j].getCol()] = 1;
					}

				}

			}

		}

	}
}
