package logic;

/**
 *  TASK :: Print the <largest> number with N digits which has the following properties: 
 *  - Its digits are only 3s and 5s
 *  - The number of 3s is divisible by 5
 *  - The number of 5s is divisible by 3
 *  
 *  If no such number exists, print -1.
 *  
 *  @HackerRank
 *
 */

public class DigitsAndDivisibility {

	/**
	 * The largest number will have all the 5s at the beginning, followed or not by the required number of 3s.
	 * We have to minimize the number of 3s to have as much 5s as possible at the beginning, so depending on 
	 * the total number of digits, we try to take as much possible for the 5s at the beginning, as long as
	 * there are enough left for the 3s (a multiple of 5).
	 * 
	 */
	public static void printLargestNumberWithProperties(int numberOfDigits) {
		
		int r = numberOfDigits % 3;
        
		// 3k => only 5s
        if (r == 0) {
            for (int j = 0; j < numberOfDigits; j++) {
                System.out.print('5');
            }
        // 3k + 1 => print 10 3s at the end to leave out a number divisible by 3 for 5s at the beginning    
        } else if (r == 1) {
            // impossible
        	if (numberOfDigits < 10) {
                System.out.print(-1);
            } else {
                // print 5s
            	for (int j = 0; j < (numberOfDigits - 10); j++) {
                    System.out.print('5');
                }
            	// then print 10 3s
                for (int j = 0; j < 10; j++) {
                    System.out.print('3');
                }
            }
        // 3k + 2 => print 5 3s at the end to leave out a number divisible by 3 for the 5s at the beginning
        } else {
        	 // impossible
             if (numberOfDigits < 5) {
                System.out.print(-1);
             } else {
                 // print 5s
            	 for (int j = 0; j < (numberOfDigits - 5); j++) {
                    System.out.print('5');
                }
            	// print 5 3s 
                for (int j = 0; j < 5; j++) {
                    System.out.print('3');
                }
            }
        }
	}
}