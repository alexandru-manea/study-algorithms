package logic;

/**
 * TASK :: Count the number of special problems in a workbook, as defined bellow. 
 * 	- There are n chapters in a workbook
 *	- The i-th chapter has ti problems
 * 	- Each page can hold up to k problems. Only the last page of a chapter may contain fewer than k problems.
 *  - Each new chapter starts on a new page, so a page will never contain problems from more than one chapter.
 *  - All numbering is 1-based
 * 
 *  A problem is special if its index (within a chapter) is the same as the page number where it's located.  
 *
 * @HackerRank
 * 
 */

public class ProblemsAndPageNumbers {

	
	/**
	 * Traverse through the whole book, keeping track of problem numbers in each chapter and overall page numbers. 
	 * 
	 */
	public static int countNumberOfSpecialProblems(int nChapters, int[] nProblemsPerChapter, int maxProblemsPerPage) {
        
		int numberOfSpecialProblems = 0;
		int pageNumber = 1;
		
		for (int i = 0; i < nChapters; i++) {
			
			
			// traverse through the problems in this chapter, checking for special ones, and turning the page as needed
			int currentProblemIndex = 1;
			while (currentProblemIndex <= nProblemsPerChapter[i]) {
				
				// check specialty condition
				if (currentProblemIndex == pageNumber) {
					numberOfSpecialProblems++;
				}
				
				// turn the page if not the last problem
				if ((currentProblemIndex % maxProblemsPerPage == 0) && (currentProblemIndex != nProblemsPerChapter[i])) {
					pageNumber++;
				}
				
				currentProblemIndex++;
			}
            
            // ensure chapter starts on a fresh page
            pageNumber++;
		}
        
		return numberOfSpecialProblems;
    }
}
