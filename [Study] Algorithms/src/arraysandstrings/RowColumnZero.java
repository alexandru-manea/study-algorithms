package arraysandstrings;

/**
 *  TASK :: Write an algorithm such that if an element in an MxN matrix is 0, 
 *  its entire row and column is set to 0. 
 *
 */

public class RowColumnZero
{
	
	/**
	 *  MINE AND THEIRS
	 *  
	 *  Cannot do it in one pass through the matrix because we can't 
	 *  modify elements we have yet to read. Then, store the indices
	 *  of the rows/columns that need to be 0 on one iteration and
	 *  set the respective elements in a second one.
	 * 
	 */

	public static void setZeros(int[][] matrix)
	{
		// safety check
		if (matrix == null)
			return;
		
		// get dimensions
		int dim1 = matrix.length;
		int dim2 = matrix[0].length;
		
		// arrays of rows & columns indices
		int[] rows = new int[dim1];
		int[] columns = new int[dim2];
		
		// iterate through matrix and set appropriate indices 
		// in the matrices when a '0' is encountered 
		for (int i = 0; i < dim1; i++)
		{
			for (int j = 0; j < dim2; j++)
			{
				if (matrix[i][j] == 0)
				{
					rows[i] = 1;
					columns[j] = 1;
				}
			}
		}
		
		// re-iterate through the array and set all elements
		// that need to be 0
		for (int i = 0; i < dim1; i++)
		{
			for (int j = 0; j < dim2; j++)
			{
				if ((rows[i] == 1) || (columns[j] == 1))
				{
					matrix[i][j] = 0;
				}
			} 
		}
	}
	
	
	public static void main(String[] args)
	{
		int[][] matrix = {{1, 0, 4}, {3, 5, 6}, {9, 4, 0}};
		
		setZeros(matrix);
		
		for (int i = 0; i < matrix.length; i++)
		{
			
			for (int j = 0; j < matrix[0].length; j++)
			{
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println();
		}
	}
}
