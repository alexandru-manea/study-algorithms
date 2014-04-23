package data_structures.stacks_and_queues.programming.deques;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Generic data type for deque.
 * 
 * A deque or double-ended queue is a generalization of a stack and a queue that supports inserting and removing items
 * from either the front or the back of the data structure.
 * 
 * The deque implementation must:
 * - support each operation in constant worst-case time and
 * - use space proportional to the number of items currently in the deque
 * 
 * The iterator implementation must:
 * - support the operations next() and hasNext() in a constant worst-case time nad
 * - use a constant amount of extra space per iterator
 * 
 * @author Alexandru Manea
 * 
 */
public class Deque<Item> implements Iterable<Item> {

	private Node<Item> first;
	private Node<Item> last;
	private int size;


	public Deque() {                           

		this.first = null;
		this.last = null;
		this.size = 0;
	}

	
	/**
	 * Returns true if deque is empty.
	 * 
	 */
	public boolean isEmpty() {

		return (size == 0);
	}

	/**
	 * Return the number of items in the deque.
	 * 	
	 */
	public int size() {

		return size;
	}

	/**
	 * Insert the item at the front.
	 * 
	 */
	public void addFirst(Item item) {

		// sanity check
		if (item == null) {
			
			throw new NullPointerException("Cannot add a null element to the deque.");
		}
		
		// create a new node with the passed-in parameter
		Node<Item> newFirst = new Node<Item>(item);
		
		// the first node will have no previous link
		newFirst.previous = null;
		
		// link the new first node to the old one (and the rest of the queue)
		newFirst.next = first;

		// update the reference to the first element
		first = newFirst;

		// if this is the first element added, it is also the last one in the data structure
		if (last == null) {

			last = newFirst;
		}

		// increase the size of the structure
		size++;
	}

	/**
	 * Insert the item at the end.
	 * 
	 */
	public void addLast(Item item) {          

		// sanity check
		if (item == null) {

			throw new NullPointerException("Cannot add a null element to the deque.");
		}

		// create a new node with the passed-in parameter
		Node<Item> newLast = new Node<Item>(item);

		// the last node will have no next link
		newLast.next = null;

		// link the new last node to the old one (and the rest of the queue)
		newLast.previous = last;
		
		// if this is not the first node added to the list, link the previous last element to this one
		if (last != null) {
		
			last.next = newLast;
		}

		// update the reference to the last element
		last = newLast;

		// if this is the first element added, it is also the first one in the data structure
		if (first == null) {

			first = newLast;
		}

		// increase the size of the structure
		size++;
	}

	/**
	 * Delete and return the item at the front.
	 * 
	 */
	public Item removeFirst() {    

		// sanity check
		if (size == 0) {
			
			throw new NoSuchElementException("Attepting to remove from an empty deque");
		}
		
		// get the item in the first node
		Item item = first.item;
		
		// remove the first node by pointing to the next one
		first = first.next;
		
		// update count
		size--;
		 
		return item;
	}

	/**
	 * Delete and return the item at the end.
	 * 
	 */
	public Item removeLast() {                 

		// sanity check
		if (size == 0) {

			throw new NoSuchElementException("Attepting to remove from an empty deque");
		}

		// get the item in the last node
		Item item = last.item;
		
		// remove the last element by pointing to the previous one
		last = last.previous;
		
		// update count
		size--;
		
		return item;
	}

	/**
	 * Return an iterator over the items in order from front to end
	 * 
	 */
	public Iterator<Item> iterator() {

		Iterator<Item> iterator = new Iterator<Item>() {
			
			// pointer used to navigate through the deque
			Node<Item> currentNode = first;
			
			@Override
			public boolean hasNext() {
				
				// check not the last element
				return (currentNode != null);
			}

			@Override
			public Item next() {
				
				Item item = currentNode.item;
				
				// advance pointer
				currentNode = currentNode.next;
				
				return item;
			}

			@Override
			public void remove() {
				
				throw new UnsupportedOperationException("Cannot remove via iterator. Use method in Deque class.");
			}
		};
		
		return iterator;
	}
	
	/**
	 * Private class used to represent nodes in the deque.		
	 * 	
	 */
	private class Node<T> {

		private T item;
		private Node<T> previous;
		private Node<T> next;

		public Node(T item) {

			this.item = item;
		}
	}

	/**
	 * Test client.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Deque<Integer> deque = new Deque<Integer>();
		
		deque.addLast(1);
		deque.addFirst(2);
		deque.addLast(3);
		deque.addFirst(4);
		
		Iterator<Integer> iterator = deque.iterator();
		
		while (iterator.hasNext()) {
			
			System.out.println(iterator.next());
		}
	}
}
