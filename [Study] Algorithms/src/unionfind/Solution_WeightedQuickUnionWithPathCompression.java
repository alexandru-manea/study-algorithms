package unionfind;

public class Solution_WeightedQuickUnionWithPathCompression {

	
	/**
	 * WEIGHTED QUICK UNION
	 * --------------------
	 * 
	 * ********************************************************************************************************************
	 * WEIGHTED QUICK UNION :: IMPROVEMENT OF QUICK UNION WHICH MODIFIES THE ALGORITHM TO AVOID TALL TREES FOR PERFORMANCE.
	 * 
	 * HOW> 1. KEEP TRACK OF SIZE OF EACH TREE (NUMBER OF OBJECTS)
	 *      2. BALANCE TREES BY LINKING ROOT OF SMALLER TREE TO ROOT OF LARGER TREE
	 *      OBS> LARGER CAN MEAN HEIGHT OR RANK
	 * ********************************************************************************************************************
	 * e.g. 
 	 * 
	 *        -- q
	 *       /  / \ 
	 *      /   ---     
	 *     p             QUICK UNION
	 *    / \
	 *   /   \
	 *  /     \
	 *  ------- 
     *
	 *     p----      
	 *    / \   \
	 *   /   \   q      WEIGHTED QUICK UNION
	 *  /     \ / \ 
	 *  ------- --- 
	 *   
	 */
	
	
	/**
	 * PATH COMPRESSION
	 * ----------------
	 * 
	 * ***************************************************************************************************************
	 * PATH COMPRESSION :: JUST AFTER COMPUTING THE ROOT OF P, SET THE ID OF EACH EXAMINED NODE TO POINT TO THAT ROOT.
	 * ***************************************************************************************************************
	 * 
	 * e.g.
	 * 
	 *  		0					0 		 		  --0				--0--
	 *  		|\				   /|\               / /|\		   	   / /|\ \
	 *  		| \				  / | \             / / | \       	  / / | \ \
	 *  		1  2             8  1  2		   8 6 	1  2	 	 8 6  3  1 2
	 *     	   /|\                 /|\			       /|\                |  |\
	 *    	  / | \				  / | \				  / | \			      |  | \
	 *   	 3 	4  5             3 	4  5		   x 3 	4  5		      7	 4  5
	 *      /|					/|					 |
	 *     / |				   / |				     |
	 *    6  7				x 6  7                   7
	 *    |
	 *    |
	 *  x 8 > p
	 */
	
	/*
	 * Weighted quick union with path compression implementation
	 * 
	 */
	static class WeightedQuickUnion {
		
		// we use two data structures now
		private int[] ids;
		private int[] sizes; // size of each tree (number of elements)
		
		public WeightedQuickUnion(int n) {
			
			this.ids = new int[n];
			this.sizes = new int[n];
			
			for (int i = 0; i < n; i++) {
				
				ids[i] = i;
				sizes[i] = 1;
			}
		}

		// # FIND - same as quick union
		public boolean connected(int p, int q)  {

			return (root(p) == root(q));
		}

		// # UNION
		public void union(int p, int q) {

			int rootOfP = root(p);
			int rootOfQ = root(q);
			
			// if both have the same root, nothing to do
			if (rootOfP == rootOfQ)
				return;
			
			// if p's tree is smaller
			if (sizes[rootOfP] < sizes[rootOfQ]) {
				
				// link root of p to root of q
				ids[rootOfP] = rootOfQ;
				// increase the size of q's tree with p's tree size
				sizes[rootOfQ] += sizes[rootOfP];
			}
			// else if q's tree is smaller
			else {
				
				// link root of q to root of p
				ids[rootOfQ] = rootOfP;
				// increase the size of q's tree with p's tree size
				sizes[rootOfP] += sizes[rootOfQ];
			}
		}

		// private method to find root of tree for given element and to do path compression
		private int root(int i) {

			while (i != ids[i]) {

				ids[i] = ids[ids[i]]; // PATH COMPRESSION
				i = ids[i]; // traverse the tree until the root is found
			}

			return i;
		}
	}
	
	
	/**
	 * COMPLEXITY ANALYSIS OF WEIGHTED QUICK-UNION WITH PATH COMPRESSION
	 * -----------------------------------------------------------------
	 * 
	 * Cost model -- number of array read/write FOR 1 OPERATION.
 	 * 
	 * # INITIALIZE :: O(n)
	 * # UNION      :: O(log* N) (includes cost of finding roots)
	 * # FIND       :: O(log*(n))
	 * 
	 * WHERE> log*n - iterate log function of base 2
	 * 
	 * **********************************************************************************************************
	 * BECAUSE NOW THE DEPTH OF ANY NODE x IS GUARANTEED TO BE AT MOST log*(n), AND HENCE FINDING THE ROOTS TAKES
	 * LOGARITHMIC TIME, WE GET A log*(n) ALGORITHM. 
	 * **********************************************************************************************************
	 * 
	 */
}
