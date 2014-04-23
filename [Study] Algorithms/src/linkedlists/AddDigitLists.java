package linkedlists;

/**
 *  TASK :: We have two numbers represented by linked lists, where each node contains a single digit.
 *  The digits are stored in reverse order. Write a function that adds the two numbers and returns the
 *  sum as a linked list.
 *
 */

public class AddDigitLists
{

	/**
	 * THEIR
	 * 
	 * Recursive solution -- add node by node (digit by digit).
	 * 
	 * - result.element = (node1.element + node2.element + any earlier carry) % 10
	 * - if node1 + node2 > 10, then carry 1 to the next addition
	 * - add the tails of the two nodes, passing along the carry
	 * - link the current node to the rest
	 * 
	 */

	public static Node<Integer> addLists(Node<Integer> list1, Node<Integer> list2, int carry)
	{

		// base case -- when we've exhaused iterating through both lists
		if (list1 == null && list2 == null) 
			return null;

		// initialise the sum to the carry
		int sum = carry;

		// add the digit in one or both lists. only one means we've exhauseted the 'shortest' number
		if (list1 != null)
			sum += list1.element;
		if (list2 != null)
			sum += list2.element;

		// take modulus of the sum to get digit
		int resultDigit = sum % 10;	

		// create the new node in the result list & assign the element
		Node<Integer> result = new Node<Integer>();
		result.element = resultDigit;

		/* the "next" reference will point to the rest of the result */

		int newCarry = sum > 10 ? 1 : 0; // carry is 0 or 1	
		Node<Integer> remainingList1 = (list1 == null ? null: list1.next); // advance the list, or return null
		Node<Integer> remainingList2 = (list2 == null ? null: list2.next);  // advance the list, or return null

		// recursive call
		Node<Integer> remainingResult = addLists(remainingList1, remainingList2, newCarry);

		// link the lists
		result.next = remainingResult;

		// the head of the result --> will percolate up the stack trace
		return result;
	}
}
