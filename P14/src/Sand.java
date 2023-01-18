import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;

public class Sand extends PApplet {

	private static final int AIR = 0;
	private static final int COL_OFFSET = 0;
	private static final int COLS = 1000;
	private static final int ROCK = 1;
	private static final int ROW_OFFSET = 0;
	private static final int ROWS = 1200;
	private static final int SAND = 2;

	private int rest = 0;
	
	private int highest_y = 0;
	
	private int started = 0;

	public static void main(String[] args) {
		PApplet.main("Sand");
	}

	private Integer[][] cave;

	private AoCCoordinate<Integer> particle;

	public Sand() {

		cave = new Integer[ROWS][COLS];
		particle = new AoCCoordinate<Integer>(ROWS, COLS, 0, 500);

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				cave[row][col] = AIR;
			}
		}

	}

	public void draw() {
		if(started == 1) {
			for (int i = 0; i < 400; i++) {
				update();
				if(cave[0][500] == SAND) {
					System.out.println(rest);
					System.exit(0);
				}
			}
			refresh();
		}
	}
	
	public void mouseClicked() {
		started = 1;
	}

	public void refresh() {
		clear();
		stroke(0, 0, 0);
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (this.cave[row][col] == AIR) {

				} else if (this.cave[row][col] == ROCK) {
					fill(255, 255, 255);
					rect((col - COL_OFFSET) * 5, (row - ROW_OFFSET) * 5, 4, 4);
				} else if (this.cave[row][col] == SAND) {
					fill(252, 186, 3);
					rect((col - COL_OFFSET) * 5, (row - ROW_OFFSET) * 5, 4, 4);
				}

			}
		}
		fill(252, 186, 3);
		rect((particle.col() - COL_OFFSET) * 5, (particle.row() - ROW_OFFSET) * 5, 4, 4);
	}

	public void rock(AoCCoordinate<Integer> start, AoCCoordinate<Integer> end) {

		if (start.col() == end.col()) {
			int col = start.col();
			if(start.row() < end.row()) {
				System.out.print("V " + start + " < " + end);
				for (int row = start.row(); row <= end.row(); row++) {
					System.out.print(".");
					cave[row][col] = ROCK;
				}
			} else {
				System.out.print("V " + start + " > " + end);
				for (int row = start.row(); row >= end.row(); row--) {
					System.out.print(".");
					cave[row][col] = ROCK;
				}
			}


		} else if (start.row() == end.row()) {
			int row = start.row();
			if(start.col() < end.col()) {
				System.out.print("H " + start + " < " + end);
				for(int col = start.col(); col <= end.col(); col++) {
					System.out.print(".");
					cave[row][col] = ROCK;
				}
			} else {
				System.out.print("H " + start + " > " + end);
				for(int col = start.col(); col >= end.col(); col--) {
					System.out.print(".");
					cave[row][col] = ROCK;
				}
			}

		}
		
		System.out.println();

	}

	public void s(String string) {
		System.out.println(string);
	}

	public void settings() {
		size(Sand.COLS, Sand.ROWS);
	}

	public void setup() {
		AoCFileParser input = new AoCFileParser();
		ArrayList<AoCCoordinate<Integer>> points = new ArrayList<AoCCoordinate<Integer>>();

		try {
			input.readFile("input");
		} catch (IOException e) {
			e.printStackTrace();
		}

		input.bufferDrop(32, 62);

		input.parseTokens(10, 44, 45);

		int row = 0;
		int col = 0;

		for (int i = 0; i < input.getTokenCount(); i++) {
			if (input.getTokenDelimeter(i) == 44) { /** COMMA **/
				col = input.getTokenAsInt(i);
			} else if (input.getTokenDelimeter(i) == 45) { /** HYPHEN **/
				row = input.getTokenAsInt(i);
				points.add(new AoCCoordinate<Integer>(Sand.ROWS, Sand.COLS, row, col));
			} else if (input.getTokenDelimeter(i) == 10) { /** NEW LINE **/
				row = input.getTokenAsInt(i);
				points.add(new AoCCoordinate<Integer>(Sand.ROWS, Sand.COLS, row, col));
				AoCCoordinate<Integer> last_point = null;
				for (AoCCoordinate<Integer> point : points) {
					if(point.row() > highest_y) {
						highest_y = point.row();
					}
					if (last_point == null) {
						last_point = point;
					} else {
						rock(last_point, point);
						last_point = point;
					}
				}
				points = new ArrayList<AoCCoordinate<Integer>>();
			}
		}

		int floor_row = highest_y + 2;
		
	
		
		rock(new AoCCoordinate<Integer>(ROWS, COLS, floor_row, 0),new AoCCoordinate<Integer>(ROWS, COLS, floor_row, COLS - 1));
	}

	public void update() {

		AoCCoordinate<Integer> temp = new AoCCoordinate<Integer>(particle);
		temp.move(1, 0);
		if (temp.getAt(cave) == AIR) {
			particle.move(1, 0);
			return;
		} else {
			temp = new AoCCoordinate<Integer>(particle);
			temp.move(1, -1);
			if (temp.getAt(cave) == AIR) {
				particle.move(1, -1);
				return;
			} else {
				temp = new AoCCoordinate<Integer>(particle);
				temp.move(1, 1);
				if (temp.getAt(cave) == AIR) {
					particle.move(1, 1);
					return;
				} else {
					cave[particle.row()][particle.col()] = SAND;
					particle.setCol(500);
					particle.setRow(0);
					rest++;
					//s(Integer.toString(rest));

				}

			}
		}
	}
}
