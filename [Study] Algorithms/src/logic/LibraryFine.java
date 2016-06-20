package logic;

/**
 * TASK :: Given the expected and actual return dates for a library book, create a program that calculates the fine (if any). 
 * 
 * The fee structure is as follows:
 *  - If the book is returned on or before the expected return date, no fine will be charged
 *  - If the book is returned after the expected return day, but still within the same calendar month and year as the expected return date:
 *  	 fine = 15 × (the number of days late)
 *  - If the book is returned after the expected return month, but still within the same calendar year as the expected return date:
 *  	 fine = 500 × (the number of months late)
 *  - If the book is returned after the calendar year in which it was expected, there is a fixed fine of 10000.
 * 
 * @HackerRank
 *
 */

public class LibraryFine {

	/**
	 * Key: make the comparisons starting with the year, then month, then day.
	 *  
	 */
	public static int computeFine(int d1, int m1, int y1, int d2, int m2, int y2) {
		
		int fine = 0;

		if (y1 < y2) {
			fine = 0;
		} else if (y1 > y2) {
			fine = 10000;
		// equal year
		} else {
			if (m1 < m2) {
				fine = 0;
			} else if (m1 > m2) {
				fine = 500 * (m1 - m2);
			}
			// equal year and month
			else {
				if (d1 <= d2) {
					fine = 0;
				} else {
					fine = 15 * (d1 - d2);
				}
			}
		}
		
		return fine;
	}
}
