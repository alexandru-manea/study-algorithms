package algorithms.union_find;

public class Solution_QuickUnion {

	
	/**
	 * 
	 * *********************************************************************************************************************
	 * QUICK UNION :: LAZY APPROACH TO SOLVING THE UNION-FIND PROBLEM WHICH USES THE SAME INTEGER ARRAY INDEX BY THE OBJECT.
	 * 
	 * THE INTERPRETATION OF THE ARRAY IN THIS CASE IS THAT IT REPRESENTS A FOREST (MULTIPLE TREES): id[i] IS PARENT OF i
	 * AND THE ROOT OF i IS id[id[...id[i]...]] (KEEP GOING UNTIL IT DOESN'T CHANGE).
	 * *********************************************************************************************************************
	 * 
	 * e.g.
	 * 
	 * 0   1   9   6   7   8
	 *        / \  |
	 *       2   4 5
	 *           |
	 *           3
	 *           
	 *       0 1 2 3 4 5 6 7 8 9
	 * id[]  | | | | | | | | | |   => {0}, {1}, {9, 2, 4, 3}, {6, 5}, {7}, {8}
	 *       0 1 9 4 9 6 6 7 8 9
	 *       
	 * ----------------------------------------------
	 * # FIND  :: CHECK IF p AND q HAVE THE SAME ROOT
	 * ----------------------------------------------
	 * 
	 * e.g. FIND(3, 5) -> root of 3 is 9, root of 5 is 6, so 3 and 5 are not connected
	 * 
	 * -------------------------------------------------------
	 * # UNION :: SET THE ID OF p'S ROOT TO THE ID OF q'S ROOT 
	 * -------------------------------------------------------
	 * 
	 * e.g. UNION(3, 5)
	 * 
	 *          0 1 2 3 4 5 6 7 8 9
	 * => id[]  | | | | | | | | | |   => {0}, {1}, {9, 2, 4, 3, 6, 5}, {7}, {8}
	 *          0 1 9 4 9 6 6 7 8 6    
	 *
	 * Implementation of above algorithm bellow.
	 * 
	 */
	
	/*
	 * Quick union implementation. 
	 * 
	 */
	static class QuickUnionUF {
		
		// same data structure used as in quick find
		private int[] ids;
		
		public QuickUnionUF(int n) {
			
			this.ids = new int[n];
			
			for (int i = 0; i < ids.length; i++) {
				
				ids[i] = i; // set parent of each node to itself in the beginning
			} 
		}
		
		// # FIND
		public boolean connected(int p, int q)  {
			
			return (root(p) == root(q));
		}
		
		// # UNION
		public void union(int p, int q) {
			
			int rootOfP = root(p);
			int rootOfQ = root(q);
			
			ids[rootOfP] = rootOfQ;
		}
		
		// private method to find root of tree for given element
		private int root(int i) {
			
			while (i != ids[i]) {
				
				i = ids[i]; // traverse the tree until the root is found
			}
			
			return i;
		}
	}
	
	/**
	 * COMPLEXITY ANALYSIS OF QUICK-UNION
	 * ---------------------------------
	 * 
	 * Cost model -- number of array read/write FOR 1 OPERATION.
 	 * 
	 * # INITIALIZE :: O(n)
	 * # UNION      :: O(n) (includes cost of finding roots)
	 * # FIND       :: O(n)
	 * 
	 * *******************************************************************************************************
	 * IN THE QUICK-UNION ALGORITHM, BOTH OPERATIONS ARE O(N); BAD PERFORMANCE ESPECIALLY WHEN TREES GET TALL.
	 * ALGORITHM GOOD ONLY AS BASIS FOR MORE ADVANCED ONES.
	 * *******************************************************************************************************
	 * 
	 */
}
