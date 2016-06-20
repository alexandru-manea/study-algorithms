package logic;

/**
 * TASK :: CHECK IF AT LEAST TWO OUT OF THREE BOOLEANS ARE TRUE IN AN OPTIMAL MANNER
 * 
 */
public class AtLeastTwoOutOfThreeBooleans {

	public static boolean atLeastTwo(boolean a, boolean b, boolean c) {
		
		// efficient solution - tests a and b exactly once, and c at most once
		return a ? (b || c) : (b && c);
		
		// NOT, inefficient, BUT clear solution
		// return (a && b) || (b && c) || (a && c);
	}
	
	/**
	 * IMP> Always inline the return statement!
	 * 
	 */
}
