package linkedlists;

import java.util.HashSet;


/**
 * TASK :: Remove duplicates from an unsorted linked list
 * 
 * FOLLOW UP :: Do not use any additional buffer.
 *
 */

public class RemoveDuplicates
{
	
	/**
	 *  MINE AND THEIRS
	 *  
	 *  Keep track of seen unique elements in a hashset. Iterate through the list and:
	 *   - if element in node is in the set (was seen before), remove the node
	 *   - if not, add element to the hashset
	 *   
	 *   OBS --> To be able to remove a node, we need to update the pointer of the previous
	 *   one to point to the next node. To do this, we can either keep a reference to the
	 *   previous node or always look ahead by one (like I did).
	 *   
	 *   Time: O(n)
	 *   Space: O(n)
	 *   
	 */
	
	public static <T> void removeDuplicates (LinkedList<T> list)
	{
		HashSet<T> uniqueSet = new HashSet<T>();
		uniqueSet.add(list.getHead()); // head is first element, so unique
		
		Node<T>	node = list.head;	
			
		while (node.next != null)	
		{
			if (uniqueSet.contains(node.next.element))
			{
				node.next = node.next.next; // current node now points to the one two links ahead
				list.no_elements--;
			}
			else
			{
				uniqueSet.add(node.next.element);
				node = node.next; // only advance here because we look ahead and before the link was already changed
			}
		}
	}
	
	
	/**
	 *  FOLLOW UP
	 *  
	 *  MINE AND THEIRS
	 *  
	 *  Iterate with two pointers: <current> does a normal iteration, while <seen> iterates through all prior nodes
	 *  to check for duplicates. If one is found, then remove the node, otherwise move on.
	 *   
	 *  Time: O(n^2)
	 *  Space: O(1)
	 * 
	 */
	public static <T> void removeDuplicatesFollowUp(LinkedList<T> list)
	{
		Node<T> current = list.head;
		
		while (current.next != null)
		{
			boolean duplicate = false;
			
			for (Node<T> seen = list.head; seen != current.next; seen = seen.next)
			{
				if (seen.element.equals(current.next.element))
				{
					current.next = current.next.next;
					list.no_elements--;
					duplicate = true;
					break;
				}
			}
			
			if (!duplicate)
			{
				current = current.next; // only if no duplicate was found advance
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		LinkedList<Integer> list = new LinkedList<Integer>();
		 
		list.add(10);
		list.add(2);
		list.add(3);
		list.add(1);
		list.add(4);
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(4);
		list.add(6);
		
		list.printList();
		
		removeDuplicatesFollowUp(list);
		
		list.printList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
