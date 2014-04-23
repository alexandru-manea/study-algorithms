package linkedlists;

/**
 * LINKED LISTS 
 * (theory & lightweight implementation toghether with simple methods)
 * 
 * Non-contiguous data => cheaper to add/remove data and to rearrange it.
 * But -- no longer random access.
 * 
 * 
 * DOUBLY LINKED LISTS (JAVA)
 * 
 * Java provides LinkedList<T> implemented as a doubly linked list, i.e. nodes have both previous and next pointers.
 * 
 * 		+ get/insert/delete from head & tail --> O(1)
 * 		+ get(i)				 		     --> O(n) (!!!)
 * 		+ instert(i)/delete(i) 				 --> O(1), once position is found
 * 
 * 
 * LINKED LISTS VS ARRAY LISTS
 * 
 * Linked Lists --> constant time insertions/deletions, but only sequential access.
 * 				--> no need for resizing, so good when we don't know the number of elements
 * 
 * Array Lists --> random access, but insertion/deletions are O(n)
 * 
 * 
 * IMPLEMENTING OTHER DSs
 * 
 * - Lists  	    :: NO, dynamic arrays better
 * - Sorted Lists   :: NO, binary trees better
 * - Stacks 	    :: YES, because we don't waste space like with arrays
 * - Queues 	    :: YES, but circular arrays are probably better 
 * - Hash Tables    :: YES, in separate chaining
 * 
 * 
 * EXAMPLE APPLICATION
 * 
 * A line editor where we do a lot of insertion and deletion (whole lines). We could store the lines as strings
 * in a linked list => fairly efficient implementation.
 * 
 * 
 * SKIP LISTS
 * 
 * Def --> hierarchies of linked lists which allow for binary (as opposed to sequential) search.
 * 
 * ADV --> O(log n) search, similar to binary trees, BUT they allow efficient concurrent access
 * 													 (ConcurrentSkipListSet in Java 6)
 * 
 */


/**
 * A lightweight implementation of a SINGLY LINKED LIST.
 * 
 * - reference to head node
 * - no_elements (sometimes not present)
 * 
 * + getHead()
 * + removeHead()
 * + add(object)
 * + remove(object)
 * + get(i)
 * + contains(object)
 *
 */

public class LinkedList<T>
{
	protected Node<T> head; // IMPORTANT -- <pointer> to the first node
	protected int no_elements;

	public LinkedList()
	{
		this.head = null;
		this.no_elements = 0;
	}


	// get the head of the list
	public T getHead()
	{
		return head.element;
	}

	// remove head of list
	public boolean removeHead()
	{
		if (empty())
			return false;

		this.head = this.head.next;
		no_elements--;
		return true;
	}

	// add an element (to the front)
	public boolean add(T element)
	{
		Node<T> newNode = new Node<T>();
		newNode.element = element;
		newNode.next = head; 
		this.head = newNode;  
		this.no_elements++;

		return true;
	}

	// remove object from list -- keeping a reference to the previous node
	public void remove1(T object)
	{
		if (head.element.equals(object))
		{
			removeHead();
		}
		else
		{
			Node<T> previous = head;
			Node<T> current = head.next;

			while (current != null)
			{
				if (current.element.equals(object)) 
				{
					previous.next = current.next;
				}

				previous = current;
				current = current.next;
			}

		}

	}

	// remove object from list -- looking at the next node
	public void remove2 (T object)
	{
		if (head.element.equals(object))
			removeHead();
		else
		{
			for (Node<T> current = head; current.next != null; current = current.next)
			{
				if (current.next.element.equals(object))
				{
					current.next = current.next.next;
					this.no_elements--;
				}
			}
		}
	}

	// return ith element in the list - O(n)
	public T get (int i)
	{
		if (i > this.no_elements)
			return null;

		Node<T> current = head;

		for (int position = 0; position < i-1; position++)
			current = current.next;

		return current.element;
	}

	// iterate over nodes and search for an element
	public boolean contains(T element)
	{
		for (Node<T> current = head; current != null; current = current.next)
		{
			if (current.element.equals(element))
			{
				return true;
			}
		}

		return false;
	}


	public int size()
	{
		return this.no_elements;
	}

	public boolean empty()
	{
		return (this.head == null);
	}

	public void printList()
	{
		for (Node<T> current = head; current != null; current = current.next)
		{
			System.out.print(current.element + " ");
		}

		System.out.println();
	}
}
