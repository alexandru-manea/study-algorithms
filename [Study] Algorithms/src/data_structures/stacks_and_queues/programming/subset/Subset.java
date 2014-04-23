package data_structures.stacks_and_queues.programming.subset;

import java.util.Scanner;

import data_structures.stacks_and_queues.programming.random_queues.RandomizedQueue;

/**
 * Client program that takes a command-line integer k; reads in a sequence of N strings from standard input and
 * prints out exactly k of them, uniformly at random. Each item from the sequence can be printed out at most once. 
 * You may assume that k >= 0 and no greater than the number of strings N on standard input.
 * 
 * % echo A B C D E F G H I | java Subset 3       % echo AA BB BB BB BB BB CC CC | java Subset 8
 * C                                              BB
 * G                                              AA
 * A                                              BB
 *  											  CC
 *  											  BB
 *  											  BB
 *  											  CC
 *  											  BB
 *  
 * The running time of Subset must be linear in the size of the input. You may use only a constant amount of memory plus
 * either one Deque or RandomizedQueue object of maximum size at most N, where N is the number of strings on standard 
 * input. 
 * 
 * For an extra challenge, use only one Deque or RandomizedQueue object of maximum size at most k.
 * 
 * @author Alexandru Manea
 *
 */
public class Subset {

	private static final String STOP_STRING = "/stop";
	
	public static void main(String[] args) {
		
		// read k from parameters
		int k = Integer.parseInt(args[0]);
		
		// use a RandomQueue
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
		
		// size of random queue
		int remainingEmptySpots = k;
		
		/* Read strings from standard input until the special stopping string is encountered */
		
		Scanner scanner = new Scanner(System.in);
		String nextToken = scanner.next();
		
		while (!nextToken.equals(STOP_STRING)) {
			
			/* If k is 1, then we have to use a random queue of size N, so we enqueue all elements */
			if (k == 1) {
				
				randomQueue.enqueue(nextToken);
			}
			/* Otherwise use a random queue of size k, fill it, and then successively dequeue and enqueue until 
			 * all N strings are ate */
			else {
				
				if (remainingEmptySpots > 0) {
					
					randomQueue.enqueue(nextToken);
					
					remainingEmptySpots--;
				}
				else {
					
					randomQueue.dequeue();
					randomQueue.enqueue(nextToken);
				}
			}
			
			// advance to next string
			nextToken = scanner.next();
		}
		
		for (int i = 0; i < k; i++) {
			
			System.out.println("Random element number " + (i + 1) + " --> " + randomQueue.dequeue());
		}
	}
	
}
