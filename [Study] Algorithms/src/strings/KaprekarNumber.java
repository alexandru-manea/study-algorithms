package strings;

/**
 * TASK: Check whether a number is a Kaprekar number.
 * 
 * A Kaprekar number is a positive whole number n with d digits, such that when we split its square into two pieces:
 *  - a right hand piece r with d digits and 
 *  - a left hand piece l that contains the remaining d or d-1 digits
 * the sum of the pieces is equal to the original number (i.e. l + r = n).
 * 
 * @HackerRank
 * 
 */

public class KaprekarNumber {

	/**
	 * Key: consider the long squared number as a string and manipulate it like this.
	 * 
	 */
	public static boolean isKaprekarNumber(int n) {
		
		// compute number of digits - length of equivalent string
        int d = String.valueOf(n).length();
        
        long nSquared = ((long)n) * ((long)n);
        
        /* Cut n^2 in two parts (digit-wise) */
        
        String nSquaredString = String.valueOf(nSquared);
        int nSquaredDigits = nSquaredString.length();
        
        int r = 0;
        int l = 0;
        
        // special case - n in [1, 9]
        if (nSquaredDigits == d) {
            r = Integer.valueOf(nSquaredString);
            l = 0;
        } else {
            r = Integer.valueOf(nSquaredString.substring(nSquaredDigits - d)); // last d digits
            l = Integer.valueOf(nSquaredString.substring(0, nSquaredDigits - d)); // first d or d-1 digits
        }
        
        // check Kaprekar number
        if (r + l == n) {
            return true;
        } else {
            return false;
        }
	}
}
