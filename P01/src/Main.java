import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		Muncher munch = new Muncher();

		munch.read("input");

		munch.parse(10);

		int length = munch.getRanges();

		int most_calories;
		int curr_calories;
		int i;
		
		most_calories = 0;
		curr_calories = 0;
		
		
		for(i = 0; i < length; i++) {

			if(munch.getRange(i).length == 0) {
				if(curr_calories > most_calories) {
					most_calories = curr_calories;
				}
				curr_calories = 0;
			} else {
				curr_calories += munch.getRangeAsInt(i);
			}
			
		}
		
		System.out.println(most_calories);
		
		int[] top_three = new int[3];
		int j;
		
		munch = new Muncher();
		
		munch.read("input");
		
		munch.parse(10);
		curr_calories = 0;
		
		j = 0;
		
		for(i = 0; i < length; i++) {

			if(munch.getRange(i).length == 0) {
				for(j = 0; j < 3; j++) {
					if(curr_calories > top_three[j]) {
						top_three[j] = curr_calories;
						break;
					}
				}
				curr_calories = 0;
			} else {
				curr_calories += munch.getRangeAsInt(i);
			}
			
		}
		
		System.out.println(top_three[0]  + top_three[1]  + top_three[2]);
		
	}

}
