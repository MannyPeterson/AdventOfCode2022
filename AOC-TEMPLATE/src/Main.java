import java.io.IOException;

public class Main {


	public static void main(String[] args) throws IOException {

		AoCFileParser input = null;
	

		input = new AoCFileParser();

		input.readFile("input");

		input.parseTokens(10);

	}

	
}