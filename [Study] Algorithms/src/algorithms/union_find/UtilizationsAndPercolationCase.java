package algorithms.union_find;

public class UtilizationsAndPercolationCase {

	
	/**
	 * EXAMPLES OF APPLICATIONS OF UNION-FIND
	 * --------------------------------------
	 * 
	 * - Dynamic connectivity
	 * - Percolation
	 * - Games (Go, Hex)
	 * - Least common ancestor
	 * - Kraskal's minimum spanning tree algorithm
	 * - Image processing
	 * - Algorithms in physics
	 * 
	 */
	
	
	/**
	 * PERCOLATION
	 * -----------
	 * 
	 * MODEL :: N-by-N grid of sites
	 *       :: Each site is open with probability p (or blocked with probability 1-p)
	 *       
	 * **************************************************************************
	 * SYSTEM IS SAID TO PERCOLATE IFF TOP AND BOTTOM ARE CONNECTED BY OPEN SITES.
	 * ************************************************************************** 
	 * 
	 * e.g.
	 * 
	 * N = 8, system percolates
	 * 
	 * [x][x][ ][ ][ ][x][x][x]-> blocked site
	 * [ ][x][x][ ][ ][ ][ ][ ]-> open site
	 * [ ][ ][ ][x][x][ ][ ][x]
	 * [x][x][ ][ ][x][ ][ ][ ]
	 * [x][ ][ ][ ][x][ ][ ][ ]
	 * [x][ ][x][x][x][x][ ][ ]
	 * [ ][x][ ][x][ ][ ][ ][ ]
	 * [ ][ ][ ][ ][x][ ][x][x]
	 * 
	 * PHYSICAL SYSTEMS
	 * - Electricity - conductor site/insulated site => conducts?
	 * - Fluid flow - empty site/blocked site => porous?
	 * - Social interaction - person/empty =? communicates? 
	 * 
	 */
	
	
	/**
	 * 
	 * PERCOLATION PHASE TRANSITION
	 * ----------------------------
	 * 
	 * OBS> Likelihood of percolation depends on probability p.
	 * 
	 * When N is large, theory guarantees a sharp threshold p* with:
	 * - p > p* :: almost certainly percolates
	 * - p < p* :: almost certainly does not percolate
	 * 
	 * *******************************************
	 * QUESTION >>HOW TO FIND OUT THE VALUE OF p*?
	 * *******************************************
	 * 
	 * OBS> Estimated in practice at ~0.6.
	 * 
	 */
	
	
	/**
	 * SOLUTION TO FINDING THE PHASE TRANSITION THRESHOLD: MONTE CARLO SIMULATION
	 * --------------------------------------------------------------------------
	 * 
	 * #1 Initialize whole grid with blocked sites
	 * #2 Open random sites until system percolates (top is connected to bottom)
	 * #3 Value of p* for this run is computed as vacancy percentage
	 * #4 Repeat steps #1-#3 and average over the values of p* obtained
	 * 
	 * OBS> Point #2 needs a way to check whether a system percolates.
	 * 
	 */
	
	
	/**
	 * DYNAMIC CONNECTIVITY SOLUTION FOR CHECKING PERCOLATION
	 * ------------------------------------------------------
	 * 
	 * *******************************************************************************************************************
	 * SYSTEM PERCOLATES IFF ANY SITE ON BOTTOM ROW IS CONNECTED TO A SITE ON THE TOP ROW. TO AVOID CHECKING ALL POSSIBLE
	 * COMBINATIONS, INTRODUCE 2 VIRTUAL SITES ON THE VERY TOP AND VERY BOTTOM AND CONNECT ALL SITES ON THE TOP ROW TO THE
	 * FIRST AND ALL SITES ON THE BOTTOM ROW TO THE SECOND. SYSTEM PERCOLATES IF TOP VIRTUAL SITE IS CONNECTED TO BOTTOM
	 * VIRTUAL SITE.
	 * *******************************************************************************************************************
	 * 
	 * OPENING A NEW SITE >> mark as open and connect it to all adjacent open sites
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
}
