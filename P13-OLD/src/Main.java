import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static int pos = 0;

	public static void main(String[] args) throws IOException {
		AoCFileParser input = new AoCFileParser();
		Main parser = new Main();

		int pairs = 0;

		input.readFile("input");

		input.parseTokens(10);

		pairs = (input.getTokenCount() + 1) / 3;

		int sum = 0;

		for (int i = 1; i <= pairs; i++) {

			ArrayList<String> left = null;

			Node left_root = new Node();

			Main.pos = 0;
			left = parser.parse(input.getToken(((i - 1) * 3) + 0));

			parser.tree(left, left_root, 0);

			ArrayList<String> right = null;

			Node right_root = new Node();

			Main.pos = 0;
			right = parser.parse(input.getToken(((i - 1) * 3) + 1));

			parser.tree(right, right_root, 0);

			System.out.println("pair : " + i + " compare: " + parser.compare(left_root, right_root));

			if (parser.compare(left_root, right_root) == 1) {
				sum += i;
			}

		}

		System.out.println(sum);

	}

	public int compare(Node left, Node right) {
		// System.out.println("left: " + left + " right: " + right);

		if (!left.isEmpty() && right.isEmpty()) {
			return 1;
		} else if(left.isEmpty() && !right.isEmpty()) {
			return -1;
		} else if (left.isInteger() && right.isInteger()) {
			if (left.getInteger() < right.getInteger()) {
				return 1;
			} else if (left.getInteger() == right.getInteger()) {
				return 0;
			} else if (left.getInteger() > right.getInteger()) {
				return -1;
			}
		} else if (!left.isInteger() && right.isInteger()) {
			int result = 0;
			Node node = new Node(new ArrayList<Node>());
			node.add(new Node(right.getInteger()));
			result = compare(left, node);
			return result;
		} else if (left.isInteger() && !right.isInteger()) {
			int result = 0;
			Node node = new Node(new ArrayList<Node>());
			node.add(new Node(left.getInteger()));
			result = compare(node, right);
			return result;
		} else if (!left.isInteger() && !right.isInteger()) {
			int i = 0;
			while ((i < left.get().size()) && (i < right.get().size())) {
				int result = compare(left.get().get(i), right.get().get(i));
				if (result == 1) {
					return 1;
				} else if (result == -1) {
					return -1;
				} else {
					i++;
				}
			}
			if ((i == left.get().size()) && (i < right.get().size())) {
				return 1;
			} else if ((i < left.get().size()) && (i == right.get().size())) {
				return -1;
			}
		}

		return 0;

	}

	public ArrayList<String> parse(byte[] packet) {
		ArrayList<String> items = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();

		for (; Main.pos < packet.length; Main.pos++) {
			char c = (char) packet[Main.pos];

			if (c == '[') {
				Main.pos++;
				items.add("[");
				items.addAll(parse(packet));
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

	public int tree(ArrayList<String> list, Node node, int i) {

		for (; i < list.size(); i++) {

			String item = list.get(i);

			if (item.compareTo("[") == 0) {
				Node child = new Node();
				node.add(child);

				i = tree(list, child, i + 1);
			} else if (item.compareTo("]") == 0) {
				break;
			} else {
				Node child = new Node();
				node.add(child);
				child.setInteger(Integer.parseInt(item));
			}
		}

		return i;
	}
}