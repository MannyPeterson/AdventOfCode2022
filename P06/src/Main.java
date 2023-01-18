import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {

		AoCFileReader input;

		input = new AoCFileReader();

		input.read("input");

		int i;
		
		i = 0;
		
		for(i = 0; i < (input.bufferlength() - 4); i++) {
			System.out.println(new String(input.buffer(i, i +4)));
			if(Main.ismaker(input.buffer(i, i + 4))) {
				System.out.println(i + 4);
				break;
			}
		}
		
		for(i = 0; i < (input.bufferlength() - 14); i++) {
			System.out.println(new String(input.buffer(i, i +14)));
			if(Main.ismaker(input.buffer(i, i + 14))) {
				System.out.println(i + 14);
				break;
			}
		}

	}

	
	static boolean ismaker(byte[] buffer) {
		
		int i;
		int j;
		
		i = 0;
		j = 0;
		
		for(i = 0; i < buffer.length; i++) {
			for(j = 0; j < buffer.length; j++) {
				if((i != j) && (buffer[i] == buffer[j])) {
					return false;
				}
			}
		}
		
		return true;
	}
}