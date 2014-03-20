package analysis;

public class MemoryUsage {

	
	/**
	 * TYPICAL MOMERY USAGE (BYTES)
	 * ----------------------------
	 * 
	 * PRIMITIVE TYPES
	 * ===============
	 * - boolean :: 1
	 * - byte    :: 1
	 * - char    :: 2
	 * - int	 :: 4 
	 * - float   :: 4
	 * - long    :: 8
	 * - double  :: 8
	 * 
	 * 
	 * ARRAY TYPES
	 * -----------
	 * - 24 + xN, where x is the type of elements
	 *
	 * e.g. int[] :: 24 + 4N
	 * 
	 * 
	 * OBJECTS
	 * -------
	 * - object overhead      :: 16
	 * - reference            :: 8
	 * - inner class overhead :: 8
	 * - padding		      :: maximum 8 (round up to multiples of 8)
	 * 
	 * e.g. Date object
	 * class Date {
	 *  int day;
	 *  int month;
	 *  int year;
	 *  ....
	 *  }
	 *  
	 *  => 16 (object overhead) + 4*3 (fields) + 4 (padding) = 32 bytes 
	 * 
	 */
}
