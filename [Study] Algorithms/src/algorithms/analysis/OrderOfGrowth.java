package algorithms.analysis;

public class OrderOfGrowth {

	
	/**
	 * OBS> The following small set of functions are sufficient to describe the order-of-growth for typical algorithms:
	 * 
	 * -> 1        -- constant     :: number of statements independent of input size N
	 * -> log(N)   --  logarithmic :: divide in half
	 * -> N        -- linear       :: loop
	 * -> N*log(N) -- linearithmic :: divide and conquer
	 * -> N^2      -- quadratic    :: double loop
	 * -> N^3      -- cubic        :: triple loop
	 * -> 2^N      -- exponential  :: exhaustive search
	 * 
	 */
	
	
	/**
	 * PRACTICAL IMPLICATIONS OF ORDER-OF-GROWTH
	 * -----------------------------------------
	 * 
	 * |growth rate               problem size solvable in minutes
	 * 								  1970s			 2000s
	 * |1							   any			  any
	 * |log(N)                         any			  any
	 * |N							   10^6           10^9
	 * |N*log						   10^5			  10^8
	 * |N^2                            10^2           10^4
	 * |N^3                            10^2           10^3
	 * |2^N                             20             30
	 * 
	 * 
	 * => NEED AT MOST LINEARITHMIC ALGORITHM
	 * 
	 */
	
	
	/**
	 * EXAMPLE OF BETTER ORDER-OF-GROWTH
	 * ---------------------------------
	 * 
	 * N^2*LOG(N) ALGORITHM FOR 3-SUM
	 * 
	 * 1. SORT THE NUMBERS
	 * 2. FOR EACH PAIR OF NUMBERS a[i] AND a[j], BINAY SEARCH FOR -(a[i] + a[j])
	 * 
	 */
}
