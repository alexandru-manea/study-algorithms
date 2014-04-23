package data_structures.linked_lists.interview;

import data_structures.linked_lists.$implementations.Node;

/**
 *  TASK :: Given a circular linked list, implement an algorithm that will return the node at the beggining
 *  of the loop.
 *  
 *  Def --> Circular LL :: A corrupt LL in which a node's next pointer points to a previous node, so as to
 *  					   make a loop.
 *  
 *	
 *	E.g. A -> B -> C -> D -> E -> C
 *	
 *	Output: C
 */

public class NodeAtBeginningOfLoop
{
	/**
	 *  THEIR
	 *  
	 *  
	 *  PRINCIPLE :: If we move two pointers on the LL, one with speed 1, and the other with speed 2, they will
	 *  			 end up meeting, if the LL has a loop. The tricky part is finding the start of the loop.
	 *  
	 *  
	 *  OBS1 :: If the loop starts k nodes from the beggining and we let both nodes move at their own speed,
	 *  		when the slow node will enter the loop, the fast one will have a head start of k in the loop.
	 *  
	 *  		WHY :: In k time steps, slowNode jumps k nodes, while fastNode jumps 2k nodes. slowNode is at
	 *  			   loopStart, fastNode is 2k-k = k nodes ahead
	 *  
	 *  
	 *  OBS2 :: If fastNode has a k head start in the loop, he will meet with slowNode k nodes before the start.
	 *  
	 *          WHY :: At time step 0, slowNode is at startNode and fastNode is k ahead of startNode.
	 *          	   At time step i, slown node is at node i in the loop, while fastNode is at (k + 2*i).
	 *          	   We need the difference between their positions to be a multiple of n for them to be in the
	 *          	   same spot. The first meeting will be at n. So (k + 2i) - i = n => i = n - k, which is k
	 *          	   nodes before the startNode.
	 *          
	 *  ALGORITHM :: Start both slowNode and fastNode from the head. According to OBS1, fastNode will be k nodes 
	 *  			 ahead when slownNode is at loopStart (where k = |loopStart|). Next, according to OBS2, the
	 *  			 two runners will meet in the loop k nodes before loopStart. We keep one of them there and
	 *  			 take the other to the head. Right now, one is k nodes before loopStart (from the head) and
	 *  			 the other is k nodes before loopStart, but in the loop. So, if we start both from these
	 *  			 positions with constant speed of 1, they will meet at loopStart.
	 */
	public static <T> Node<T> findLoopStart (Node<T> head)
	{
		Node<T> slowNode = head;
		Node<T> fastNode = head;
		
		// Find meeting point
		while (fastNode.getNext() != null)
		{
			slowNode = slowNode.getNext();
			fastNode = fastNode.getNext().getNext();
			
			if (slowNode == fastNode)
				break;
		}
		
		// Safety check - if they haven't met => no loop
		if (fastNode.getNext() == null)
			return null;
		
		
		/* Move slowNode to head. Keep fastNode at meeting point. Each are k steps from the loop start.
		 * If they move at the same pace, they must meet at loop start */
		
		slowNode = head;
		
		while (slowNode != fastNode)
		{
			slowNode = slowNode.getNext();
			fastNode = fastNode.getNext();
		}
		
		return fastNode;
	}
}
