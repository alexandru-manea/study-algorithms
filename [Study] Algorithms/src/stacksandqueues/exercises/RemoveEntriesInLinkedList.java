package stacksandqueues.exercises;

public class RemoveEntriesInLinkedList {

	
	/**
	 * Test client
	 * 
	 */
	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		// add 15 items, with 6 occurrences of 1
		for (int i = 0; i < 10; i++) {
			
			list.addItem(i + 1);
			list.addItem(1);
			list.addItem(1);
			list.addItem(1);
		}
		
		list.addItem(2);
		
		// print list
		System.out.println(list);
		
		// remove all occurrences of 1
		list.removeEntries(1);
		
		// print list
		System.out.println(list);
	}
	
	
	/**
	 * Barebones linked-list implementation -- see method of interest
	 * 
	 */
	private static final class LinkedList<Item> {
		
		private Node<Item> head;
		
		public void addItem(Item item) {
			
			Node<Item> newNode = new Node<Item>(item);
			newNode.nextNode = head;
			head = newNode;
		}
		
		
		/**
		 * Method of interest -- remove all occurrences of an item in the linked list
		 * 
		 */
		public void removeEntries(Item itemToBeRemoved) {
			
			if (head == null) {
				
				throw new UnsupportedOperationException("Cannot remove entries from an empty list");
			}
			
			// remove occurrences at the beginning of the list
			while ((head != null) && (head.item.equals(itemToBeRemoved))) {
				
				head = head.nextNode;
			}
			
			// remove occurrences in the rest of the list
			Node<Item> currentNode = head;
			
			while (currentNode != null) {
				
				Node<Item> nextLegitimateNode = currentNode.nextNode;
				
				// navigate through sublist of elements equal to the element to be removed
				while ((nextLegitimateNode != null) && (nextLegitimateNode.item.equals(itemToBeRemoved))) {
					
					nextLegitimateNode = nextLegitimateNode.nextNode;
				}
				
				// skip all elements in the above sublist
				currentNode.nextNode = nextLegitimateNode;
				
				// consider rest of list next
				currentNode = nextLegitimateNode;
			}
		}
		
		
		@Override
		public String toString() {
			
			if (head == null) {
				
				return "<<Empty list>>";
			}
			
			StringBuilder builder = new StringBuilder();
			
			Node<Item> currentNode = head;
			
			while (currentNode != null) {
				
				builder.append(currentNode.item.toString());
				builder.append("\t");
				currentNode = currentNode.nextNode;
			}
			
			return builder.toString();
		}
	}
	
	private static final class Node<Item> {
		
		private Item item;
		private Node<Item> nextNode;
		
		public Node(Item item) {
			
			this.item = item;
		}
	}
}
