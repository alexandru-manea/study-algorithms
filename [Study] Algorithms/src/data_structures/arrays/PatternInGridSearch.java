package data_structures.arrays;

/**
 * TASK :: Given a 2D array of digits, try to find the occurrence of a given 2D pattern of digits.
 * 
 * A 2D pattern of P digits is said to be present in a larger grid G, if the latter contains a contiguous, rectangular 2D grid of digits matching 
 * with the pattern P.
 * 
 * Example
 * 
 * 1234567890  
 * 0987654321  
 * 1111111111  
 * 1111111111  
 * 2222222222  
 * 
 * Assume we need to look for the following 2D pattern (which IS present in the grid above):
 *
 * 876543  
 * 111111  
 * 111111
 * 
 * 
 * @HackerRank
 *
 */

public class PatternInGridSearch {

	/**
	 * Check each possible sub-grid against the given pattern.
	 * 
	 * R, C, r, c are the number of rows and columns of the grid and pattern, respectively. 
	 * 
	 */
	public static void lookForPatternInGrid(int R, int C, String[] grid, int r, int c, String[] pattern) {
        
		boolean found = false;
		
        // take each possible upper-left corner of the pattern replication in the grid
        outerRow: for (int i = 0; i <= (R - r); i++) {
           outerCoulumn: for (int j = 0; j <= (C - c); j++) {
                
                // traverse the possible pattern and stop at the first difference
                innerRow: for (int k = 0; k < r; k++) {
                    innerColumn: for (int l = 0; l < c; l++) {
                        
                        if (grid[i + k].charAt(j + l) != pattern[k].charAt(l)) {
                            continue outerCoulumn;
                        }
                    }
                }
               
               // if this point is reached, a pattern match was found - stop
               System.out.println("YES");
               found = true;
               break outerRow;
            }
        }
        
        if (!found) {
            System.out.println("NO");
        }
	}
}
