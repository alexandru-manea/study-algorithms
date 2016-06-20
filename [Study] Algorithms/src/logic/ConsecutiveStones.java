package logic;

import java.util.Set;
import java.util.TreeSet;

/**
 * TASK :: Consider a trail of n stones with numbers on them. The first one has number 0, and the following obey the rule: 
 *  - Two consecutive stones have a difference of either a or b 
 * 
 * Find all the possible values for the number on the last stone.
 * 
 * @HackerRank
 *
 */

public class ConsecutiveStones {
	
	/**
	 * Key: the last value will be a combination of multiples of a (x * a) and multiples of b (y * b), with the condition:
	 * 	x + y = n;
	 * 
	 * So we need to find all these possible combinations.
	 * 
	 */
	public static Set<Integer> getAllPossibleLastValues(int n, int a, int b) {
		
		// use a TreeSet to keep the values ordered
		Set<Integer> values = new TreeSet<Integer>(); 
        // find all possible combinations of multiples of a and multiples of b
		for (int k = 0; k < n; k++) {
            int value = (n - k - 1) * a + k * b;
            values.add(value);
        }
        
        return values;
	}
}
