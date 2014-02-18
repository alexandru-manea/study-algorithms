package unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem_DynamicConnectivity {

	
	/**
	 * -> Given a set of N objects
	 *    # UNION COMMAND :: connect two objects
	 *    # CONNECTED QUERY :: is there a path connecting two objects
	 *    
	 *   e.g. very simple example
	 *    
	 *   0    1----2   3-----4
	 *                 |     | 
	 *   5----6    7   8     9 
	 *   
	 *   union(4, 3)
	 *   union(3, 8)
	 *   union(6, 5)
	 *   union(9, 4)
	 *   union(2, 1)
	 *   
	 *   connected(0, 7) --> NO
	 *   connected(8, 9) --> YES	
	 *   
	 *   SO THE PROBLEM OF DYNAMIC CONNECTIVITY IS HOW TO EFFIECIENTLY SUPPORT THOSE TWO COMMANDS.
	 * 
	 */
	
	
	/**
	 * MODELLING THE OBJECTS
	 * ---------------------
	 * 
	 * Examples in real life:
	 * - Pixels in a digital photo
	 * - Computers in a network
	 * - Friends in a social network
	 * - Metallic sites in a composite system
	 * etc.
	 * 
	 * Convention :: when programming, abstract each object to an integer 0 to N, so it can be used as an array index.
	 * 
	 */
	
	
	/**
	 * MODELLING THE CONNECTIONS
	 * -------------------------
	 * 
	 * *********************************************
	 * <IS CONNECTED TO> IS AN EQUIVALENCE RELATION:
	 * - REFLEXIVE
	 * - SYMMETRIC
	 * - TRANSITIVE
	 * *********************************************
	 */
	
	 
	/**
	 * ****************************************************************************
	 * <CONNECTED COMPONENTS> :: MAXIMAL SET OF OBJECTS THAT ARE MUTUALLY CONNECTED
	 * ****************************************************************************
	 * 
	 *  e.g.
	 * 
	 *  0   1   2---3
	 *     /    |  /|
	 *    /     | / |
	 *   /      |/  |
	 *  4---5   6   7
	 * 
	 * Connected components: {0} {1 4 5} {2 3 6 7}
	 * 
	 */
	
	
	/**
	 * IMPLEMENTING THE OPERATIONS USING CONNECTED COMPONENTS
	 * ------------------------------------------------------
	 * 
	 * # UNION COMMAND
	 * -> replace components containing two objects with their union
	 *  
	 *  e.g.
	 * 
	 *   0   1   2---3                   0   1   2---3
	 *      /    |  /|                      /   /|  /|             
	 *     /     | / |   UNION(2, 5) =>    /   / | / |
	 *    /      |/  |                    /   /  |/  |
	 *   4---5   6   7                   4---5   6   7
	 * 
	 *  Connected components: {0} {1 4 5} {2 3 6 7} => {0} {1 2 3 4 5 6 7}
	 *  
	 *  # FIND QUERY
	 *  -> check if two objects are in the same components
	 *  
	 */

	
	/*
	 * UNION-FIND DATA TYPE (API)
	 * --------------------------
	 * 
	 * GOAL :: Design efficient data structure for union-find
	 *         - number of objects can be huge
	 *         - number of operations can be huge
	 *         - find queries with union commands may be intermixed
	 *         
	 * A mock class is presented bellow to illustrate the data type.
	 * 
	 */
	static class UnionFind {
		
		// initialise the union-find data structure with n objects (0 to n-1)
		public UnionFind(int n) {}
		
		// add connection between p and q
		void union(int p, int q) {}
		
		// are p and q in the same component?
		boolean connected(int p, int q) {return false; /*mock*/}
	}

	
	/*
	 * DYNAMIC CONNECTIVITY CLIENT
	 * ---------------------------
	 * 
	 * To test our algorithms, we will need a client to construct the union-find data structure. A sketch is presented.
	 * 
	 */
	static void constructUnionFindDataStructure() {

		// construct a scanner for an input file
		Scanner scanner = null;
		try {

			scanner = new Scanner(new File("tinyUF.txt"));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// read the number of objects from the standard input
		int n = Integer.parseInt(scanner.nextLine());

		// initialise a union-find data structure
		UnionFind unionFind = new UnionFind(n);

		/* Repeatedly read in pairs of integers (object identifiers). If they are not yet connected, connect them
		 * and print out the pair */

		while (scanner.hasNextLine()) {

			// read integers
			String pair = scanner.nextLine();
			String[] objects = pair.split(" ");
			int p = Integer.parseInt(objects[0]);
			int q = Integer.parseInt(objects[1]);

			// connect them if not already
			if (!unionFind.connected(p, q)) {

				unionFind.union(p, q);
				System.out.println(p + " " + q);
			}
		}
	}
}