package algorithms.analysis;

public class Observations {
	
	
	
	/**
	 * EMPIRICAL ANALYSIS
	 * ------------------
	 * 
	 * -> RUN the program for VARIOUS SIZE inputs N
	 * -> MEASURE running time T(N)
	 * 
	 * DATA ANALYSIS
	 * *************
	 * 
	 * # STANDARD PLOT :: Plot T(N) vs. N --> little information
	 * # LOG-LOG PLOT  :: Plot log(T(N)) vs log(N) --> most used
	 * 
	 * The LOG-LOG PLOT is used when we assume that the running time follows a power law relationship with the input size: T(N) = a * N^b
	 * 
	 * log(T(N)) = b * log(N) + log(a)
	 *  
	 *  >> Fit a straight line through the points in the log-log plot
	 *  >> b = slope of line
	 *  >> a = axis intersection
	 * 
	 * PREDICTION AND VALIDATION
	 * *************************
	 * 
	 * -> Try to hypothesize from the values/graphs how the running time relates to the input size
	 * -> Make predictions for untried input sizes and validate the hypothesis
	 *    OBS> Commonly used approach: DOUBLE the input
	 *  
	 *  NOTE> There are also system dependent factors, such as hardware, operating system, software etc.
	 */
	
	
	/**
	 * EXAMPLE -> 3-SUM
	 * ----------------
	 * 
	 * Problem :: Given N distinct integers, how many triplets sum to exactly 0?
	 * Applications :: computational geometry
	 * 
	 */
	
	/*
	 * Brute-force algorithm for 3-SUM
	 * 
	 * Empirical analysis => O(N^3)
	 * 
	 */
	public static int threeSum(int[] numbers) {
		
		int count = 0;
		
		for (int i = 0; i < numbers.length - 2; i++) {
			
			for (int j = i + 1; j < numbers.length - 1; j++) {
				
				for (int k = j + 1; k < numbers.length; k++) {
					
					if (numbers[i] + numbers[j] + numbers[k] == 0) {
						
						count++;
					}
				}
			}
		}
		
		return count;
	}
}