import java.util.ArrayList;

public class Node {
	private ArrayList<Node> child;
	private int integer;
	private boolean isinteger;
	private boolean isempty;

	public Node() {
		this.child = null;
		this.integer = 0;
		this.isinteger = true;
		this.isempty = true;

	}

	public String toString() {
		if (this.isempty) {
			return new String("EMPTY");
		} else {
			if (this.isinteger) {
				return new String("INTEGER: " + this.integer);
			} else {
				return new String("LIST: " + this.child.size());
			}
		}
	}

	public boolean isInteger() {
		return this.isinteger;
	}

	public Node(ArrayList<Node> nodelist) {
		this.child = nodelist;
		this.integer = 0;
		this.isinteger = false;
		this.isempty = false;
	}

	public Node(int integer) {
		this.child = null;
		this.integer = integer;
		this.isinteger = true;
		this.isempty = false;
	}

	public ArrayList<Node> get() {
		return this.child;
	}

	public void add(Node child) {
		if (this.child == null) {
			this.child = new ArrayList<Node>();
		}
		this.child.add(child);
		this.isinteger = false;
		this.isempty = false;
	}

	public int getInteger() {
		return this.integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
		this.isinteger = true;
		this.isempty = false;
	}

	public boolean isEmpty() {
		return this.isempty;

	}

}
