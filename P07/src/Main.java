import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static String[] stack;
	public static int top;
	public static ArrayList<AoCFile> files;
	public static ArrayList<AoCDir> dirs;

	public static void main(String[] args) throws IOException {

		AoCFileParser input = null;
		int i = 0;

		Main.stack = new String[10];
		Main.files = new ArrayList<AoCFile>();
		Main.dirs = new ArrayList<AoCDir>();
		Main.top = 0;

		input = new AoCFileParser();

		input.readFile("input");

		input.parseFields(10);

		for (i = 0; i < input.getFieldCount(); i++) {
			if (input.getFieldAt(i, 0) == 36) {
				/** $ **/
				if ((new String(input.getField(i))).compareTo("$ cd /") == 0) {
					Main.push("root");
					Main.dirs.add(new AoCDir("root", "root", 0));

				} else if ((new String(input.getField(i))).compareTo("$ cd ..") == 0) {
					int size = 0;
					for (AoCDir dir : Main.dirs) {
						if(dir.getPath().compareTo(Main.stackToString()) == 0) {
							size = dir.getSize();
							break;
						}
					}
					Main.pop();
					for (AoCDir dir : Main.dirs) {
						if(dir.getPath().compareTo(Main.stackToString()) == 0) {
							dir.addFileSize(size);
							break;
						}
					}
					
				} else if ((new String(input.getField(i))).startsWith("$ cd ") == true) {
					Main.push(new String(input.getFieldBetween(i, 5, input.getField(i).length - 1)));
					Main.dirs.add(new AoCDir(Main.stackToString(),
							new String(input.getFieldBetween(i, 5, input.getField(i).length - 1)), 0));

				}
			} else if (input.getFieldAt(i, 0) == 100) { /** d **/

			} else if ((input.getFieldAt(i, 0) > 47) && (input.getFieldAt(i, 0) < 58)) { /** 0 - 9 **/
				String[] details = (new String(input.getField(i))).split(" ");
				Main.files.add(new AoCFile(Main.stackToString(), details[1], Integer.parseInt(details[0])));
				for (AoCDir dir : Main.dirs) {
					if (Main.stackToString().compareTo(dir.getPath()) == 0) {
						dir.addFileSize(Integer.parseInt(details[0]));
						break;
					}
				}
			}

		}
		
		int total = 0;
		
		for (AoCDir dir : Main.dirs) {
			if(dir.getSize() <= 100000) {
			System.out.println(dir.toString());
			total += dir.getSize();
			}
		}
		
		System.out.println(total);

		int nearest = 999999999;
		int inuse = 0;
		int available = 0;
		int needed = 0;
		
		for (AoCDir dir : Main.dirs) {
			if(dir.getName().compareTo("root") ==0 ) {
				inuse = dir.getSize();
				break;
			}
		}
		
		available = 70000000 - inuse;
		needed = 30000000 - available;
		
		System.out.println(needed);
		
		for (AoCDir dir : Main.dirs) {
			if((dir.getSize() < nearest) && (dir.getSize() >= needed	)) {
				nearest = dir.getSize();
				System.out.println(dir.toString());
			}
		}
	}

	public static void push(String dir) {
		Main.stack[top] = dir;
		top++;
	}

	public static String pop() {
		String ret = null;
		top--;
		ret = Main.stack[top];
		Main.stack[top] = null;
		return ret;
	}

	public static String stackToString() {
		String ret = null;
		StringBuilder dir = new StringBuilder();

		int i = 0;

		for (i = 0; i < top; i++) {
			dir.append(Main.stack[i]);
			if ((i + 1) < top) {
				dir.append("/");
			}
		}

		ret = dir.toString();

		return ret;
	}
}