package unionfind;

public class Solution_QuickFind {

	/**
	 * 
	 * ****************************************************************************************************************
	 * QUICK FIND :: EAGER APPROACH TO SOLVING THE UNION-FIND PROBLEM WHICH USES AN INTEGER ARRAY INDEXED BY THE OBJECT 
	 * AS THE SUPPORTING DATA STRUCTURE. 
	 * 
	 * THE INTERPRETATION IS THAT TWO OBJECTS ARE CONNECTED IFF THEIR ENTRIES IN THE ARRAY ARE THE SAME.
	 * ****************************************************************************************************************
	 * 
	 *  e.g.
	 *  
	 *  0   1---2   3---4
	 *  |       |   |   |
	 *  |       |   |   |
	 *  5---6   7   8   9
	 *  
	 *       0 1 2 3 4 5 6 7 8 9
	 * id[]  | | | | | | | | | |   => {0, 5, 6}, {1, 2, 7}, {3, 4, 8, 9}
	 *       0 1 1 8 8 0 0 1 8 8
	 *   
	 * -----------------------------------------------      
	 * # FIND  :: CHECK IF p AND q HAVE THE SAME ENTRY
	 * -----------------------------------------------
	 * 
	 * e.g. id[1] != id[6] => not connected
	 *      id[3] == id[4] => connected
	 * 
	 * ------------------------------------------------------------------
	 * # UNION :: CHANGE ALL ENTRIES WHOSE ID EQUALS id[p] TO VALUE id[q] 
	 * ------------------------------------------------------------------
	 * 
	 * e.g. UNION(1, 6)
	 * 
	 *          0 1 2 3 4 5 6 7 8 9
	 * => id[]  | | | | | | | | | |   => {0, 1, 2, 5, 6, 7}, {3, 4, 8, 9}
	 *          0 0 0 8 8 0 0 0 8 8    
	 *          
	 *           
	 * Implementation of above algorithm bellow.
	 *       
	 */
	
	/*
	 * Quick Find implementation. 
	 * 
	 */
	static class QuickFindUF {
		
		// data structure used
		private int[] ids;
		
		public QuickFindUF(int n) {
			
			this.ids = new int[n];
			
			for (int i = 0; i < n; i++) {
				
				ids[i] = i;
			}
		}
		
		// # FIND
		public boolean connected(int p, int q) {
			
			return (ids[p] == ids[q]);
		}
		
		// # UNION
		public void union(int p, int q) {
			
			int pId = ids[p];
			int qId = ids[q];
			
			for (int i = 0; i < ids.length; i++) {
				
				if (ids[i] == pId) {
					
					ids[i] = qId;
				}
			}
		}
	}
	
	
	/**
	 * COMPLEXITY ANALYSIS OF QUICK-FIND
	 * ---------------------------------
	 * 
	 * Cost model -- number of array read/write FOR 1 OPERATION.
 	 * 
	 * # INITIALIZE :: o(n)
	 * # UNION      :: O(n)
	 * # FIND       :: O(1)
	 * 
	 * *******************************************************************************************************
	 * IN THE QUICK-FIND ALGORITHM, THE FIND OPERATION IS FAST (O(1)), BUT UNION OPERATION IS EXPENSIVE (O(n))
	 * *******************************************************************************************************
	 * 
	 */
}
