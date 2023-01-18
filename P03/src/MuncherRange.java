
public class MuncherRange {

	private int delimiter;
	private int start;
	private int stop;

	public MuncherRange(int delimiter, int start, int stop) {
		this.delimiter = delimiter;
		this.start = start;
		this.stop = stop;
	}

	public int getDelimiter() {
		return this.delimiter;
	}

	public int getLength() {
		return this.stop - this.start;
	}

	public int getStart() {
		return this.start;
	}

	public int getStop() {
		return this.stop;
	}

}
