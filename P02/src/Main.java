import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		FileMuncher munch = new FileMuncher();

		munch.read("input");

		munch.parse(10, 32);

		int i;

		char[] hand = new char[2];

		int total_score;

		total_score = 0;
		for (i = 0; i < munch.getSize(); i += 2) {

			hand[0] = munch.getRangeChar(i, 0);
			hand[1] = munch.getRangeChar(i + 1, 0);

			total_score += Main.scorehand(hand[1]) + Main.scoregame(hand);

		}
		
		System.out.println(total_score);
		
		munch = new FileMuncher();
		
		munch.read("input");
		
		munch.parse(10, 32);
		
		total_score = 0;
		
		for (i = 0; i < munch.getSize(); i += 2) {

			hand[0] = munch.getRangeChar(i, 0);
			hand[1] = munch.getRangeChar(i + 1, 0);

			total_score += Main.part2(hand);

		}
		

		System.out.println(total_score);
	}

	static int part2(char[] hand) {
		if (hand[1] == 'X') { /* LOSE */
			if (hand[0] == 'A') { /* ROCK */
				return 3 + 0; /* SCISSORS */
			} else if (hand[0] == 'B') { /* PAPER */
				return 1 + 0; /* ROCK */
			} else if (hand[0] == 'C') { /* SCISSORS */
				return 2 + 0; /* PAPER */
			}
		} else if (hand[1] == 'Y') { /* DRAW */
			if (hand[0] == 'A') { /* ROCK */
				return 1 + 3; /* ROCK */
			} else if (hand[0] == 'B') { /* PAPER */
				return 2 + 3; /* PAPER */
			} else if (hand[0] == 'C') { /* SCISSORS */
				return 3 + 3; /* SCISSORS */
			}
		} else if (hand[1] == 'Z') { /* WIN */
			if (hand[0] == 'A') { /* ROCK */
				return 2 + 6; /* PAPER */
			} else if (hand[0] == 'B') { /* PAPER */
				return 3 + 6; /* SCISSORS */
			} else if (hand[0] == 'C') { /* SCISSORS */
				return 1 + 6; /* ROCK */
			}
		}

		return 0;
	}

	static int scoregame(char[] hand) {

		String blah = new String(hand);

		if (blah.contentEquals("AX")) { /* ROCK ROCK */
			return 3;
		} else if (blah.contentEquals("BX")) { /* PAPER ROCK */
			return 0;
		} else if (blah.contentEquals("CX")) { /* SCISSORS ROCK */
			return 6;
		} else if (blah.contentEquals("AY")) { /* ROCK PAPER */
			return 6;
		} else if (blah.contentEquals("BY")) { /* PAPER PAPER */
			return 3;
		} else if (blah.contentEquals("CY")) { /* SCISSORS PAPER */
			return 0;
		} else if (blah.contentEquals("AZ")) { /* ROCK SCISSORS */
			return 0;
		} else if (blah.contentEquals("BZ")) { /* PAPER SCISSORS */
			return 6;
		} else if (blah.contentEquals("CZ")) { /* SCISSORS SCISSORS */
			return 3;
		}

		return 0;
	}

	static int scorehand(char hand) {

		if ((hand == 'A') || (hand == 'X')) { /* ROCK */
			return 1;
		} else if ((hand == 'B') || (hand == 'Y')) { /* PAPER */
			return 2;
		} else if ((hand == 'C') || (hand == 'Z')) { /* SCISSORS */
			return 3;
		}

		return 0;
	}
}
