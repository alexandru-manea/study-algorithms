package data_structures.linked_lists.interview;

import data_structures.linked_lists.$implementations.LinkedList;
import data_structures.linked_lists.$implementations.Node;

/**
 *  TASK :: Find the n-th to last element of a singly linked list. 
 *  
 *  NOTE :: We assume we do not know the number of elements of the list (although we always do).
 *  	    If we did, we would just go through links (no_elements - n) times. Also, we could
 *  		simply traverse it once and find the no_elements, but it's inefficient.
 *
 */

public class NthToLastElement
{
	/**
	 *  THEIR SOLUTION
	 *  
	 *  Make a sliding window (two pointers at the ends) and move it until the right end reaches the last node.
	 *  
	 *  1. Create two pointers, p1 and p2, that point to the beggining of the node
	 *  2. Increment p2 by n positions => distance (p1, p2) = n
	 *  3. Increment both pointers until (p2.next == null), meaning it has reached the last node
	 *  
	 *  Time: O(n)
	 *  Space: O(1)
	 */
	
	
	public static <T> T nthToLast1(LinkedList<T> list, int n)
	{
		// safety check
		if (list.getHead() == null || n < 1)
			return null;
		
		// #1
		Node<T> p1 = list.getHeadNode();
		Node<T> p2 = list.getHeadNode();
		
		// #2
		for (int i = 0; i < n; i++)
		{
			if (p2 == null)
				return null;
			
			p2 = p2.getNext();
		}
		
		// #3
		while (p2.getNext() != null)
		{
			p1 = p1.getNext();
			p2 = p2.getNext();
		}
		
		return p1.getElement();
	}
	
	/**
	 *  WEB
	 *  
	 *  Recursive method.
	 *  
	 *  We go to the end of the list => no_els stack frames => need to go back n times down the recursive call stack.
	 *  
	 *  So we need to keep a count of how many times we pop a stack frame after the end of list has been reached. How?
	 *  Because a local variable would stay local to each function call, we need an alternative - make a static inner
	 *  class and keep a static variable. A method in this class can then be recursive and update this count. We could
	 *  make the class, variable, & method non-static just as well, but this makes more (conceptual) sense.
	 *  NOTE --> In C, a static variable in the method can be used
	 *  
	 *  <!!! IMPORTANT !!!> Because we need to count and check the number of popped stack frames, we add the code for  
	 *  doing this after the recursive call.
	 *  
	 *  Time: O(n), but more than iterative
	 *  Space: more than iterative
	 *
	 */
	static class NthToLast2
	{
		// number of popped frames
		static int i;
		
		private static <T> void nthToLastRecursive(Node<T> node, int n)
		{
			// base case -- reach the end of the list
			if (node == null)
				return;
			
			// recursive call with advanced pointer
			nthToLastRecursive(node.getNext(), n);
			
			// increment i and check to see if it equals n, case in which we've reached the desired element
			i++;
			if (i == n)
				System.out.println(node.getElement());
		}
	}
}


