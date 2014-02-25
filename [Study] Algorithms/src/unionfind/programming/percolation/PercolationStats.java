package unionfind.programming.percolation;

public class PercolationStats {

	/**
	 * Perform T independent computational experiments on an N-by-N grid.
	 * 
	 */
	public PercolationStats(int N, int T) {
		
		if ((N <= 0) || (T <= 0)) {
			
			throw new IllegalArgumentException("Both the size of the grid and the number of experiments have to be strictly positive");
		}
	}
	
	/**
	 * Compute the sample mean of percolation threshold.
	 * 
	 */
	public double mean() {                     
		
		
	}
	
	/**
	 * Compute the sample standard deviation of percolation threshold.
	 * 
	 */
	public double stddev() {                  
		
		
	}
	
	/**
	 * Returns lower bound of the 95% confidence interval
	 * 
	 */
	public double confidenceLo() {
		
		
	}
	
	/**
	 * Return upper bound of the 95% confidence interval
	 * 
	 */
	public double confidenceHi() {

		
	}
	
	
	/**
	 * Test client
	 * 
	 */
	public static void main(String[] args) {
		
		
	}
}
