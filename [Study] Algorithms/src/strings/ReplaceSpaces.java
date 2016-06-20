package strings;

/**
 *  TASK :: Replace all spaces in a string with 'x'. 
 *  
 *  Note --> Original problem was solved by the author
 *  as to replace <single> spaces with '%20'. To accomplish
 *  this, we need to increase the storage space, so a new
 *  array has to be created.
 *
 */

public class ReplaceSpaces
{
	
	/**
	 *  MINE
	 *  
	 *  Iterate through the char array, keeping a current index
	 *  of the last written position, alongside the index (i) in
	 *  the original array. If a non-space character is encountered, 
	 *  it is copied to the current index, and both indices are 
	 *  advanced. If a space is encountered, the original index
	 *  is incremented until all the spaces have been iterated
	 *  over, then an 'x' is written at the current index, which
	 *  is afterwards incremented.
	 *  
	 *  Time: O(n)
	 *  Space: O(1)
	 */
	
	public static String replaceSpaces(String string)
	{
		// safety check
		if (string == null)
			return null;
		
		char[] charArray = string.toCharArray();
		int size = charArray.length;

		int currentIndex = 0; // the last written position in the new array
		int i = 0; // index which goes through original characters
		
		// consider all chars in original array
		while (i < size)
		{
			// if non-space char
			if (charArray[i] != ' ')
			{
				charArray[currentIndex] = charArray[i];
				i++;
			}
			else // if space char
			{
				charArray[currentIndex] = 'x';
				
				// all spaces are replaced by a single 'x'
				do
				{
					i++;
				} 
				while (charArray[i] == ' ');
			}
			
			// advanced position to write in
			currentIndex++;
		}
		
		return new String(charArray, 0, currentIndex);
	}
	
	
	public static void main(String[] args)
	{
		
		System.out.println(replaceSpaces("ab   cd r       f dddre v"));
		
	}
}
