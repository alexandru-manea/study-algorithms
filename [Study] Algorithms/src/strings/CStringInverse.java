package strings;

/**
 *  TASK :: Write code to reverse a C-style string
 * 
 *  A C-String means that "abcd" is represented by 5 characters,
 *  including the null character ('\0') - added at the end. 
 *
 */

public class CStringInverse
{
	/**
	 *  MINE AND THEIRS 
	 *  
	 *  Do it in place, by swapping characters in complementary positions.
	 *  The null character has to stay in the same position, so we simply
	 *  assume the array is composed only of the first n-1 characters.
	 *  
	 *  TRICKY --> where to stop in the for loop (Math.ceil(charsSize/2))
	 *  
	 *  Time: O(n)
	 *  Space: O(1)
	 */
	
	public static String inverse1(String string)
	{
		char[] chars = string.toCharArray();
		int originalSize = chars.length;
		int charsSize = originalSize-1; // size of array, bar the last character (null one)
		
		char temp = 'a'; // used for the swapping procedure
		
		for (int i = 0; i < Math.ceil(charsSize/2); i++) // <ceil> function to account for uneven number of elements
		{
			temp = chars[i];
			chars[i] = chars[charsSize-1-i];
			chars[charsSize-1-i] = temp;
		}
		
		return new String(chars);
	}
	
	
	public static void main(String[] args)
	{
		
		System.out.println(inverse1("abcdefghijx".replace('x', '\0')));
		
	}
}
