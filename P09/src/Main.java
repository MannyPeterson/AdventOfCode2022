import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		AoCFileParser input = new AoCFileParser();
		P9Grid grid = new P9Grid();

		input.readFile("input");

		input.parseTokens(32, 10);

		for (int i = 0; i < input.getTokenCount(); i += 2) {
			//grid.applyMovePart1(input.getTokenAt(i, 0), input.getTokenAsInt(i + 1));
		}
		
		//grid.printResults();
		
		grid = new P9Grid();
		
		for (int i = 0; i < input.getTokenCount(); i += 2) {
			grid.applyMovePart2(input.getTokenAt(i, 0), input.getTokenAsInt(i + 1));
		}
		
		grid.printResults();
	}

}