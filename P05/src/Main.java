import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {

		AoCFileReader input;

		byte[][] stacks;

		int i;
		int j;
		int k;
		int qty;
		int src;
		int dst;

		input = new AoCFileReader();

		stacks = new byte[9][64];

		i = 0;
		j = 0;
		k = 0;
		qty = 0;
		src = 0;
		dst = 0;

		input.read("input");

		input.parse(10);

		for (i = 7; i >= 0; i--) {
			for (j = 1; j < input.range(i).length; j += 4) {
				Main.stack(stacks, k, input.range(i)[j]);
				k++;
			}
			k = 0;
		}

		input = new AoCFileReader();

		input.read("input");

		input.parse(32, 10);

		for (i = 149; i < input.size(); i += 6) {
			qty = input.getint(i);
			src = input.getint(i + 2);
			dst = input.getint(i + 4);

			System.out.println(qty + " " + src + " " + dst);

			for (j = 0; j < qty; j++) {
				Main.move(stacks, src - 1, dst - 1);
			}
		}

		for (i = 0; i < 9; i++) {
			System.out.print((char) Main.top(stacks, i));
		}

		/** PART II **/
		
		input = new AoCFileReader();
		
		stacks = new byte[9][64];

		i = 0;
		j = 0;
		k = 0;
		qty = 0;
		src = 0;
		dst = 0;

		input.read("input");

		input.parse(10);

		for (i = 7; i >= 0; i--) {
			for (j = 1; j < input.range(i).length; j += 4) {
				Main.stack(stacks, k, input.range(i)[j]);
				k++;
			}
			k = 0;
		}

		input = new AoCFileReader();

		input.read("input");

		input.parse(32, 10);

		for (i = 149; i < input.size(); i += 6) {
			qty = input.getint(i);
			src = input.getint(i + 2);
			dst = input.getint(i + 4);

			System.out.println(qty + " " + src + " " + dst);

			Main.move2(stacks, qty, src - 1, dst - 1);
		}

		for (i = 0; i < 9; i++) {
			System.out.print((char) Main.top(stacks, i));
		}
	}

	static byte top(byte[][] stacks, int stack) {
		int i;
		byte ret;

		i = 0;
		ret = 0;

		for (i = 0; i < stacks[stack].length; i++) {
			if ((stacks[stack][i] > 64) && (stacks[stack][i] < 91) && (stacks[stack][i + 1] == 0)) {
				ret = stacks[stack][i];
				break;
			}
		}

		return ret;
	}
	
	static void move2(byte[][] stacks, int qty, int src, int dst) {
		
		int i;
		int j;
		int start;
		byte pallets[];
		
		i = 0;
		j = 0;
		start = 0;
		pallets = new byte[qty];
		
		for (i = 0; i < stacks[src].length; i++) {
			if ((stacks[src][i] > 64) && (stacks[src][i] < 91) && (stacks[src][i + qty] == 0)) {
				start = i;
				break;
			}
		}
		
		for(i = start; i < stacks[src].length; i++) {
			if ((stacks[src][i] > 64) && (stacks[src][i] < 91)) {
				pallets[j] = stacks[src][i];
				j++;
				stacks[src][i] = 0;
			}
		}
		
		for(i = 0; i < qty; i++) {
			for (j = 0; j < stacks[dst].length; j++) {
				if (stacks[dst][j] == 0) {
					stacks[dst][j] = pallets[i];
					System.out.println("moved " + (char) pallets[i] + " from " + (src + 1) + " to " + (dst + 1));
					break;
				}
			}
		}
	}

	static void move(byte[][] stacks, int src, int dst) {

		int i;
		byte pallet;

		i = 0;
		pallet = 0;

		for (i = 0; i < stacks[src].length; i++) {
			if ((stacks[src][i] > 64) && (stacks[src][i] < 91) && (stacks[src][i + 1] == 0)) {
				pallet = stacks[src][i];
				stacks[src][i] = 0;
				break;
			}
		}

		for (i = 0; i < stacks[dst].length; i++) {
			if (stacks[dst][i] == 0) {
				stacks[dst][i] = pallet;
				break;
			}
		}

		//System.out.println("moved " + (char) pallet + " from " + (src + 1) + " to " + (dst + 1));
	}

	static void stack(byte[][] stacks, int stack, byte pallet) {

		int i;

		i = 0;

		if ((pallet > 64) && (pallet < 91)) {
			for (i = 0; i < stacks[stack].length; i++) {

				if (stacks[stack][i] == 0) {
					stacks[stack][i] = pallet;
					break;
				}

			}
		}

	}
}
