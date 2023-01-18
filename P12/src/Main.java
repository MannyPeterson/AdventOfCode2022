import java.io.IOException;
import java.util.ArrayList;

public class Main {
/*
	public static void main(String[] args) throws IOException {

		AoCFileParser input = null;
		int rows = 0;
		int cols = 0;
		int[][] map = null;
		P12Coordinate start = null;
		P12Coordinate end = null;
		input = new AoCFileParser();

		input.readFile("input");

		input.parseTokens(10);

		rows = input.getTokenCount();
		cols = input.getToken(0).length;

		map = new int[rows][cols];

		for (int row = 0; row < rows; row++) {
			map[row] = new int[cols];
			for (int col = 0; col < cols; col++) {
				char chr = (char) input.getTokenAt(row, col);
				if (chr == 'S') {
					start = new P12Coordinate(rows, cols, row, col);
					map[row][col] = 0;
				} else if (chr == 'E') {
					end = new P12Coordinate(rows, cols, row, col);
					map[row][col] = 27;
				} else {
					map[row][col] = (int) (input.getTokenAt(row, col) - 96);
				}
			}
		}

		P12BestPath child = new P12BestPath();
		
		child.find(map, start, end, null);

		
		child.poo();
	}
*/
}