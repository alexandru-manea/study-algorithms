package algorithms.union_find.programming.percolation;

import java.util.HashSet;
import java.util.Set;

import algorithms.union_find.programming.percolation.specification.WeightedQuickUnionUF;

/**
 * Data type representing a grid of sites, initially all blocked, with the capability of opening sites and checking whether
 * the system percolates. Uses the weighted quick union algorithm to model the problem and answer the question.
 * 
 * @author Alexandru Manea
 *
 */
public class Percolation {

	private int lengthOfOneSideOfGrid;
	private int topVirtualElementUFIndex;
	private int bottomVirtualElementUFIndex;
	
	private WeightedQuickUnionUF unionFindDataStructure; // used to check for percolation
	private SitesGrid sitesGrid; // used to keep track of open or blocked sites
	
	/*
	 * Creates an N-by-N grid with all sites blocked
	 * 
	 */
	public Percolation(int n) {
		
		this.lengthOfOneSideOfGrid = n;
		
		initializeUnionFindDataStructure();
		this.sitesGrid = new SitesGrid(n);
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
		
		this.topVirtualElementUFIndex = 0;
		this.bottomVirtualElementUFIndex = noOfUnionFindElements - 1;
		
		// connect top virtual element to first row
		for (int firstRowElement = 1; firstRowElement <= lengthOfOneSideOfGrid; firstRowElement++) {
			
			unionFindDataStructure.union(topVirtualElementUFIndex, firstRowElement);
		}

		// connect bottom virtual element to last row
		int firstElementInLastRow = transformFromGridIndicesToUFArrayIndex(lengthOfOneSideOfGrid, 1);
		int lastElementInLastRow = transformFromGridIndicesToUFArrayIndex(lengthOfOneSideOfGrid, lengthOfOneSideOfGrid);
		
		for (int lastRowElement = firstElementInLastRow; lastRowElement <= lastElementInLastRow; lastRowElement++) {

			unionFindDataStructure.union(bottomVirtualElementUFIndex, lastRowElement);
		}
	}


	/**
	 * Opens site at row i and column j, if it is not already. 1-index based.
	 * 
	 */
	public void open(int i, int j) {
		
		// check for correct parameters
		if (((i < 1) || (i > lengthOfOneSideOfGrid)) || ((j < 1) || (j > lengthOfOneSideOfGrid))) {
			
			throw new IndexOutOfBoundsException("The indices provided are outside the allowable range.");
		}
		
		/* Mark site as open in the grid */
		
		sitesGrid.openSite(i, j);
		
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
			if (sitesGrid.isSiteOpen(rowIndex - 1, columnIndex))
				surroundingElements.add(new GridPosition(rowIndex - 1, columnIndex));
		
		// if last row, don't add the bottom neighbor
		if ((rowIndex + 1) <= lengthOfOneSideOfGrid)
			if (sitesGrid.isSiteOpen(rowIndex + 1, columnIndex))
				surroundingElements.add(new GridPosition(rowIndex + 1, columnIndex));
		
		// if leftmost column, don't add the left neighbor
		if ((columnIndex - 1) >= 1)
			if (sitesGrid.isSiteOpen(rowIndex, columnIndex - 1))
				surroundingElements.add(new GridPosition(rowIndex, columnIndex - 1));
		
		// if rightmost column, don't add the right neighbor
		if ((columnIndex + 1) <= lengthOfOneSideOfGrid)
			if (sitesGrid.isSiteOpen(rowIndex, columnIndex + 1))
				surroundingElements.add(new GridPosition(rowIndex, columnIndex + 1));
		
		return surroundingElements;
	}
	
	
	/**
	 * Checks whether site at row i and column j is open. 1-index based.
	 * 
	 */
	public boolean isOpen(int i, int j) {

		// check for correct parameters
		if (((i < 1) || (i > lengthOfOneSideOfGrid)) || ((j < 1) || (j > lengthOfOneSideOfGrid))) {

			throw new IndexOutOfBoundsException("The indices provided (" + i + ", " + j + ") are outside the allowable range.");
		}
		
		return sitesGrid.isSiteOpen(i, j);
	}
	
	/**
	 * Checks whether site at row i and column j is full. 1-index based.
	 * A full site is 
	 * - an open site which
	 * - is connected to the top row directly or indirectly via open sites.
	 * 
	 */
	public boolean isFull(int i, int j) {
		
		// check for correct parameters
		if (((i < 1) || (i > lengthOfOneSideOfGrid)) || ((j < 1) || (j > lengthOfOneSideOfGrid))) {

			throw new IndexOutOfBoundsException("The indices provided are outside the allowable range.");
		}
		
		return (isOpen(i, j) && unionFindDataStructure.connected(transformFromGridIndicesToUFArrayIndex(i, j), topVirtualElementUFIndex));
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
				+ columnIndex;                        // where we are in the current row
				
	}
	
	/**
	 * Checks whether the system percolates by checking whether the top virtual element and the bottom virtual element
	 * are connected in the underlying union-find data structure.
	 * 
	 */
	public boolean percolates() {
		
		if (lengthOfOneSideOfGrid == 1 || lengthOfOneSideOfGrid == 2)
			return false;
		
		return unionFindDataStructure.connected(topVirtualElementUFIndex, bottomVirtualElementUFIndex);
	}
	
	/**
	 * Private inner class representing the grid of sites. Encapsulates the transformation of indices from a 1-based
	 * system to a 0-based one.
	 * 
	 */
	private class SitesGrid {
		
		private boolean[][] openState; // used to keep track of which sites are open/blocked
		
		public SitesGrid(int gridSize) {
			
			openState = new boolean[gridSize][gridSize];
		}
		
		
		/**
		 * Open site at specified indices.
		 * 
		 */
		public void openSite(int rowIndex, int columnIndex) {
			
			
			openState[rowIndex - 1][columnIndex - 1] = true;
		}
		
		/**
		 * Check whether a site is open at the specified position.
		 * 
		 */
		public boolean isSiteOpen(int rowIndex, int columnIndex) {
			
			return openState[rowIndex - 1][columnIndex - 1];
		}
	}
	
	/**
	 * Private inner class used when computing surrounding elements for an element in the grid.
	 *
	 */
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
	
	
public static void main(String[] args) {
		
		Percolation percolation = new Percolation(3);
		
		System.out.println("Is (1, 1) full after 0 calls to open? " + percolation.isFull(1, 1));
		
		System.out.println("Opening (1, 1)...");
		percolation.open(1, 1);
		
		System.out.println("Is (1, 1) full after 1 call to open? " + percolation.isFull(1, 1));
	}	
	
	
	
}
