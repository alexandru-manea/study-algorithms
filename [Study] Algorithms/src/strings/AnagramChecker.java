package strings;

import java.util.Arrays;

/**
 *  TASK ::  Write a method to decide if two strings are anagrams or not.
 */

public class AnagramChecker
{
	/**
	 *  WEB
	 *  
	 *  Sort the char array and then compare linearly. If at any position,
	 *  the character differ, then the strings are not anagrams.
	 */
	public static boolean areAnagrams(String string1, String string2)
	{
		// safety check
		if (string1.length() != string2.length())
			return false;
		
		char[] chars1 = string1.toCharArray();
		char[] chars2 = string2.toCharArray();
		
		Arrays.sort(chars1);
		Arrays.sort(chars2);
		
		int size = chars1.length;
		
		// compare index-wise
		for (int i = 0; i < size; i++)
		{
			if (chars1[i] != chars2[i])
				return false;
		}
		
		return true;
	}
	
	
	/**
	 *  NOTE
	 *  
	 *  They also have the solution above, but they simply compare
	 *  sort(string1) == sort(string2). They have another solution
	 *  as well, in which they check if the two strings have identical
	 *  counts for each unique character, but the algorithm is rather
	 *  complicated.
	 */
	
	
	public static void main(String[] args)
	{
		System.out.println(areAnagrams("gigi", "iigg"));
	}
	
}
