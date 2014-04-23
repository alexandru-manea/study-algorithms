package linkedlists;

/**
 * Implementation of Stack using a Singly Linked List.
 * 
 * TIME COMPLEXITY: all operations are O(1) - the same as an array implementation. 
 * But - hidden costs of creating and destroying Node objects => array slightly faster.
 * 
 * SPACE COMPLEXITY: same as worst case of array implementation.
 * 
 * NOTE -- we could also implements queues in O(1) time with linked lists, but we need
 * one more pointer to the end (tail) of the list. We can then enqueue at the back (by
 * advancing the tail pointer in the same manner as with the head) and dequeue at the 
 * head (by removing it).
 * 
 */

public class Stack<T>
{
	private LinkedList<T> stack;
	
	public Stack()
	{
		this.stack = new LinkedList<T>();
	}
	
	public void push(T element)
	{
		this.stack.add(element);
	}
	
	public T pop()
	{
		T head = this.stack.getHead();
		this.stack.removeHead();
		
		return head;
	}
	
	public T peek()
	{
		return this.stack.getHead();
	}
	
	public boolean empty()
	{
		return this.stack.empty();
	}
}
