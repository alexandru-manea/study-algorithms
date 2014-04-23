package data_structures.stacks_and_queues.interview;

import java.util.Stack;


/**
 * INTERVIEW QUESTION :: Implement a queue with two stacks so that each queue operations takes a constant amortized 
 * number of stack operations.
 * 
 * @author Alexandru Manea
 *
 */
public class QueueWithTwoStacks {

	
	/**
	 * SOLUTION
	 * 
	 * -> Keep two stacks, call them inbox and outbox
	 * 
	 * -> QUEUE   :: push element onto inbox
	 * -> DEQUEUE :: if outbox is empty, refill it by popping each element from inbox and pushing it on outbox
	 * 			  :: pop and return top element from outbox 
	 * 
	 * e.g.
	 * 
	 *   % queue(A), queue(B), queue(C), queue(D)
	 *   
	 *     D		
	 *     C
	 *     B
	 *     A
	 *     -		  -
	 *   inbox		outbox
	 *   
	 *   
	 *   % dequeue()
	 *   
	 *   			  A -->          
	 *   			  B			                 B
	 *   			  C		  => 	             C
	 *   			  D			                 D         
	 *     -          -			      -          -
	 *   inbox		outbox		    inbox      outbox
	 *   
	 */
	
	
	/*
	 * Queue implementation using two stacks.
	 * 
	 */
	class Queue<T> {
		
		private Stack<T> inbox;
		private Stack<T> outbox;
		
		public Queue() {
			
			this.inbox = new Stack<T>();
			this.outbox = new Stack<T>();
		}
		
		
		/*
		 * Queue operation -- push element into inbox
		 * 
		 */
		public void queue(T item) {
			
			inbox.push(item);
		}
		
		
		/*
		 * Dequeue operation -- push all items from inbox to outbox and pop the one on the top
		 * 
		 */
		public T dequeue() {
			
			if (outbox.isEmpty()) {
				
				while (!inbox.isEmpty()) {
					
					outbox.push(inbox.pop());
				}
			}
			
			return outbox.pop();
		}
	}
}