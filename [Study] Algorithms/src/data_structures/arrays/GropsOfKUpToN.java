package data_structures.arrays;

/**
 * TASK :: Given an integer n, and another one k, with k < n, write a linear-time algorithm to populate an array like this:
 * 
 *   00...0 11...1 ... 11...1
 *   |____| |____|     |____|  
 *     k      k          k
 *   |______________________|
 *             n  
 *             
 * Restriction --> Cannot use if statements, can only use one for loop and the only conditions in the loop are of the
 * form i from 1 to n.
 *  
 */

public class GropsOfKUpToN {

	public static int[] repeatGroupsOfKUpToN(int n, int k) {
		
		int[] result = new int[n];
		
		for (int i = 0; i < n; i++) {
			
			result[i] = (i / k) % 2; // Important
		}
		
		return result;
	}
	
	// Test client
	public static void main(String[] args) {
		
		int[] result = repeatGroupsOfKUpToN(10, 3);
		
		for (int i = 0; i < result.length; i++) {
			
			System.out.print(result[i] + " ");
		}
	}
}
