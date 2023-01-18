import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		P11Monkey[] monkey = new P11Monkey[8];
		
		long lcm = 1;
		
		
		
		monkey[0] = new P11Monkey(P11Monkey.MULTIPLY, 19, 7, 2, 3, 57, 58);
		monkey[1] = new P11Monkey(P11Monkey.ADD, 1, 19, 4, 6, 66, 52, 59, 79, 94, 73);
		monkey[2] = new P11Monkey(P11Monkey.ADD, 6, 5, 7, 5, 80);
		monkey[3] = new P11Monkey(P11Monkey.ADD, 5, 11, 5, 2, 82, 81, 68, 66, 71, 83, 75, 97);
		monkey[4] = new P11Monkey(P11Monkey.MULTIPLY, -1, 17, 0, 3, 55, 52, 67, 70, 69, 94, 90);
		monkey[5] = new P11Monkey(P11Monkey.ADD, 7, 13, 1, 7, 69, 85, 89, 91);
		monkey[6] = new P11Monkey(P11Monkey.MULTIPLY, 7, 2, 0, 4, 75, 53, 73, 52, 75);
		monkey[7] = new P11Monkey(P11Monkey.ADD, 2, 3, 1, 6, 94, 60, 79);
		
		
		for(int i = 0; i < monkey.length; i++) {
			lcm *= monkey[i].getTestDivisor();
		}
		
		for(int i = 0; i < monkey.length; i++) {
			monkey[i].setLCM(lcm);
		}

		
		for(int i = 0; i < 10000; i++) {
			for(int j = 0; j < monkey.length; j++) {
				monkey[j].business2(monkey);	
			}
		}
		
		for(int i = 0; i < monkey.length; i++) {
			System.out.println(monkey[i].getInspections());
		}
	}

}