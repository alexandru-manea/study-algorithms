package strings;

/**
 * TASK :: The cipher rotates every letter in a string by a fixed number, K. The cipher only encrypts letters; symbols, such as -, remain unencrypted.
 * 
 * Given a string, S, and a number, K, encrypt S and print the resulting string. 
 * 
 * @HackerRank
 *
 */

public class RotationCipher {

	/**
	 * Rotate char by char. Add k, but subtract as many multiples of the uppercase or lowercase span as necessary to bring the value in the range.
	 * 
	 */
	public static void encrypt(String s, int k) {
		
		int n = s.length();
		
		// consider each character
		for (int i = 0; i < n; i++) {
            int charCode = (int)s.charAt(i);
            
            int newCharCode = charCode;
            
            // uppercase letters
            if (charCode >= 65 && charCode <= 90) {
                // add k
            	newCharCode += k;
                // but in a circular manner so we end up in the same uppercase letters range
            	while (newCharCode > 90) {
                    newCharCode -= (90 - 65 + 1);
                }
            // lowercase letters - same as for uppercase   
            } else if (charCode >= 97 && charCode <=122) {
                newCharCode += k;
                while (newCharCode > 122) {
                    newCharCode -= (122 - 97 + 1);
                }
            }
            
            System.out.print((char)newCharCode);
        }
	}
	
}