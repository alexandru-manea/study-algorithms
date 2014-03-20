package analysis;

public class MathematicalModels {

	
	/**
	 * MATHEMATICAL MODELS FOR RUNNING TIME
	 * ------------------------------------
	 * 
	 * **********************************************************
	 * TOTAL RUNNING TIME = SUM OF (COST*FREQUENCY OF OPERATIONS)
	 * **********************************************************
	 * 
	 */
	
	/*
	 * Example: counting instructions as a function of input size N for 1-SUM
	 * 
	 * - variable declaration -> 2
	 * - assignment           -> 2
	 * - less than compare	  -> N + 1
	 * - equal to compare     -> N
	 * - array access		  -> N
	 * - increment			  -> N to 2N
	 * 
	 */
	public static int oneSum(int[] a) {
		
		int N = a.length;
		int count = 0;
		
		for (int i = 0 ; i < N; i++) {
			
			if (a[i] == 0) {
			
				count++;
			}
		}
		
		return count;
	}
	
	/*
	 * Example: counting instructions as a function of input size N for 2-SUM
	 * 
	 * - variable declaration -> 2
	 * - assignment           -> 2
	 * - less than compare	  -> 1/2*(N+1)*(N+2)
	 * - equal to compare     -> 1/2*N*(N-1)
	 * - array access		  -> N*(N-1)
	 * - increment			  -> 1/2*N*(N-1) to N*(N-1)
	 * 
	 */
	public static int twoSum(int[] a) {

		int N = a.length;
		int count = 0;

		for (int i = 0 ; i < N-1; i++) {

			for (int j = i + 1; j < N; j++) {

				if (a[i] + a[j] == 0) {
					
					count++;
				}
			}
		}

		return count;
	}
	
	
	/**
	 * SIMPLIFICATIONS
	 * ---------------
	 * 
	 * We observe that counting instructions can become tedious and hard to do, even for simple situations. We therefore
	 * try to simplify.
	 * 
	 * SIMPLIFICATION 1 -> COST MODEL :: Use some BASIC OPERATION as a proxy for running time
	 * SIMPLIFICATION 2 -> TILDE NOTATION :: IGNORE LOWER ORDER TERMS when estimating running time as a function of N
	 * 
	 * OBS> Use both to simplify counts
	 * e.g. 1-SUM: ~ N array accesses
	 *      2-SUM: ~ N^2 array accesses
	 *      3-SUM: ~ 1/2* N^3 array accesses
	 *      
	 */
	
	
	/**
	 * SOME ESTIMATION FORMULAS
	 * ------------------------
	 * 
	 * # 1 + 2 + ... + N        -> 1/2 * N^2
	 * 
	 * # 1^k + 2^k + ... + N^k  -> 1/(k+1) * N^(k+1)
	 * 
	 * # 1 + 1/2 + ... + 1/N    -> ln N
	 * 
	 */
}
