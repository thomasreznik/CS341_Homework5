package reznik;

public class LinkedList {
	Node head;
	Node tail;

	public LinkedList() {
		head = null;
		tail = null;
	}

	// Append node to end of linked list
	public void append(int n) {
		Node node = new Node(n);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
	}

	// Calculates mean of values in linked list
	public double mean() {
		Node trav = head;
		double sum = 0;
		int length = 0;
		while (trav != null) {
			sum += trav.value;
			length++;
			trav = trav.next;
		}
		return (sum / length);
	}

	public double s_deviation() {
		return s_deviation(this.mean());
	}

	// Calculate standard deviation of values in linked list
	public double s_deviation(double mean) {
		Node trav = head;
		double sum = 0;
		int length = 0;
		while (trav != null) {
			sum += Math.pow(mean - trav.value, 2);
			length++;
			trav = trav.next;
		}
		return Math.pow(sum / length, .5);
	}
}
