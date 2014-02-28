package unionfind.programming.percolation;

import unionfind.programming.percolation.specification.StdOut;
import unionfind.programming.percolation.specification.StdRandom;
import unionfind.programming.percolation.specification.StdStats;

/**
 * Class intended for finding statistics related to the percolation threshold of a grid of sites.
 * 
 * @author Alexandru Manea
 *
 */
public class PercolationStats {

	// record all percolation thresholds from the experiments to compute statistics
	private double[] percolationThresholds;
	private int sizeOfGrid;
	
	
	/**
	 * Perform T independent computational experiments on an N-by-N grid and record the percolation threshold obtained.
	 * 
	 */
	public PercolationStats(int N, int T) {
		
		if ((N <= 0) || (T <= 0)) {
			
			throw new IllegalArgumentException("Both the size of the grid and the number of experiments have to be strictly positive");
		}
		
		this.sizeOfGrid = N;
		this.percolationThresholds = new double[T];
		
		for (int i = 0; i < T; i++) {
			
			percolationThresholds[i] = computePercolationThresholdExperiment(N);
		}
	}
	
	/**
	 * Private method which represents one experiment:
	 * - create a percolation grid with all sites blocked
	 * - keep opening sites at random until the system percolates
	 * - record the fraction of open sites when that happens (which represents the percolation threshold)
	 *  
	 */
	private double computePercolationThresholdExperiment(int sizeOfGrid) {
		
		// create a new percolation experiment of required size
		Percolation percolationExperiment = new Percolation(sizeOfGrid);
		
		/* Keep opening sites at random until the system percolates */
		
		while (!percolationExperiment.percolates()) {
			
			int randomRowIndex, randomColumnIndex; 
			
			// find a random location in the grid
			do {
				randomRowIndex = StdRandom.uniform(sizeOfGrid) + 1; // 1-based
				randomColumnIndex = StdRandom.uniform(sizeOfGrid) + 1; // 1-based
			} 
			while (percolationExperiment.isOpen(randomRowIndex, randomColumnIndex));
			
			// open the site found
			percolationExperiment.open(randomRowIndex, randomColumnIndex);
		}
		
		/* Compute and return the fraction of open sites at the moment of percolation -> percolation threshold */
		
		int totalNumberOfSites = sizeOfGrid * sizeOfGrid;
		
		// count the number of open sites
		int numberOfOpenSites = 0;
		for (int rowIndex = 1; rowIndex <= sizeOfGrid; rowIndex++) {
			for (int columnIndex = 1; columnIndex <= sizeOfGrid; columnIndex++) {
				
				if (percolationExperiment.isOpen(rowIndex, columnIndex)) {
					
					numberOfOpenSites++;
				}
			}
		}
		
		// compute the percolation threshold in this case
		double percolationThreshold = ((double)numberOfOpenSites) / ((double)totalNumberOfSites);

		return percolationThreshold;
	}
	
	
	/**
	 * Compute the sample mean of percolation threshold.
	 * 
	 */
	public double mean() {                     
		
		return StdStats.mean(percolationThresholds);
	}
	
	/**
	 * Compute the sample standard deviation of percolation threshold.
	 * 
	 */
	public double stddev() {                  
		
		return StdStats.stddev(percolationThresholds);
	}
	
	/**
	 * Returns lower bound of the 95% confidence interval
	 * 
	 */
	public double confidenceLo() {
		
		return mean() - ((1.96 * stddev()) / Math.sqrt(sizeOfGrid));
	}
	
	/**
	 * Return upper bound of the 95% confidence interval
	 * 
	 */
	public double confidenceHi() {

		return mean() + ((1.96 * stddev()) / Math.sqrt(sizeOfGrid));
	}
	
	
	/**
	 * Test client
	 * 
	 */
	public static void main(String[] args) {
		
		PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		
		StdOut.println("mean = " + percolationStats.mean());
		StdOut.println("stddev = " + percolationStats.stddev());
		StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
	}
}
