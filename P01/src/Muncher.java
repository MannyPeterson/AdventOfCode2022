import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Muncher {

	byte[] buffer;

	ArrayList<MuncherRange> ranges;

	public Muncher() {

		this.ranges = new ArrayList<MuncherRange>();

	}

	public byte[] getRange(int range) {

		int i;
		byte[] ret;

		i = 0;

		ret = new byte[this.ranges.get(range).getLength()];

		for (; i < this.ranges.get(range).getLength(); i++) {
			ret[i] = this.buffer[this.ranges.get(range).getStart() + i];

		}

		return ret;
	}

	public int getRangeAsInt(int range) {
		if (this.getRangeAsString(range).length() > 0) {
			return Integer.parseInt(this.getRangeAsString(range));
		} else {
			return 0;
		}
	}

	public String getRangeAsString(int range) {

		return new String(this.getRange(range));
	}

	public int getRanges() {

		return this.ranges.size();
	}

	public void parse(int... delimiter) {

		int i;
		int j;
		int k;

		k = 0;

		for (i = 0; i < this.buffer.length; i++) {

			for (j = 0; j < delimiter.length; j++) {
				if (this.buffer[i] == delimiter[j]) {
					if ((i - k) < 2) {
						this.ranges.add(new MuncherRange(delimiter[j], i, i));
						k = i + 1;
					} else {
						this.ranges.add(new MuncherRange(delimiter[j], k, i));
						k = i + 1;
					}
					break;
				}
			}

		}

	}

	public void read(String file) throws IOException {

		Path path = Paths.get(file);
		this.buffer = Files.readAllBytes(path);

	}

}
