
public class AoCFile {
	private String dir;
	private String name;
	private int size;

	public AoCFile(String dir, String name, int size) {
		this.dir = dir;
		this.name = name;
		this.size = size;
	}

	public String getDir() {
		return dir;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}
	
	public String toString() {
		StringBuilder ret = new StringBuilder();
		
		ret.append("File ");
		ret.append(this.name);
		ret.append(" is ");
		ret.append(Integer.toString(this.size));
		ret.append(" bytes in size and is in ");
		ret.append(dir);
		ret.append("...");
		
		return ret.toString();
	}
}
