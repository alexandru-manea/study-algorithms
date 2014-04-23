package data_structures.stacks_and_queues.$implementations;

public class Stacks {

	
	/**
	 * STACK
	 * -----
	 * 
	 * ***********************************************************
	 * COLLECTION OF OBJECTS IN WHICH THE ORDER OF REMOVAL IS LIFO
	 * ***********************************************************
	 * 
	 * 
	 * OBS> SEPARATE INTERFACE FROM IMPLEMENTATION
	 * 
	 * -> Clients don't know beforehand preferred implementation => clients has a choice
	 * -> Implementors don't know beforehand needs of clients => many clients can reuse the same implementation
	 * -> Create modular, reusable libraries
	 * 
	 */
	
	
	/*
	 * Stack API
	 * 
	 */
	interface Stack<T> {
		
		public void push(T item);
		public T pop();
		public boolean isEmpty();
	}
	
	
	/**
	 * LINKED-LIST IMPLEMENTATION
	 * --------------------------
	 * 
	 * -> Maintain pointer to the first node in a linked list
	 * -> Insert and remove from the front
	 * 
	 * 
	 * PERFORMANCE
	 * ===========
	 * 
	 * SPEED (worst case) --> All operations are O(1)
	 * MEMORY --> ~40N bytes for a stack with N items, excluding memory for item objects
	 * 
	 */
	
	/*
	 * Linked-list implementation of stack
	 * 
	 */
	class LinkedListStack<T> implements Stack<T> {

		private Node<T> head; // pointer to start of linked list
		
		public LinkedListStack() {
			
			this.head = null;
		}
		
		
		@Override
		public void push(T item) {

			// save pointer to previous head
			Node<T> previousHead = head;
			
			// construct a new node to act as the new head
			head = new Node<T>();
			head.setItem(item);
			head.setNext(previousHead);
		}

		
		@Override
		public T pop() {

			// save the item currently in head
			T item = head.getItem();
			
			// advance the head pointer
			head = head.getNext();
	
			return item;
		}

		@Override
		public boolean isEmpty() {

			return (head == null);
		}
		
		
		/*
		 * Inner class for node components.
		 */
		private class Node<T> {
			
			private T item;
			private Node<T> next;
			
			public T getItem() {
				return item;
			}
			public void setItem(T item) {
				this.item = item;
			}
			public Node<T> getNext() {
				return next;
			}
			public void setNext(Node<T> next) {
				this.next = next;
			}
		}
	}
	
	
	/**
	 * RESIZING ARRAY IMPLEMENTATION
	 * ------------------------------
	 * 
	 * -> Use an array to store the N items
	 * -> Push :: add new item at array[N]
	 * -> Pop  :: remove item from array[N-1]
	 * 
	 * INCREASING THE ARRAY
	 * ====================
	 *  - Need a technique for increasing the capacity of the underlying array
	 *  - Best performance: DOUBLE ARRAY CAPACITY every time it runs out of space
	 *  	Compare:
	 *         a). Inserting N elements in an (initially 0-length) array and increasing capacity by 1 each time
	 *             1 + 2 + ... + N ~= N^2/2
	 *         b). Do the same, but double the array each time it runs out of space
	 * 			   2 + 4 + ... + n ~= 3N
	 * 
	 * SHRINKING THE ARRAY
	 * ===================
	 * 
	 * - Need a technique for shrinking the space utilized by the array when it gets unoccupied by pop operations
	 * - Best performance: HALVE ARRAY CAPACITY when IT IS ONE QUARTER-FULL
	 * 
	 * 
	 * PERFORMANCE
	 * ===========
	 * 
	 * SPEED (worst case) --> All operations are O(N) in the worst case, but overall, after a number of operations, the
	 * 						  amortized cost will be constant
	 * MEMORY --> ~8N-32N bytes for a stack with N items, excluding memory for item objects
	 * 
	 */
	
	/*
	 * Resizing array implementation of stack
	 * 
	 */
	class LinkedListArray<T> implements Stack<T> {

		private T[] array; // array of elements
		private int head; // pointer to top of stack (next empty location to be pushed on)
		
		public LinkedListArray() {
			
			this.array = (T[]) new Object[1]; // unsafe cast, but only possibility for generic array
			head = 0;
		}
		
		
		@Override
		public void push(T item) {

			// ensure capacity of underlying array
			if (head == array.length) {
				
				// double array size
				resizeArray(2 * array.length);
			}
			
			// add item at the new head location
			array[head] = item;
		}

		
		@Override
		public T pop() {
			
			// move pointer back
			head--;
			
			// get what is on top of the stack 
			T item =  array[head];
			
			// explicitly null out old reference to avoid loitering
			array[head] = null;
			
			// ensure capacity of underlying array
			if (head > 0 && head == array.length/4) {
				
				// halve the array size
				resizeArray(array.length / 2);
			}
			
			return item;
		}

		
		@Override
		public boolean isEmpty() {
			
			return (head == 0);
		}
		
		/*
		 * Private method used to ensure the correct capacity of the underlying array.
		 * 
		 */
		private void resizeArray(int newCapacity) {
			
			// create a new array with the required capacity
			T[] newArray = (T[]) new Object[newCapacity];
			
			// copy elements from existing array
			for (int i = 0; i < head; i++) {
				
				newArray[i] = array[i];
			}
			
			// point to the newly created array
			array = newArray;
		}
	}
	
	
	/**
	 * LINKED-LIST VS. ARRAY IMPLEMENTATIONS
	 * -------------------------------------
	 * 
	 * LINKED LIST 
	 * + constant time operations in the worst case
	 * - extra space used to deal with links
	 * 
	 * RESIZING ARRAY
	 * + less wasted space
	 * - operations only take constant amortized cost, i.e. on average is constant, but the speed will fluctuate
	 * 
	 */
	
	
	/**
	 * STACK APPLICATIONS
	 * ------------------
	 * 
	 * -> Parsing in compilers
	 * -> JVM
	 * -> Undo in word processors
	 * -> Back button in Web browser
	 * 
	 * --> Arithmetic expression evaluation
	 * 	   TWO-STACK ALGORITHM (DIJKSTRA)
	 * 	     - value 			 :: push on value stack
	 *       - operator 		 :: push on operator stack
	 *       - left parenthesis  :: ignore
	 *       - right parenthesis :: pop operator and two values, push the result on the value stack
	 */
}
