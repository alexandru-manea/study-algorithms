package unionfind;

public class Solutions_TimeComplexitySummary {

	
	/**
	 * 
	 * ********************************************************************************************************************
	 * WEIGHTED QUICK-UNION WITH PATH COMPRESSION MAKES IT POSSIBLE TO SOLVE PROBLEMS THAT COULD NOT OTHERWISE BE ADDRESSED
	 * BY PROVIDING A LOGARITHMIC TIME ALGORIHM.
	 * ********************************************************************************************************************
	 * 
	 * e.g. 10^9 unions and finds with 10^9 objects
	 *      => reduced time from 30 years to 6 seconds
	 *      
	 */
	
	
	/**
	 * TIME COMPLEXITY FOR M UNION-FIND OPERATIONS (WITH INITIALIZATION) ON A SET OF N OBJECTS:
	 * ----------------------------------------------------------------------------------------
	 * 
	 * Algorithm									|	Time Complexity
	 * 
	 * QUICK FIND									|  O(M N)	
	 * QUICK-UNION									|  O(M N)
	 * WEIGHTED QUICK UNION WITH PATH COMPRESSION   |  N + M lg*(N)
	 * 
	 * 
	 * [PROVEN FACT] NO LINEAR TIME ALGORITHM EXIST, BUT WQUPC IS IN PRACTICE ALMOST LINEAR.
	 * 
	 */
}
