package data_structures.linked_lists.interview;

import data_structures.linked_lists.$implementations.Node;

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
		if ((node == null) || (node.getNext() == null))
			return false;
		
		// copy next node into this one
		Node<T> next = node.getNext();
		node.setElement(next.getElement()); 
		node.setNext(next.getNext());
		
		return true;
	}
}
