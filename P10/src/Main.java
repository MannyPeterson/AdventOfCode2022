import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		AoCFileParser input = new AoCFileParser();
		P10Instruction[] instruction = new P10Instruction[1000];
		P10Machine machine = new P10Machine();
		int instructions = 0;
		input.readFile("/workspaces/AdventOfCode2022/P10/input");

		input.parseTokens(32, 10);


		
		for(int i = 0; i < input.getTokenCount(); i++) {
			instruction[instructions] = new P10Instruction(input.getToken(i), input.getToken(i + 1));	
			System.out.println(input.getTokenAsString(i));
			if(instruction[instructions].getOpcode() == P10Instruction.ADDX) {
				i++;
			}
			instructions++;
		}
		
		for(int i = 0; i < instructions; i++) {
			machine.execute(instruction[i]);
		}
		
		System.out.println(machine.getSum());
		
		
		machine.rednerCRT();
	}

}