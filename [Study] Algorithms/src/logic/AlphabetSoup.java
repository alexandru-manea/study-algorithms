package logic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * TASK :: COUNT THE NUMBER OF TIMES A GIVEN TOKEN CAN BE CONSTRUCTED FROM THE LETTERS OF A GIVEN STRING
 * 
 * CONSTRAINTS
 * 	- Only uppercase letters and spaces
 *  - At least one letter
 *  - At most 1000 characters
 * 
 */
public class AlphabetSoup {

	/**
	 * Solution
	 * 
	 * Store occurrences of each letter in the string and in the token in maps. 
	 * 
	 * A letter has enough occurrences for <number of occurrences in the string> / <number of occurrences in the token> repetitions of the token.
	 * The minimum of this value of all the letters is the number of tokens we can construct.
	 * 
	 */
	public static int getNumberOfTokensFromString(String string, String token) {
		
		// use a map to count the occurrences of letters in the string (deliberately count all of them, better than checking if we're interested in each one)
		Map<Character, Integer> letterToCountInString = countOccurrencesOfLetters(string);
		
		// use a map to count how many of each letter we need for one token
		Map<Character, Integer> letterToCountInToken = countOccurrencesOfLetters(token);
		
		// use a TreeSet to store the result of the division
		TreeSet<Integer> numberOfTokensForLetter = new TreeSet<Integer>(); 
		
		// traverse the map of letters for the given string and store the value of interest
		for (Map.Entry<Character, Integer> entry: letterToCountInToken.entrySet()) {
			char c = entry.getKey();
			
			// if one of the letters in the token is not present at all in the string, stop the process and return 0
			if (!letterToCountInString.containsKey(c)) {
				return 0;
			}
			
			int countInString = letterToCountInString.get(c);
			int countInToken = entry.getValue();
			
			int enoughFor = countInString / countInToken;
			
			numberOfTokensForLetter.add(enoughFor);
		}
		
		// the answer is the minimum value in the set
		return numberOfTokensForLetter.first();
	}
	
	/**
	 * Helper method which, given a string, returns a map with the number of occurrences for each letter.
	 * 
	 */
	private static Map<Character, Integer> countOccurrencesOfLetters(String string) {
		
		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char c: string.toCharArray()) {
			if (!letterToCount.containsKey(c)) {
				letterToCount.put(c, 1);
			} else {
				// do it explicitly to avoid bugs
				int oldCount = letterToCount.get(c);
				int newCount = oldCount + 1;
				letterToCount.put(c, newCount);
			}
		}
		
		return letterToCount;
	}
	
	
	// Dummy test client
	public static void main(String[] args) {
		
		// 1
		String string1 = "WELCOME TO FACEBOOK HACKERCUP";
		String token1 = "HACKERCUP";
		System.out.println(getNumberOfTokensFromString(string1, token1));
		
		// 2
		String string2 = "CUP WITH LABEL HACKERCUP BELONGS TO HACKER";
		String token2 = "HACKERCUP";
		System.out.println(getNumberOfTokensFromString(string2, token2));
		
		// 0
		String string3 = "MOVE FAST BE BOLD";
		String token3 = "HACKERCUP";
		System.out.println(getNumberOfTokensFromString(string3, token3));
		
		// 3
		String string4 = "AM FOST ACOLO SI AM VAZUT MULTI IDIOTI CASE SE BENOCLAU LA O SCENA PLINA DE PROSTI";
		String token4 = "MIAU";
		System.out.println(getNumberOfTokensFromString(string4, token4));
	}
}
