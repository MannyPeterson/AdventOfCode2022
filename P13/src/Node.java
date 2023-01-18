import java.util.ArrayList;

public class Node {
	public enum NodeType {
		EMPTY, INTEGER, LIST
	}

	private ArrayList<Node> nodes;
	private Integer integer;
	private NodeType type;

	public Node() {
		this.setNodes(new ArrayList<Node>());
		this.setInteger(0);
		this.setType(Node.NodeType.EMPTY);
	}

	public Node(ArrayList<Node> nodes) {
		this.setNodes(this.copyOf(nodes));
		this.setInteger(0);
		this.setType(Node.NodeType.LIST);
	}

	public Node(Integer integer) {
		this.setNodes(new ArrayList<Node>());
		this.setInteger(integer);
		this.setType(Node.NodeType.INTEGER);
	}

	public void add(Node node) {
		if (this.isList()) {
			this.setInteger(0);
			this.setType(Node.NodeType.LIST);
			this.getNodes().add(node);
		} else if (this.isEmpty()) {
			this.setInteger(0);
			this.setType(Node.NodeType.LIST);
			this.getNodes().add(node);
		} else if (this.isInteger()) {
			/** CAN'T ADD NODE IF NODETYPE IS INTEGER **/
		}
	}

	private ArrayList<Node> copyOf(ArrayList<Node> nodes) {
		ArrayList<Node> ret = new ArrayList<Node>();

		for (Node source : nodes) {
			Node destination = new Node();

			destination.setNodes(source.getNodes());
			destination.setInteger(source.getInteger());
			destination.setType(source.getType());

			ret.add(destination);
		}

		return ret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[ ");
		
		if(this.isEmpty()) {
			sb.append("EMPTY");
		} else if(this.isInteger()) {
			sb.append(this.getInteger());
		} else if(this.isList()) {
			sb.append("LIST(" + this.getSizeOfList() + ")");
		}
		
		
		sb.append(" ]");
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}

		Node other = (Node) o;
		if ((this.getInteger() != other.getInteger()) || (this.getType() != other.getType())
				|| (this.getSizeOfList() != other.getSizeOfList())) {
			return false;
		}

		for (int i = 0; (i < this.getSizeOfList()) && (i < other.getSizeOfList()); i++) {
			if (!this.getNodes().get(i).equals(other.getNodes().get(i))) {
				return false;
			}
		}

		return true;
	}

	public Node getAsListOfNodes() {
		Node ret = null;

		if (this.isEmpty()) {
			ret = this;
		} else if (this.isList()) {
			ret = this;
		} else if (this.isInteger()) {
			ret = new Node();
			ret.add(new Node(this.getInteger()));
		}

		return ret;
	}

	public Node getNode(int index) {
		Node ret = null;

		if (this.isList()) {
			ret = this.getNodes().get(index);
		}

		return ret;
	}

	private ArrayList<Node> getNodes() {
		return this.nodes;
	}

	public Integer getInteger() {
		return this.integer;
	}

	public int getSizeOfList() {
		int ret = 0;

		if (this.isList()) {
			ret = this.getNodes().size();
		}

		return ret;
	}

	private NodeType getType() {
		return this.type;
	}

	public boolean isEmpty() {
		boolean ret = false;

		if (this.getType() == Node.NodeType.EMPTY) {
			ret = true;
		}

		return ret;
	}

	public boolean isInteger() {
		boolean ret = false;

		if (this.getType() == Node.NodeType.INTEGER) {
			ret = true;
		}

		return ret;
	}

	public boolean isList() {
		boolean ret = false;

		if (this.getType() == Node.NodeType.LIST) {
			ret = true;
		}

		return ret;
	}

	private void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	private void setInteger(Integer integer) {
		this.integer = integer;
	}

	private void setType(NodeType type) {
		this.type = type;
	}
}
