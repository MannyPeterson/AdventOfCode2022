import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		AoCFileParser input = null;

		int rows = 0;
		int cols = 0;
		int col = 0;
		int row = 0;
		byte[][] forrest = null;
		int visible = 0;
		int scenicscore = 0;

		input = new AoCFileParser();

		input.readFile("input");

		input.parseTokens(10);

		cols = input.getToken(0).length;
		rows = input.getTokenCount();

		visible += (cols * 2);
		visible += (rows * 2);

		visible -= 4;

		forrest = new byte[rows][cols];

		for (row = 0; row < rows; row++) {
			forrest[row] = input.getToken(row);
		}

		for (row = 1; row < (rows - 1); row++) {
			for (col = 1; col < (cols - 1); col++) {
				if (Main.isVisible(forrest, row, col) == true) {
					visible++;
				}
			}
		}

		System.out.println(visible);

		for (row = 1; row < (rows - 1); row++) {
			for (col = 1; col < (cols - 1); col++) {
				if (scenicscore < getScenicScore(forrest, row, col)) {
					scenicscore = getScenicScore(forrest, row, col);
				}
			}
		}

		 //System.out.println(getScenicScore(forrest, 1,1));
		 //System.out.println(getScenicScore(forrest, 2,2));

		System.out.println(scenicscore);

	}

	static int toInt(byte b) {
		return (int) (b - 48);
	}

	static int getScenicScore(byte[][] forrest, int trow, int tcol) {

		int row = 0;
		int col = 0;
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;

		up = trow;
		for (row = trow; row > -1; row--) { /* ROWS UP! */
			if ((row != trow) && (toInt(forrest[row][tcol]) >= toInt(forrest[trow][tcol]))) {
				up = trow - row;
				break;
			}
		}

		down = (forrest.length - 1) - trow;
		for (row = trow; row < forrest.length; row++) { /* ROWS DOWN! */
			if ((row != trow) && (toInt(forrest[row][tcol]) >= toInt(forrest[trow][tcol]))) {
				down = row - trow;
				break;
			}
		}

		left = tcol;
		for (col = tcol; col > -1; col--) { /* COLS LEFT! */
			if ((col != tcol) && (toInt(forrest[trow][col]) >= toInt(forrest[trow][tcol]))) {
				left = tcol - col;
				break;
			}
		}

		right = (forrest[0].length - 1) - tcol;
		for (col = tcol; col < forrest[0].length; col++) { /* COLS RIGHT! */
			if ((col != tcol) && (toInt(forrest[trow][col]) >= toInt(forrest[trow][tcol]))) {
				right = col - tcol;
				break;
			}
		}


		System.out.println(" R:" + trow + " C:" + tcol + " T:" + ((int) (forrest[trow][tcol] - 48))+ " U:" + up + " D:" + down + " L:" + left + " R:" + right);
		return up * down * left * right;
	}

	static boolean isVisible(byte[][] forrest, int trow, int tcol) {

		int sides = 4;

		int col = 0;
		int row = 0;

		for (row = 0; row < trow; row++) {
			if (((int) (forrest[row][tcol] - 48)) >= ((int) (forrest[trow][tcol] - 48))) {
				sides--;
				break;
			}
		}

		for (row = (trow + 1); row < forrest.length; row++) {
			if (((int) (forrest[row][tcol] - 48)) >= ((int) (forrest[trow][tcol] - 48))) {
				sides--;
				break;
			}
		}

		for (col = 0; col < tcol; col++) {
			if (((int) (forrest[trow][col] - 48)) >= ((int) (forrest[trow][tcol] - 48))) {
				sides--;
				break;
			}
		}

		for (col = (tcol + 1); col < forrest[0].length; col++) {
			if (((int) (forrest[trow][col] - 48)) >= ((int) (forrest[trow][tcol] - 48))) {
				sides--;
				break;
			}
		}

		if (sides > 0) {
			return true;
		}

		return false;
	}

}