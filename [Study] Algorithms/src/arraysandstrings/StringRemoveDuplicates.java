package arraysandstrings;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *  TASK :: Design an algorithm to remove the duplicate characters
 *  in a string without using any additional buffer.
 *  
 *  Write test cases for it.
 *
 */

public class StringRemoveDuplicates
{
	
	/**
	 *  THEIR SOLUTION
	 *  
	 *  The method keeps the unique characters found thus far
	 *  to the left of a tail pointer, which starts at position
	 *  1. Starting with the second element, it checks [0, tail)
	 *  to see if the character has been seen before. If it has,
	 *  it keeps the tail in place and continues with the next
	 *  character. If it has not, it assigns the location at tail
	 *  with the current character and shifts the pointer forward.
	 *  
	 *  Time: O(n^2)
	 *  Space: O(1)
	 *  
	 *  ADV: space-efficient
	 *  DISADV: entangled, slow(er)
	 *   
	 */
	
	public static String removeDuplicates1(String string)
	{
		// safety checks
		if (string == null) return null;
		if (string.length() < 2) return string;
		
		char[] charArray = string.toCharArray();
		int size = charArray.length;
		
		int tail = 1; // number of unique chars in the array
		
		// start at the 2nd char and go till the end of the array
		for (int i = 1; i < size; i++)
		{
			int j;
			
			// for every char in outer loop check if it has been
			// already seen. chars in [0, tail) are all unique
			for (j = 0; j < tail; j++)
			{
				if (charArray[i] == charArray[j])
					break; // when we find duplicate
			}
			
			// if j reaches tail, we did not break,
			// so char at pos i is no duplicate
			// so we add it to the unique char list
			// at the last position (tail)
			if (j == tail)
			{
				charArray[tail] = charArray[i]; // add it
				tail++; // increment tail => [0, tail) unique char list
			}
		}
		
		return new String(charArray, 0, tail);
	}
	
	
	/**
	 *  STACKOVERFLOW
	 *  
	 *  Store the characters in a LinkedHashSet. This will only
	 *  keep unique entries and it will preserve the ordering.
	 *  
	 *  Time: O(n)
	 *  Space: O(n)
	 *  
	 *  ADV: straightforward, safe
	 *  DISADV: not in-place
	 */
	
	public static String removeDuplicates2(String string)
	{
		// safety checks
		if (string == null) return null;
		if (string.length() < 2) return string;

		char[] charArray = string.toCharArray();
		
		Set<Character> charSet = new LinkedHashSet<Character>();
		
		for (char c: charArray)
		{
			charSet.add(c);
		}
		
		StringBuilder builder = new StringBuilder();
		for (Character character: charSet)
		{
			builder.append(character);
		}
		
		return builder.toString();
	}
	
	
	/**
	 *  Testing method 
	 */
	public static void test()
	{
		// null
		System.out.println(removeDuplicates1(null));
		System.out.println(removeDuplicates2(null));
		
		// empty string
		System.out.println(removeDuplicates1(""));
		System.out.println(removeDuplicates2(""));
		
		// length-1 string
		System.out.println(removeDuplicates1("a"));
		System.out.println(removeDuplicates2("a"));
		
		// no duplicates
		System.out.println(removeDuplicates1("abcdefgh"));
		System.out.println(removeDuplicates2("abcdefgh"));
		
		// some duplicates
		System.out.println(removeDuplicates1("abadfrgba"));
		System.out.println(removeDuplicates2("abadfrgba"));
		
		// all duplicates
		System.out.println(removeDuplicates1("aaaaaaaaaaaa"));
		System.out.println(removeDuplicates2("aaaaaaaaaaaa"));
		
		// all continuous duplicates
		System.out.println(removeDuplicates1("aaabbb"));
		System.out.println(removeDuplicates2("aaabbb"));
		
		// non-contiguous duplicates
		System.out.println(removeDuplicates1("abababa"));
		System.out.println(removeDuplicates2("abababa"));
	}

	
	public static void main(String[] args)
	{
		test();
	}
}
