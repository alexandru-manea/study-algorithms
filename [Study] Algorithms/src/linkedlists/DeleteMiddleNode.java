package linkedlists;

/**
 *  TASK :: Delete a node in the midle of a single linked list, given only access to that node. 
 *
 */

public class DeleteMiddleNode
{

	/**
	 * THEIR
	 * 
	 * Simply copy the data (element and link) from the ndext node into this node and then delete the next node.
	 * NOTE :: cannot do it if last node
	 * 
	 */
	
	public static <T> boolean deleteNode(Node<T> node)
	{
		// safety check
		if ((node == null) || (node.next == null))
			return false;
		
		// copy next node into this one
		Node<T> next = node.next;
		node.element = next.element; 
		node.next = next.next;
		
		return true;
	}
}
