package data_structures.stacks_and_queues.programming.random_queues;

import java.util.Iterator;
import java.util.Random;

/**
 * Generic data type for randomized queue.
 * 
 * A randomized queue is similar to a queue, except the item removed is chosen uniformly at random from items in the
 * data structure.
 * 
 * The randomized queue implementation must:
 * - support each randomized queue operation in constant amortized time
 * - use space proportional to the number of items currently in the structure
 * 
 * The iterator implementation must:
 * - support the operations next() and hasNext() in constant worst-case time
 * - use a linear amount of extra memory
 * - maintain the order of two or more iterators mutually independent
 * 
 * 
 * @author Alexandru Manea
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private static final int ARRAY_RESIZING_GROWTH_FACTOR = 2;
	private static final int ARRAY_RESIZING_SHRINKING_LIMIT = 4;
	private static final double ARRAY_RESIZING_SHRINKING_FACTOR = 0.5; 

	private Item[] items;
	private int size; // actual number of elements currently in the structure	
	private int capacity; // size of underlying array

	private Random generator = new Random(); // used to generate a random index in the underlying array


	public RandomizedQueue() {

		this.size = 0;
		this.capacity = 1;
		this.items = (Item[])(new Object[capacity]); 
	}


	/**
	 * Add a new element at the end of the queue.
	 * 
	 */
	public void enqueue(Item item) {

		// increase the array if no more space
		if (size == capacity) {

			ensureCapacity(ARRAY_RESIZING_GROWTH_FACTOR * capacity);
		}

		// add the new element at the end of the array
		items[size] = item;
		
		// increase the number of elements in the structure
		size++;
	}

	/**
	 * Remove and return a random element from the queue.
	 * 
	 */
	public Item dequeue() {

		// pick a random index
		int randomIndex = generator.nextInt(size);

		// get the item at that index
		Item randomItem = items[randomIndex];

		// trick -- put the last item at the index just used
		items[randomIndex] = items[size - 1];

		// null out the last item (now copied to the random position)
		items[size - 1] = null;

		// decrease the size
		size--;

		// shrink the array if necessary
		if ((capacity > 1) && (size > 0) && (capacity/size >= ARRAY_RESIZING_SHRINKING_LIMIT)) {

			ensureCapacity((int)(ARRAY_RESIZING_SHRINKING_FACTOR * capacity));
		}

		return randomItem;
	}

	/**
	 * Return a random item. 
	 * 
	 */
	public Item sample() {

		// pick a random index
		int randomIndex = generator.nextInt(size);

		// get the item at that index
		Item randomItem = items[randomIndex];

		return randomItem;
	}

	public boolean isEmpty() {               

		return (size == 0);
	}

	public int size() {

		return size;
	}

	private void ensureCapacity(int newCapacity) {

		// create a new array with the new capacity
		Item[] newItems = (Item[]) new Object[newCapacity];

		// copy all elements from the old to the new array
		for (int i = 0; i < size; i++) {

			newItems[i] = items[i];
		}

		// replace the old with the new array
		items = newItems;
		
		// update the capacity
		capacity = newCapacity;
	}

	public Iterator<Item> iterator() {

		Iterator<Item> iterator = new Iterator<Item>() {

			// pointer used to navigate through the underlying array
			int currentIndex = -1;

			@Override
			public boolean hasNext() {

				// check whether we've reached the end
				return (currentIndex + 1) < size;
			}

			@Override
			public Item next() {

				// advance pointer
				currentIndex++;
				
				return items[currentIndex];
			}

			@Override
			public void remove() {

				throw new UnsupportedOperationException("Cannot remove via iterator. Use method in Deque class.");
			}
		};

		return iterator;
	}

	
	// Test client
	public static void main(String[] args) {

		RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();
		
		// enqueue 10 elements
		for (int i = 0; i < 10; i++) {
			
			randomQueue.enqueue(i+1);
		}
		System.out.println("SIZE after 10 enqueues: " + randomQueue.size);
		
		
		// iterate through them		
		Iterator<Integer> iterator = randomQueue.iterator();

		while (iterator.hasNext()) {

			System.out.println(iterator.next());
		}
		
		System.out.println("-----------------------------------------");

		
		// deque 5 random elements
		for (int i = 0; i < 5; i++) {
			System.out.println("- Dequeued element " + randomQueue.dequeue());
		}
		
		
		// iterate through them again
		System.out.println("SIZE after 5 deques: " + randomQueue.size);

		iterator = randomQueue.iterator();
		while (iterator.hasNext()) {

			System.out.println(iterator.next());
		}
	}
}
