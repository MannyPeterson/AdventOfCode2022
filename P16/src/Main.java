import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		AoCFileParser input = new AoCFileParser();
		try {
			input.readFile("input");
		} catch (IOException e) {
			e.printStackTrace();
		}

		input.parseTokens(10);		
	}
}
