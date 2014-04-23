package data_structures.linked_lists.$implementations;


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
		return head.getElement();
	}

	// remove head of list
	public boolean removeHead()
	{
		if (empty())
			return false;

		this.head = this.head.getNext();
		no_elements--;
		return true;
	}

	// add an element (to the front)
	public boolean add(T element)
	{
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(head); 
		this.head = newNode;  
		this.no_elements++;

		return true;
	}

	// remove object from list -- keeping a reference to the previous node
	public void remove1(T object)
	{
		if (head.getElement().equals(object))
		{
			removeHead();
		}
		else
		{
			Node<T> previous = head;
			Node<T> current = head.getNext();

			while (current != null)
			{
				if (current.getElement().equals(object)) 
				{
					previous.setNext(current.getNext());
				}

				previous = current;
				current = current.getNext();
			}

		}

	}

	// remove object from list -- looking at the next node
	public void remove2 (T object)
	{
		if (head.getElement().equals(object))
			removeHead();
		else
		{
			for (Node<T> current = head; current.getNext() != null; current = current.getNext())
			{
				if (current.getNext().getElement().equals(object))
				{
					current.setNext(current.getNext().getNext());
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
			current = current.getNext();

		return current.getElement();
	}

	// iterate over nodes and search for an element
	public boolean contains(T element)
	{
		for (Node<T> current = head; current != null; current = current.getNext())
		{
			if (current.getElement().equals(element))
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
		for (Node<T> current = head; current != null; current = current.getNext())
		{
			System.out.print(current.getElement() + " ");
		}

		System.out.println();
	}

	// Getters and Setters

	public Node<T> getHeadNode() {

		return this.head;
	}

	public int getNo_elements() {
		return no_elements;
	}

	public void setNo_elements(int no_elements) {
		this.no_elements = no_elements;
	}
}
