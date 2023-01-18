import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static int pos = 0;

	public static void main(String[] args) throws IOException {
		
		/** PART I **/
		
		AoCFileParser input = new AoCFileParser();
		Main parser = new Main();
		int pairs = 0;
		int sum = 0;

		input.readFile("input");

		input.parseTokens(10);

		pairs = (input.getTokenCount() + 1) / 3;

		for (int i = 1; i <= pairs; i++) {
			int result = 0;

			Node left = new Node();
			Main.pos = 0;
			parser.makeTree(parser.parsePacket(input.getToken(((i - 1) * 3) + 0)), left, 0);

			Node right = new Node();
			Main.pos = 0;
			parser.makeTree(parser.parsePacket(input.getToken(((i - 1) * 3) + 1)), right, 0);

			result = parser.compareTrees(left, right);
			
			//System.out.println(i + " " + result);

			if (result == 1) {
				sum += i;
			}

		}

		//System.out.println("sum: " + sum);
		
		/*** PART II ***/
		
		
		input = new AoCFileParser();
		
		input.readFile("input2");

		input.parseTokens(10);
		

		String[] packet = new String[(input.getTokenCount() / 3) * 2 + 2] ;

		
		for(int i = 0, j = 0; i < packet.length; i++) {
			packet[i] = input.getTokenAsString(j);
			i++;
			j++;
			packet[i] = input.getTokenAsString(j);
			j++;
			j++;
		}
		
		for(int i = 0; i < packet.length; i++) {
			//System.out.println(packet[i]);
		}

		for(int i = 0; i < (packet.length - 1); i++) {
			for(int j = 0; j < (packet.length - i - 1); j++) {
				Node left = new Node();
				Node right = new Node();
				
				Main.pos = 0;
				parser.makeTree(parser.parsePacket(packet[j].getBytes()), left, 0);
				
				Main.pos = 0;
				parser.makeTree(parser.parsePacket(packet[j + 1].getBytes()), right, 0);
				
				if(parser.compareTrees(right, left) == 1) {
					//System.out.println(" before: " + packet[j]);
					//System.out.println(" before: " + packet[j + 1]);
					String temp = packet[j];
					packet[j] = new String(packet[j + 1]);
					packet[j + 1] = new String(temp);
					//System.out.println(" after: " + packet[j]);
					//System.out.println(" after: " + packet[j + 1]);
				}
			}
		}
		for(int i = 0; i < packet.length; i++) {
			System.out.println(i + 1 + "::"+ packet[i]);
		}
		

	}
	

	public int compareTrees(Node left, Node right) {

		if (!left.isEmpty() && right.isEmpty()) {
			return -1;
		} else if (left.isEmpty() && !right.isEmpty()) {
			return 1;

		} else if (left.isInteger() && right.isInteger()) {
			if (left.getInteger() < right.getInteger()) {
				return 1;
			} else if (left.getInteger() == right.getInteger()) {
				return 0;
			} else if (left.getInteger() > right.getInteger()) {
				return -1;
			}
		} else if (left.isList() && right.isInteger()) {
			return compareTrees(left, right.getAsListOfNodes());
		} else if (left.isInteger() && right.isList()) {
			return compareTrees(left.getAsListOfNodes(), right);
		} else if (left.isList() && right.isList()) {
			int i = 0;
			while ((i < left.getSizeOfList()) && i < right.getSizeOfList()) {
				int compare = 0;
				compare = compareTrees(left.getNode(i), right.getNode(i));
				if (compare == 1) {
					return 1;
				} else if (compare == -1) {
					return -1;
				}

				i++;

				if ((i == left.getSizeOfList()) && (i < right.getSizeOfList())) {
					return 1;
				} else if ((i < left.getSizeOfList()) && (i == right.getSizeOfList())) {
					return -1;
				}
			}
		}

		return 0;
	}

	public int makeTree(ArrayList<String> list, Node node, int i) {

		for (; i < list.size(); i++) {

			String item = list.get(i);

			if (item.compareTo("[") == 0) {
				Node child = new Node();
				node.add(child);

				i = makeTree(list, child, i + 1);
			} else if (item.compareTo("]") == 0) {
				break;
			} else {
				Node child = new Node(Integer.parseInt(item));
				node.add(child);
			}
		}

		return i;
	}

	public ArrayList<String> parsePacket(byte[] packet) {
		ArrayList<String> items = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();

		for (; Main.pos < packet.length; Main.pos++) {
			char c = (char) packet[Main.pos];

			if (c == '[') {
				Main.pos++;
				items.add("[");
				items.addAll(parsePacket(packet));
			} else if (c == ',') {
				if (sb.length() > 0) {
					items.add(sb.toString());
				}
				sb = new StringBuilder();
			} else if (c == ' ') {

			} else if (c == ']') {
				if (sb.length() > 0) {
					items.add(sb.toString());
				}
				items.add("]");
				sb = new StringBuilder();
				break;
			} else {
				sb.append(c);
			}
		}

		return items;

	}
}