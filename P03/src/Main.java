import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		FileMuncher munch = new FileMuncher();

		byte[] dups;
		int i;
		int j;
		int priority_sum;
		
		dups = null;
		
		priority_sum = 0;

		munch.read("input");

		munch.parse(10);
		

		for (i = 0; i < munch.getSize(); i++) {

			dups = Main.finddups(munch.getRange(i));
			
			for(j = 0; j < dups.length; j++) {
				priority_sum += Main.priority(dups[j]);
			}
		}
		
		System.out.println(priority_sum);

		
		priority_sum = 0;
		
		munch = new FileMuncher();
		
		munch.read("input");
		
		munch.parse(10);
		
		for(i = 0; i < munch.getSize(); i += 3) {
			
			dups = Main.finddups2(munch.getRange(i), munch.getRange(i + 1));
			
			dups = Main.finddups2(munch.getRange(i + 2), dups);
			
			priority_sum += Main.priority(dups[0]);
		}
		
		System.out.println(priority_sum);
		
	}

	static byte[] finddups(byte[] rucksack) {

		int compartment_size;
		byte[] dups = new byte[64];
		byte[] ret;

		int i;
		int j;
		int k;
		int l;

		l = 0;
		
		for(i = 0; i < 64; i++) {
			dups[i] = 0;
		}

		compartment_size = rucksack.length / 2;

		for (i = 0; i < compartment_size; i++) {
			for (j = 0; j < compartment_size; j++) {
				if (rucksack[i] == rucksack[compartment_size + j]) {
					for (k = 0; k < 64; k++) {
						if (dups[k] == rucksack[i]) {
							break;
						} else if (dups[k] == 0) {
							dups[k] = rucksack[i];
							l++;
							break;
						}
					}
				}
			}
		}

		ret = new byte[l];

		for (i = 0; i < l; i++) {
			ret[i] = dups[i];
		}

		return ret;

	}

	static byte[] finddups2(byte[] rucksack1, byte[] rucksack2) {
	
		
		byte[] dups = new byte[64];
		byte[] ret;

		int i;
		int j;
		int k;
		int l;

		l = 0;
		
		for(i = 0; i < 64; i++) {
			dups[i] = 0;
		}


		for (i = 0; i < rucksack1.length; i++) {
			for (j = 0; j < rucksack2.length; j++) {
				if (rucksack1[i] == rucksack2[j]) {
					for (k = 0; k < 64; k++) {
						if (dups[k] == rucksack1[i]) {
							break;
						} else if (dups[k] == 0) {
							dups[k] = rucksack1[i];
							l++;
							break;
						}
					}
				}
			}
		}

		ret = new byte[l];

		for (i = 0; i < l; i++) {
			ret[i] = dups[i];
		}

		return ret;
		
	}
	
	static int priority(byte item) {

		int ret;

		ret = 0;

		if (item > 96) { /* LOWER */
			ret = item - 96;
		} else if (item > 64) { /* UPPER */
			ret = item - 64 + 26;
		}

		return ret;
	}
}
