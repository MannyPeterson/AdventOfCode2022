import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AoCFileParser {

	byte[] buffer;
	
	/* TO-DO: RENAME FIELD TO TOKEN */

	ArrayList<AocParsedField> fields;

	public AoCFileParser() {
		this.fields = new ArrayList<AocParsedField>();
	}
	
	public AoCFileParser(byte[] buffer) {
		this.fields = new ArrayList<AocParsedField>();
		this.buffer = buffer;
	}

	public byte getBufferAt(int position) {
		byte ret = 0;
		if ((position > -1) && (position < this.buffer.length)) {
			ret = this.buffer[position];
		}
		return ret;
	}

	public byte[] getBufferBetween(int start, int end) {
		int i = 0;
		int j = 0;
		byte[] ret = null;
		if ((start > -1) && (end >= start) && (end <= this.buffer.length)) {
			ret = new byte[end - start + 1];
			for (i = start; i <= end; i++) {
				ret[j] = this.buffer[i];
				j++;
			}
		}
		return ret;
	}

	public int getBufferLength() {
		return this.buffer.length;
	}

	public byte[] getField(int field) {
		int i = 0;
		byte[] ret = null;
		if ((field > -1) && (field < this.fields.size())) {
			ret = new byte[this.fields.get(field).getLength()];
			for (i = 0; i < this.fields.get(field).getLength(); i++) {
				ret[i] = this.buffer[this.fields.get(field).getStart() + i];
			}
		}
		return ret;
	}

	public byte getFieldAt(int field, int position) {
		byte ret = 0;
		if ((field > -1) && (field < this.fields.size()) && (position > -1)
				&& (position < this.getField(field).length)) {
			ret = this.getField(field)[position];
		}
		return ret;
	}

	public byte[] getFieldBetween(int field, int start, int end) {
		int i = 0;
		int j = 0;
		byte[] ret = null;
		if ((field > -1) && (field < this.fields.size()) && (start > -1) && (end >= start)
				&& (end <= this.getField(field).length)) {
			ret = new byte[end - start + 1];
			for (i = start; i <= end; i++) {
				ret[j] = this.getField(field)[i];
				j++;
			}
		}
		return ret;
	}

	public int getFieldCount() {
		return this.fields.size();
	}

	public byte[] getFields(int... fields) {
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		byte[] ret = null;
		if (fields.length > 0) {
			for (i = 0; i < fields.length; i++) {
				if ((fields[i] > -1) && (fields[i] < this.fields.size())) {
					l += this.fields.get(fields[i]).getLength();
				}
			}
			ret = new byte[l];
			for (i = 0; i < fields.length; i++) {
				for (j = 0; j < this.fields.get(fields[i]).getLength(); j++) {
					ret[k] = this.buffer[this.fields.get(fields[i]).getStart() + j];
					k++;
				}
			}
		}
		return ret;
	}

	public void parseFields(int... delimiter) {
		int i = 0;
		int j = 0;
		int k = 0;
		if (delimiter.length > 0) {
			for (i = 0; i < this.buffer.length; i++) {
				for (j = 0; j < delimiter.length; j++) {
					if (this.buffer[i] == delimiter[j]) {
						if ((i - k) < 1) {
							this.fields.add(new AocParsedField(delimiter[j], i, i));
							k = i + 1;
						} else {
							this.fields.add(new AocParsedField(delimiter[j], k, i));
							k = i + 1;
						}
						break;
					}
				}
			}
		}
	}

	public void readFile(String file) throws IOException {
		Path path = Paths.get(file);
		this.buffer = Files.readAllBytes(path);
	}

}
