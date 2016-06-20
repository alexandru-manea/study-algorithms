package logic;

/**
 * TASK :: Given integers a and b, count the number of square integers between them. 
 * 
 * @HackerRank
 * 
 */

public class NumberOfSquares {

	/**
	 * The trick is to get the square roots of the limits and use them to do the calculation.
	 * 
	 */
	public static int getNumberOfSquareIntegers(int a, int b) {
		
		int rootA = (int)Math.ceil(Math.sqrt(a));
        int rootB = (int)Math.floor(Math.sqrt(b));
        
        int result = rootB - rootA + 1;
        
        return result;
	}
}
