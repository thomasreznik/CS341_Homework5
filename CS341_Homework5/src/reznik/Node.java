package reznik;

public class Node {
	public double value;
	public Node next;

	Node(double value) {
		this.value = value;
	}

	public Node(double value, Node next) {
		this.value = value;
		this.next = null;
	}
}
