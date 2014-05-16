package data_structures.arrays_and_strings;

/**
 * TASK :: Given an integer n, write a linear-time algorithm to generate the following pattern (in an array):
 * 
 * 010010001...
 * |__________|
 *      n 
 *  
 * Restriction --> Cannot use if statements, can only use one for loop and the only conditions in the loop are of the
 * form i from 1 to n.
 *
 */
public class GeneratePatternOfZerosAndOnes {

	public static int[] generatePatternOfZerosAndOnes(int n) {
		
		int[] result = new int[n];
		
		int k = 1;
		int p = k;
		
		for (int i = 0; i < n; i++) {
			
			result[i] = k % 1;
			
			k = (result[i] * p) + (k * (result[i] % 1) - result[i] % 1);
			
			p = p + (result[i] % 1);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		System.out.println(Math.pow(1, 0));
		
		int[] result = generatePatternOfZerosAndOnes(10);
		
		for (int i = 0; i < result.length; i++) {
			
			System.out.print(result[i] + " ");
		}
	}
}
