package analysis;

public class TheoryOfAlgorithms {

	
	/**
	 * TYPES OF ANALYSIS
	 * -----------------
	 * 
	 * # BEST CASE
	 * -> lower bound on cost
	 * -> determined by easiest input
	 * -> provides a goal for all inputs
	 * -> uses Big Omega (Ω) notation 
	 * 
	 * # WORST CASE
	 * -> upper bound on cost
	 * -> determined by most difficult input
	 * -> provides a guarantee for all input
	 * -> uses big O notation
	 * 
	 * # AVERAGE CASE
	 * -> expected cost for random input
	 * -> needs definition of random input
	 * -> provides a way to predict performance
	 * -> uses big Theta (Θ) notation
	 * 
	 */
	
	
	/**
	 * EXAMPLE OF ANALYSIS - 3-SUM
	 * ---------------------------
	 * 
	 * LOWER BOUND -- proof that no algorithm can do better
	 * 			   -- Ω(N), have to examine all N entries
	 * 
	 * UPPER BOUND -- a specific algorithm, for example the earlier improved N^2*log(N) algorithm
	 * 			   -- O(N^2*log(N))
	 * 
	 * OPEN PROBLEMS -- optimal solution?
	 * 
	 */
}
