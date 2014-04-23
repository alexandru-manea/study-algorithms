package data_structures.linked_lists.interview;

import java.util.HashSet;

import data_structures.linked_lists.$implementations.LinkedList;
import data_structures.linked_lists.$implementations.Node;


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
		
		Node<T>	node = list.getHeadNode();	
			
		while (node.getNext() != null)	
		{
			if (uniqueSet.contains(node.getNext().getElement()))
			{
				node.setNext(node.getNext().getNext()); // current node now points to the one two links ahead
				list.setNo_elements(list.getNo_elements() - 1);
			}
			else
			{
				uniqueSet.add(node.getNext().getElement());
				node = node.getNext(); // only advance here because we look ahead and before the link was already changed
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
		Node<T> current = list.getHeadNode();
		
		while (current.getNext() != null)
		{
			boolean duplicate = false;
			
			for (Node<T> seen = list.getHeadNode(); seen != current.getNext(); seen = seen.getNext())
			{
				if (seen.getElement().equals(current.getNext().getElement()))
				{
					current.setNext(current.getNext().getNext());
					list.setNo_elements(list.getNo_elements() - 1);
					duplicate = true;
					break;
				}
			}
			
			if (!duplicate)
			{
				current = current.getNext(); // only if no duplicate was found advance
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
