package data_structures.stacks_and_queues.$implementations;

public class Queues {

	
	/**
	 * QUEUE
	 * -----
	 * 
	 * ***********************************************************
	 * COLLECTION OF OBJECTS IN WHICH THE ORDER OF REMOVAL IS FIFO
	 * ***********************************************************
	 * 
	 */
	
	/*
	 * Qeueue API
	 * 
	 */
	interface Queue<T> {
		
		public void enqueue(T item);
		public T dequeue();
		public boolean isEmpty();
	}
	
	
	/**
	 * LINKED-LIST IMPLEMENTATION
	 * --------------------------
	 * 
	 * -> Maintain pointer to first and last nodes in a linked list
	 * -> Enqueue at back
	 * -> Dequeue at front
	 * 
	 */
	
	/*
	 * Linked-list implementation of queue.
	 * 
	 */
	class LinkedListQueue<T> implements Queue<T> {

		private Node<T> head; // used for dequeue
		private Node<T> tail; // used for enqueue
		
		public LinkedListQueue() {
			
			head = tail = null;
		}
		
		
		@Override
		public void enqueue(T item) {

			// save the previous tail node
			Node<T> previousTail = tail;
			
			// construct a new tail node with the required information
			tail = new Node<T>();
			tail.setItem(item);
			tail.setNext(null);
			
			// if queue is empty
			if (isEmpty()) {
				
				// point the head to the newly constructed tail
				head = tail;
			}
			else {
				
				// link the previous tail to the new one
				previousTail.setNext(tail);
			}
			
		}

		
		@Override
		public T dequeue() {

			// get item now in the head node
			T item = head.getItem();
			
			// advance head to next element in queue
			head = head.getNext();
			
			// if queue is empty
			if (isEmpty()) {
				
				// null out the tail node
				tail = null;
			}
			
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
	 * -> Use an array to store the items in a queue
	 * -> Enqueue :: add new item to array[tail]
	 * -> Dequeue :: remove item from array[head]
	 * -> Update head and tail module the capacity 
	 * 
	 */
}
