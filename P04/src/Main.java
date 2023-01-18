import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {

		AoCFileReader input;
		
		int i;
		int[] section;
		int overlaps;
		
		input = new AoCFileReader();
		
		i = 0;
		section = new int[4];
		overlaps = 0;


		input.read("input");

		input.parse(10, 45, 44);
		
		for(i = 0; i < input.size(); i += 4) {
			section[0] = input.getint(i + 0);
			section[1] = input.getint(i + 1);
			section[2] = input.getint(i + 2);
			section[3] = input.getint(i + 3);
			
			if((section[2] >= section[0]) && (section[3] <= section[1])) {
				overlaps++;
			} else if((section[0] >= section[2]) && (section[1] <= section[3])) {
				overlaps++;
			}
		}
		
		System.out.println(overlaps);
		
		i = 0;
		section = new int[4];
		overlaps = 0;
		
		for(i = 0; i < input.size(); i += 4) {
			section[0] = input.getint(i + 0);
			section[1] = input.getint(i + 1);
			section[2] = input.getint(i + 2);
			section[3] = input.getint(i + 3);
			
			if((section[0] >= section[2]) && (section[0] <= section[3])) {
				overlaps++;
			} else if((section[1] >= section[2]) && (section[1] <= section[3])) {
				overlaps++;
				
			}else if((section[2] >= section[0]) && (section[2] <= section[1])) {
				overlaps++;
			} else if((section[3] >= section[0]) && (section[3] <= section[1])) {
				overlaps++;
			}
		}
		
		System.out.println(overlaps);
		
		
	}
}
