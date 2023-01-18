
public class P10Machine {
	public final static int CRT_COLS = 40;
	public final static int CRT_ROWS = 6;
	private int clock;
	private int[][] crt;
	private int nextClock;
	private int register;

	private int sum;

	public P10Machine() {
		this.register = 1;
		this.clock = 1;
		this.nextClock = 20;
		this.sum = 0;
		this.crt = new int[P10Machine.CRT_ROWS][P10Machine.CRT_COLS];
	}

	public void execute(P10Instruction instruction) {
		if (instruction.getOpcode() == P10Instruction.ADDX) {
			for (int i = 0; i < instruction.getCycles(); i++) {
				// System.out.println("clock: " + this.clock + " ADDX " +
				// instruction.getOperand() + " reg: " + this.register + " sum: " + this.sum);
				this.tick();
			}
			this.register += instruction.getOperand();
		} else if (instruction.getOpcode() == P10Instruction.NOOP) {
			for (int i = 0; i < instruction.getCycles(); i++) {

				// System.out.println("clock: " + this.clock + " NOOP " + " reg: " +
				// this.register + " sum: " + this.sum);
				this.tick();
			}
			/** NOOP - Nothing to do here **/
		}
	}

	public int getSum() {
		return this.sum;
	}

	public void rednerCRT() {
		for (int row = 0; row < P10Machine.CRT_ROWS; row++) {
			for (int col = 0; col < P10Machine.CRT_COLS; col++) {
				if (this.crt[row][col] == 0) {
					System.out.print(" ");
				} else if (this.crt[row][col] == 1) {
					System.out.print("#");
				}
			}
			System.out.println();
		}
	}

	private void tick() {
		if ((((this.clock - 1) % 40) >= (this.register - 1)) && (((this.clock - 1) % 40) <= (this.register + 1))) {
			this.crt[(this.clock - 1) / 40][(this.clock - 1) % 40] = 1;
		}

		if (this.clock == this.nextClock) {
			this.sum += (this.clock * this.register);
			this.nextClock += 40;
		}

		this.clock++;
	}
}
