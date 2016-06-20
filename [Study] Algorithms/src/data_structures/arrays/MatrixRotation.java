package data_structures.arrays;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TASK :: You are given a 2D matrix of dimension MxN and a positive integer R. You have to rotate the matrix R times and print the resultant matrix. 
 * Rotation should be in anti-clockwise direction.
 * 
 * OBS> The min of M and N will be even.
 * 
 * @HackerRank
 * 
 */

public class MatrixRotation {

	/**
	 * Key: Rotate each circular contour from the outside of the matrix towards the inside
	 * 
	 */
	public static void rotateMatrix(int[][] array, int M, int N, int R) {

		// there are min(M, N)/2 circulars
		for (int k = 0; k < (Math.min(M, N) / 2); k++) {

			// save the positions in order so we can reconstruct the circular with the rotated values
			Map<Position, Integer> positionToValue = new LinkedHashMap<>(); 

			int row = 0, column = 0;

			// add top row of circular (left to right)
			for (row = k, column = k; column < N-k; column++) {
				positionToValue.put(new Position(row, column), array[row][column]);
			}

			// and right (top to bottom)
			for (row = k+1, column = N-1-k; row < M-1-k; row++) {
				positionToValue.put(new Position(row, column), array[row][column]);
			}

			// and bottom (right to left)
			for (row = M-1-k, column = N-1-k; column >= k; column--) {
				positionToValue.put(new Position(row, column), array[row][column]);
			}

			// and left (bottom to top)
			for (row = M-2-k, column = k; row >= k+1; row--) {
				positionToValue.put(new Position(row, column), array[row][column]);
			}

			int circularCount = positionToValue.size();

			/* Add all elements in the circular to an array with the first spot empty */

			int[] circElements = new int[circularCount+1];

			Collection<Integer> values = positionToValue.values();
			int index = 1; // leave out first element of array
			for (int value: values) {
				circElements[index] = value; 
				index++;
			}

			/* Rotate the elements in the circular using the array */

			// each complete sequence of circularCount rotations will bring the elements in the same place
			int nrOfRotations = R % circularCount;

			for (int i = 0 ; i < nrOfRotations; i++) {
				// move all elements to the left starting from the first one to simulate one rotation
				for (int j = 0; j < circularCount; j++) {
					circElements[j] = circElements[j+1];
				}
				// first element comes to the tail; the first spot will again be used for rotation
				circElements[circularCount] = circElements[0];
			}

			/* Add elements back to matrix from the rotated array in the same order we got them out */

			index = 1; // ignore first element again
			for (Position position: positionToValue.keySet()) {
				array[position.getRow()][position.getColumn()] = circElements[index];
				index++;
			}
		}

		// print resulting matrix
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}

	}

	/**
	 *   Helper class - represents a position in the matrix.
	 *
	 */
	private static class Position {
		private int row;
		private int column;

		public Position(int row, int column) {
			this.row = row;
			this.column = column;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}

		@Override
		public boolean equals(Object another) {

			// check for self comparison
			if ( this == another ) return true;

			// check of the same type
			if ( !(another instanceof Position) ) return false;

			// cast to native object is now safe
			Position anotherPosition = (Position)another;

			// now a proper field-by-field evaluation can be made
			if ((this.getRow() == anotherPosition.getRow()) && (this.getColumn() == anotherPosition.getColumn())) {
				return true;
			} else {
				return false;
			}
		}
	}
}