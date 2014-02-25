package unionfind.programming.percolation;

import java.util.HashSet;
import java.util.Set;

import unionfind.programming.percolation.specification.WeightedQuickUnionUF;

/**
 * 
 * 
 * @author Alexandru Manea
 *
 */
public class Percolation {

	private int lengthOfOneSideOfGrid;
	private WeightedQuickUnionUF unionFindDataStructure; // used to check for percolation
	private boolean[][] emptyState; // used to keep track of open or full sites TODO Do we need this?
	
	/*
	 * Creates an N-by-N grid with all sites blocked
	 * 
	 */
	public Percolation(int n) {
		
		this.lengthOfOneSideOfGrid = n;
		
		initializeUnionFindDataStructure();
		this.emptyState = new boolean[n][n]; // all closed by default, hence false values
	}
	
	/**
	 * TODO 
	 * @param lengthOfOneSideOfGrid
	 */
	private void initializeUnionFindDataStructure() { 
		
		/*
		 * Initialize union-find data structure.
		 * 
		 * Number of union-find elements:
		 * - lengthOfOneSideOfGrid * lengthOfOneSideOfGrid :: one for each element of the n*n grid
		 * - 2 :: the two virtual elements that we require to use the union find algorithm for percolation
		 */
		
		int noOfUnionFindElements = lengthOfOneSideOfGrid * lengthOfOneSideOfGrid + 2;
		this.unionFindDataStructure = new WeightedQuickUnionUF(noOfUnionFindElements);
		
		
		/* Execute union operations to connect the two virtual elements to the top and bottom elements */
		
		int topVirtualElementId = 0;
		int bottomVirtualElementId = noOfUnionFindElements - 1;
		
		// connect top virtual element to first row
		for (int firstRowElement = 1; firstRowElement <= lengthOfOneSideOfGrid; firstRowElement++) {
			
			unionFindDataStructure.union(topVirtualElementId, firstRowElement);
		}

		// connect bottom virtual element to last row
		for (int lastRowElement = 1; lastRowElement <= lengthOfOneSideOfGrid; lastRowElement++) {

			unionFindDataStructure.union(bottomVirtualElementId, lastRowElement);
		}
	}


	/**
	 * Opens site at row i and column j, if it is not already. 1-index based.
	 * 
	 */
	public void open(int i, int j) throws IndexOutOfBoundsException {
		
		// check for correct parameters
		if (((i < 1) || (i > lengthOfOneSideOfGrid)) || ((j < 1) || (j > lengthOfOneSideOfGrid))) {
			
			throw new IndexOutOfBoundsException("The indices provided are outside the allowable range.");
		}
		
		/* Mark site as open in the grid */
		
		setEmptyState(i, j, true);
		
		/* Make the necessary connections in the union-find data structure */
		
		// get all open sites surrounding the current site
		Set<GridPosition> openSurroundingElements = getOpenSurroundingElements(i, j);
		
		// make unions (in the union-find data structure) between the element to be opened and any open surrounding ones
		for (GridPosition currentNeighbor: openSurroundingElements) {
			
			int toBeOpenedUFPosition = transformFromGridIndicesToUFArrayIndex(i, j);
			int currentNeighborUFPosition = transformFromGridIndicesToUFArrayIndex(currentNeighbor.getRowIndex(), 
																				   currentNeighbor.getColumnIndex());
			
			unionFindDataStructure.union(toBeOpenedUFPosition, currentNeighborUFPosition);
		}
	}
	
	/**
	 * Get all open surrounding elements for an element in the grid:
	 * - top neighbor 
	 * - bottom neighbor
	 * - left neighbor
	 * - right neighbor
	 * 
	 * [(1,1)][(1,2)][(1,3)]
	 * [(2,1)][(2,2)][(2,3)]
	 * [(3,1)][(3,2)][(3,3)]
	 * 
	 * e.g.
	 * for (2,2): {(1,2), (3,2), (2,1), (2,3)}
	 * 
	 */
	private Set<GridPosition> getOpenSurroundingElements(int rowIndex, int columnIndex) {
		
		Set<GridPosition> surroundingElements = new HashSet<GridPosition>();
		
		// if first row, don't add the top neighbor
		if ((rowIndex - 1) >= 1) 
			if (emptyState[rowIndex - 1][columnIndex])
				surroundingElements.add(new GridPosition(rowIndex - 1, columnIndex));
		
		// if last row, don't add the bottom neighbor
		if ((rowIndex + 1) <= lengthOfOneSideOfGrid)
			if (emptyState[rowIndex + 1][columnIndex])
				surroundingElements.add(new GridPosition(rowIndex + 1, columnIndex));
		
		// if leftmost column, don't add the left neighbor
		if ((columnIndex - 1) >= 1)
			if (emptyState[rowIndex][columnIndex - 1])
				surroundingElements.add(new GridPosition(rowIndex, columnIndex - 1));
		
		// if rightmost column, don't add the right neighbor
		if ((columnIndex + 1) <= lengthOfOneSideOfGrid)
			if (emptyState[rowIndex][columnIndex + 1])
				surroundingElements.add(new GridPosition(rowIndex, columnIndex + 1));
		
		return surroundingElements;
	}
	
	
	/**
	 * Checks whether site at row i and column j is open. 1-index based.
	 * 
	 */
	public boolean isOpen(int i, int j) throws IndexOutOfBoundsException {

		// check for correct parameters
		if (((i < 1) || (i > lengthOfOneSideOfGrid)) || ((j < 1) || (j > lengthOfOneSideOfGrid))) {

			throw new IndexOutOfBoundsException("The indices provided are outside the allowable range.");
		}
		
		return getEmptyState(i, j);
	}
	
	/**
	 * Checks whether site at row i and column j is full. 1-index based.
	 * 
	 */
	public boolean isFull(int i, int j) throws IndexOutOfBoundsException {
		
		// check for correct parameters
		if (((i < 1) || (i > lengthOfOneSideOfGrid)) || ((j < 1) || (j > lengthOfOneSideOfGrid))) {

			throw new IndexOutOfBoundsException("The indices provided are outside the allowable range.");
		}
		
		return !(getEmptyState(i, j));
	}
	
	/**
	 * Transforms a location in the percolation grid, given by the indices of row and column, into an index in the
	 * union-find array, which includes all elements in the grid (flattened out) and the two virtual nodes used in 
	 * the Quick Union algorithm.
	 * 
	 * @param i - row index in percolation grid (1-based)
	 * @param j - column index in percolation grid (1-based)
	 * @return index in union-find array
	 */
	private int transformFromGridIndicesToUFArrayIndex(int rowIndex, int columnIndex) { 
		
		return (rowIndex - 1) * lengthOfOneSideOfGrid // all elements from previous rows
				+ columnIndex                         // where we are in the current row
				- 1;                                  // transform from 1-based to 0-based index system
	}
	
	/*
	 * Checks whether the system percolates by checking whether the top virtual element and the bottom virtual element
	 * are connected in the underlying union-find data structure.
	 * 
	 */
	public boolean percolates() {
		
		

	}
	
	/**
	 * Custom getter for the grid elements empty state. Transforms from the 1-based indices in the parameters to the 
	 * 0-based indices of the underlying array data structure.
	 * 
	 */
	private boolean getEmptyState(int rowIndex, int columnIndex) {
		
		return emptyState[rowIndex- 1 ][columnIndex - 1];
	}
	
	/**
	 * Custom setter for the grid elements empty state. Transforms from the 1-based indices in the parameters to the 
	 * 0-based indices of the underlying array data structure.
	 *
	 */
	private void setEmptyState(int rowIndex, int columnIndex, boolean emptyStateValue) {
		
		emptyState[rowIndex- 1 ][columnIndex - 1] = emptyStateValue;
	}
	
	private class GridPosition {
		
		private int rowIndex;
		private int columnIndex;
		
		public GridPosition(int rowIndex, int columnIndex) {
			
			this.rowIndex = rowIndex;
			this.columnIndex = columnIndex;
		}

		public int getRowIndex() {
			return rowIndex;
		}

		public int getColumnIndex() {
			return columnIndex;
		}
	}
}
