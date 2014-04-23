package data_structures.arrays_and_strings;
import java.util.HashSet;

/**
 *  TASK :: Implement an algorithm to determine if a string has all unique characters.
 */

public class StringUniqueChars
{	
	/**
	 *  MY SOLUTION
	 * 
	 *  Use a HashSet to store all previously seen characters.
	 *  Stop when a duplicate is found.
	 * 
	 *  Time: O(n)
	 *  Space: O(n)
	 * 
	 *  ADV: good time complexity, clear
	 *  DISADV: can do it faster and with less memory if we restrict the char set
	 */
	public static boolean hasUniqueChars1(String string)
	{
		HashSet<Character> uniqueChars = new HashSet<Character>();
		
		for (int i = 0; i < string.length(); i++)
		{
			if (uniqueChars.contains(string.charAt(i)))
			{
				return false;
			}
			
			else
			{
				uniqueChars.add(string.charAt(i));
			}
		}
		
		return true;
	}
	
	/**
	 *  THEIR SOLUTION
	 * 
	 *  Assume the char set is ASCII (DO MENTION TO INTERVIEWER).
	 *  Use a boolean[] in the same manner as above.
	 * 
	 *  Time: O(n)
	 *  Space: O(n)
	 * 
	 *  ADV: slightly faster and more space efficient
	 *  DISADV: restricted set, but can increase storage size for UNICODE
	 */
	public static boolean hasUniqueChars2(String string)
	{
		boolean[] charSet = new boolean[256]; //# of ASCII chars
		
		for (int i = 0; i < string.length(); i++)
		{
			int value = string.charAt(i); // IMPORTANT: the int returned is the ASCII code
			if (charSet[value]) return false;
			charSet[value] = true;
		}
		
		return true;
	}
	
	
	/* FOLLOW UP :: Implement it so no other DSs are used. */
	
	/**
	 *  MINE AND THEIRS
	 * 
	 *  Check every char with every other one for duplicate occurrences.
	 * 
	 *  Time: O(n^2)
	 *  Space: O(1)
	 *  
	 *  ADV: in-place
	 *  DISADV: slower
	 */
	public static boolean hasUniqueCharsFollowUp(String string)
	{
		for (int i = 0; i < string.length()-1; i++)
		{
			for (int j = i+1; j < string.length(); j++)
			{
				if (string.charAt(i) == string.charAt(j))
					return false;
			}
		}
		
		return true;
	}
	
	
	/** NOTES 
	 * 
	 *  1. Could use a bit vector instead of a boolean one in their solution.
	 *  Thus, we could reduce the space usage and use bit manipulation.
	 * 
	 *  2. If we do not care about the string afterwards, we could sort it in
	 *  O(n*log(n)) time and then linearly check for neighbouring chars that
	 *  are identical. Careful - some sorting algorithms require further space.
	 * 
	 * 
	 * */
	
	
	public static void main(String[] args)
	{
		System.out.println(hasUniqueChars1("unique"));
		System.out.println(hasUniqueChars2("unique"));
		System.out.println(hasUniqueCharsFollowUp("unique"));
		
		System.out.println(hasUniqueChars1("abcd"));
		System.out.println(hasUniqueChars2("abcd"));
		System.out.println(hasUniqueCharsFollowUp("abcd"));
	}
}
