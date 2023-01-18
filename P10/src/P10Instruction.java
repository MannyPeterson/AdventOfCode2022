
public class P10Instruction {
	public final static int ADDX = 1;
	public final static int NOOP = 2;

	private int opcode;
	private int operand;

	public P10Instruction(byte[] opcode, byte[] operand) {
		if ((new String(opcode).compareTo("addx")) == 0) {
			this.opcode = P10Instruction.ADDX;
			this.operand = Integer.parseInt(new String(operand));
		} else if ((new String(opcode).compareTo("noop")) == 0) {
			this.opcode = P10Instruction.NOOP;
			this.operand = 0;
		}
	}

	public int getCycles() {
		if (this.opcode == P10Instruction.ADDX) {
			return 2;
		} else if (this.opcode == P10Instruction.NOOP) {
			return 1;
		}
		return 0;
	}

	public int getOpcode() {
		return this.opcode;
	}

	public int getOperand() {
		return this.operand;
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();

		ret.append("[ ");

		if (this.opcode == P10Instruction.ADDX) {
			ret.append("ADDX, " + this.operand);
		} else if (this.opcode == P10Instruction.NOOP) {
			ret.append("NOOP, 0");
		}

		ret.append(" ]");

		return ret.toString();
	}
}
