import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AoCFileParser {

	byte[] buffer;

	ArrayList<AoCToken> tokens;

	public AoCFileParser() {
		this.tokens = new ArrayList<AoCToken>();
	}

	public AoCFileParser(byte[] buffer) {
		this.tokens = new ArrayList<AoCToken>();
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

	public byte[] getToken(int token) {
		int i = 0;
		byte[] ret = null;
		if ((token > -1) && (token < this.tokens.size())) {
			ret = new byte[this.tokens.get(token).getLength()];
			for (i = 0; i < this.tokens.get(token).getLength(); i++) {
				ret[i] = this.buffer[this.tokens.get(token).getStart() + i];
			}
		}
		return ret;
	}

	public int getTokenAsInt(int token) {
		return Integer.parseInt(this.getTokenAsString(token));
	}

	public String getTokenAsString(int token) {
		return new String(this.getToken(token));
	}

	public byte getTokenAt(int token, int position) {
		byte ret = 0;
		if ((token > -1) && (token < this.tokens.size()) && (position > -1)
				&& (position < this.getToken(token).length)) {
			ret = this.getToken(token)[position];
		}
		return ret;
	}

	public byte[] getTokenBetween(int token, int start, int end) {
		int i = 0;
		int j = 0;
		byte[] ret = null;
		if ((token > -1) && (token < this.tokens.size()) && (start > -1) && (end >= start)
				&& (end <= this.getToken(token).length)) {
			ret = new byte[end - start + 1];
			for (i = start; i <= end; i++) {
				ret[j] = this.getToken(token)[i];
				j++;
			}
		}
		return ret;
	}

	public int getTokenCount() {
		return this.tokens.size();
	}

	public byte[] getTokens(int... tokens) {
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		byte[] ret = null;
		if (tokens.length > 0) {
			for (i = 0; i < tokens.length; i++) {
				if ((tokens[i] > -1) && (tokens[i] < this.tokens.size())) {
					l += this.tokens.get(tokens[i]).getLength();
				}
			}
			ret = new byte[l];
			for (i = 0; i < tokens.length; i++) {
				for (j = 0; j < this.tokens.get(tokens[i]).getLength(); j++) {
					ret[k] = this.buffer[this.tokens.get(tokens[i]).getStart() + j];
					k++;
				}
			}
		}
		return ret;
	}

	public int getTokensAsInt(int... tokens) {
		return Integer.parseInt(this.getTokensAsString(tokens));
	}

	public String getTokensAsString(int... tokens) {
		return new String(this.getTokens(tokens));
	}

	public void parseTokens(int... delimiter) {
		int i = 0;
		int j = 0;
		int k = 0;
		if (delimiter.length > 0) {
			for (i = 0; i < this.buffer.length; i++) {
				for (j = 0; j < delimiter.length; j++) {
					if (this.buffer[i] == delimiter[j]) {
						if ((i - k) < 1) {
							this.tokens.add(new AoCToken(delimiter[j], i, i));
							k = i + 1;
						} else {
							this.tokens.add(new AoCToken(delimiter[j], k, i));
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
