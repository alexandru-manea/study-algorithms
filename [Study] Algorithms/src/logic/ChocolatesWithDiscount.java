package logic;

/**
 * TASK :: Little Bob loves chocolate, and he goes to a store with N dollars in his pocket. The price of each chocolate is
 * C dollars. The store offers a discount: for every M wrappers he gives to the store, he gets one chocolate for free. 
 * How many chocolates does Bob get to eat?
 * 
 * @HackerRank
 * 
 */

public class ChocolatesWithDiscount {

	/**
	 * Compute first how many chocolates Bob gets with the N dollars, then iteratively use the wrappers to get more
	 * free chocolates.
	 * 
	 */
	public static int getNumberOfChocolates(int N, int C, int M) {
		
		int result = N / C;
        
        int wrappers = result;
        while(wrappers >= M) {
            int gotForWrappers = wrappers / M;
            result += gotForWrappers;
            wrappers = (wrappers % M) + gotForWrappers;
        }
        
        return result;
	}
}
