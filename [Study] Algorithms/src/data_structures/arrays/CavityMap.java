package data_structures.arrays;

/**
 * 
 * TASK :: You are given a square map of size n×n. Each cell of the map has a value denoting its depth. We will call a cell of the map a cavity 
 * if and only if this cell is not on the border of the map and each cell adjacent to it has strictly smaller depth.
 * 
 * You need to find all the cavities on the map and depict them with the uppercase character X
 * 
 * @HackerRank
 *
 */

public class CavityMap {

	/**
	 * Consider each row is a string, so the whole grid is an array of strings.
	 * 
	 * Search each position, except borders, from the top left to the bottom right.
	 * 
	 */
	public static void findCavities(String[] grid) {
		
		int nRows = grid.length;
		
		// don't search on the borders
        for (int i = 1; i < (nRows - 1); i++) {
            for (int j = 1; j < (nRows - 1); j++) {
                
                // we know this hasn't been checked before, so it's a number
                int value = (int) grid[i].charAt(j);
                
                // get neighbors
                char top = grid[i - 1].charAt(j);
                char bottom = grid[i + 1].charAt(j);
                char left = grid[i].charAt(j - 1);
                char right = grid[i].charAt(j + 1);
                
                // check cavity
                if (((top != 'X') && ((int) top < value)) && 
                    ((bottom != 'X') && ((int) bottom < value)) &&
                    ((left != 'X') && ((int) left < value)) &&
                    ((right != 'X') && ((int) right < value))) {
                    
                    // replace whole row
                    grid[i] = grid[i].substring(0, j)+ 'X' + grid[i].substring(j + 1);
                }
            }   
        }
        
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nRows; j++) {
                System.out.print(grid[i].charAt(j));
            }
            System.out.println();
        }
	}
}
