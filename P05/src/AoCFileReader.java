import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AoCFileReader {

	byte[] buffer;

	ArrayList<AocFileReaderRange> ranges;

	public AoCFileReader() {

		this.ranges = new ArrayList<AocFileReaderRange>();

	}

	public byte[] range(int range) {

		int i;
		byte[] ret;

		i = 0;

		ret = new byte[this.ranges.get(range).getLength()];

		for (; i < this.ranges.get(range).getLength(); i++) {
			ret[i] = this.buffer[this.ranges.get(range).getStart() + i];

		}

		return ret;
	}
	
	public byte[] ranges(int... ranges) {
		
		int i;
		int j;
		int k;
		int l;
		byte[] ret;

		i = 0;
		j = 0;
		k = 0;
		l = 0;
		
		for(i = 0; i < ranges.length; i++) {
			l += this.ranges.get(ranges[i]).getLength();
		}
		
		
		ret = new byte[l];

		
		for(i = 0; i < ranges.length; i++) {
			for(j = 0; j < this.ranges.get(ranges[i]).getLength(); j++) {
				ret[k] = this.buffer[this.ranges.get(ranges[i]).getStart() + j];
				k++;
			}
		}
	

		return ret;
	}

	public int getint(int range) {
		if (this.getRangeAsString(range).length() > 0) {
			return Integer.parseInt(this.getRangeAsString(range));
		} else {
			return 0;
		}
	}

	public String getRangeAsString(int range) {

		return new String(this.range(range));
	}
	
	public char getRangeChar(int range, int pos) {
		
		return (char) this.range(range)[pos];
	}

	public int size() {

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
					if ((i - k) < 1) {
						this.ranges.add(new AocFileReaderRange(delimiter[j], i, i));
						k = i + 1;
					} else {
						this.ranges.add(new AocFileReaderRange(delimiter[j], k, i));
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
