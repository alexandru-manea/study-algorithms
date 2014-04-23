package linkedlists;


/**
 * Problem :: Detect loops in singly-linked list
 * 
 * Optimal solution :: Floys's Cycle-Finding Algorithm / The Tortoise and Hare Algorithm
 * -> simultaneously go through the list by ones (slow iteration) and by twos (fast iteration)
 * -> if there is a loop the fast iterator will go around that loop twice as fast as the slow iterator
 * -> the fast iterator will lap the slow iterator within a single pass through the cycle
 * -> detecting a loop is then just detecting that the slow iterator has been lapped by the fast one
 * 
 * @author Alexandru Manea
 *
 */
public class DetectLoops {

	
	public static <T> boolean hasLoop(Node<T> startNode) {
		
		if (startNode == null)
			return false;
		
		Node<T> slowIterator = startNode; // moves one by one
		Node<T> fastIterator = startNode; // moves two by two
		
		while (((slowIterator = slowIterator.next) != null) // we are not at the end of the LL with the slow iterator 
			  && ((fastIterator.next != null) // we are not at the end of the LL with the fast one (necessary for the next condition)
			  && ((fastIterator = fastIterator.next.next) != null))) { // we are not at the next to last node with the fast one
			
			// if they're in the same spot, we have a cycle
			if (slowIterator == fastIterator) {
				
				return true;
			}
		}
		
		// no cycle detected
		return false;
	}
	
	
	private static final class Node<T> {
		
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			
			this.data = data;
		}
	}
	
	// Test client
	public static void main(String[] args) {
		
		Node<Integer> node1 = new Node<Integer>(1);
		Node<Integer> node2 = new Node<Integer>(2);
		node1.next = node2;
		Node<Integer> node3 = new Node<Integer>(3);
		node2.next = node3;
		Node<Integer> node4 = new Node<Integer>(4);
		node3.next = node4;
		Node<Integer> node5 = new Node<Integer>(5);
		node4.next = node5;
		
		// cycle
		node5.next = node2;
		
		System.out.println("Has cycles: " + hasLoop(node1));
	}
}
