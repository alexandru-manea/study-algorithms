package linkedlists;

/**
 *  TASK :: (interview) Reverse a singly linked list in-place. 
 *
 */

public class ReverseList
{
	
	/**
	 * STACKOVERFLOW
	 * 
	 * Recursive method.
	 * 
	 * GENERAL METHODOLOGY FOR RECURSIVE PROBLEMS
	 * $ Ask three questions:
	 * 	1. What is the reverse of the null list? --> null
	 *  2. What is the reverse of a one element list? --> the element
	 *  3. What is the reverse of a n element list? --> the reverse of the sublist starting with the next el. followd by the current el.
	 * 
	 * NOTE :: It is easier to pass the node we are at in the list than to pass the list itself
	 * 
	 * @return the new head - the previously last element. 
	 * 						- every method call will return it and it will percolate back through the stack trace
	 * 
	 * 
	 * Time: O(n), but overhead of creating/popping stack frames
	 * Space: O(1)
	 * 
	 */
	public static <T> Node<T> reverseRecursively(Node<T> currentNode)
	{
		
		/* question #1 --> reverse of null/empty list is the null/empty list */
		
		if (currentNode == null)
			return null;
		
		
		/* question #2 --> reverse of one element list is the element */
		
		if (currentNode.next == null)
			return currentNode;
		
		
		/* question #3 --> reverse of n element list is the reverse of the list from next element on followed by the current element */
		
		Node<T> nextNode = currentNode.next;
		
		// unlink the current node fom the rest
		currentNode.next = null;
		
		// reverse everything from the second element on; we return smt. so we can percolate new head
		Node<T> reverseRest = reverseRecursively(nextNode);
		
		// join the two lists
		nextNode.next = currentNode;
		
		// return the new head head - percolate from the last node through method calls until the original one
		return reverseRest;
	}
	
	
	/**
	 *  WEB
	 * 
	 *  We always have two lists:
	 *  1. the remaining portion of the original list pointed to by <current>
	 *  2. the new list pointed to by <newHead>
	 *  
	 *  Each iteration strips the head element from the original list, and adds it to the head of our new list.
	 *  
	 *  Time: O(n)
	 *  Space: O(1)
	 *  
	 */
	public static <T> Node<T> reverseIteratively(Node<T> head)
	{
		Node<T> current = head;
		Node<T> newHead = null;
		
		while (current != null)
		{
			Node<T> temp = current; // save the current loop pointer
			current = current.next; // advance the current loop pointer one past the head
			temp.next = newHead; // assign the next pointer of our new head to the previous head pointer
			newHead = temp; // track new head
		}
		
		return newHead;
	}
}
