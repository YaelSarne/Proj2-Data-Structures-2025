/**
 * FibonacciHeap
 *
 * An implementation of Fibonacci heap over positive integers.
 *
 */
public class FibonacciHeap
{
	public HeapNode min;
	private int n = 0;
	private int c;
	/**
	 *
	 * Constructor to initialize an empty heap.
	 * pre: c >= 2.
	 *
	 */
	public FibonacciHeap(int c)
	{
		this.c = c;
	}

	/**
	 * 
	 * pre: key > 0
	 *
	 * Insert (key,info) into the heap and return the newly generated HeapNode.
	 *
	 */
	public HeapNode insert(int key, String info) 
	{    
		HeapNode node = new HeapNode();
        node.key = key;
        node.info = info;
        node.rank = 0;
        node.parent = null;
        node.child = null;

        // Initially a single-node circular list
        node.prev = node;
        node.next = node;

        // Merge into root list
        if (min == null) {
            min = node;
        } else {
            // Insert node into root list next to min
            node.next = min.next;
            node.prev = min;
            min.next.prev = node;
            min.next = node;

            if (node.key < min.key) {
                min = node;
            }
        }

        n++;
        return node;
	}

	/**
	 * 
	 * Return the minimal HeapNode, null if empty.
	 *
	 */
	public HeapNode findMin()
	{
		return min;
	}

	/**
	 * 
	 * Delete the minimal item.
	 * Return the number of links.
	 *
	 */
	public int deleteMin()
	{
		return 46; // should be replaced by student code

	}

	/**
	 * 
	 * pre: 0<diff<x.key
	 * 
	 * Decrease the key of x by diff and fix the heap.
	 * Return the number of cuts.
	 * 
	 */
	public int decreaseKey(HeapNode x, int diff) 
	{    
		return 46; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the x from the heap.
	 * Return the number of links.
	 *
	 */
	public int delete(HeapNode x) 
	{    
		return 46; // should be replaced by student code
	}


	/**
	 * 
	 * Return the total number of links.
	 * 
	 */
	public int totalLinks()
	{
		return 46; // should be replaced by student code
	}


	/**
	 * 
	 * Return the total number of cuts.
	 * 
	 */
	public int totalCuts()
	{
		return 46; // should be replaced by student code
	}


	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(FibonacciHeap heap2)
	{
		return; // should be replaced by student code   		
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 *   
	 */
	public int size()
	{
		return n; // should be replaced by student code
	}


	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees()
	{
		return 46; // should be replaced by student code
	}

	/**
	 * Class implementing a node in a Fibonacci Heap.
	 *  
	 */
	public static class HeapNode{
		public int key;
		public String info;
		public HeapNode child;
		public HeapNode next;
		public HeapNode prev;
		public HeapNode parent;
		public int rank;
	}




/////////////////this is not part of this
	@Override
	public String toString() {
		if (min == null) return "(empty heap)";
		StringBuilder sb = new StringBuilder();
		sb.append("Fibonacci Heap:\n");
		HeapNode current = min;
		do {
			appendTree(current, "", true, sb);
			current = current.next;
		} while (current != min);
		return sb.toString();
	}

	private void appendTree(HeapNode node, String prefix, boolean isTail, StringBuilder sb) {
		sb.append(prefix)
		.append(isTail ? "└── " : "├── ")
		.append("(").append(node.key).append(")\n");

		if (node.child != null) {
			HeapNode child = node.child;
			boolean first = true;
			do {
				HeapNode nextChild = child.next;
				boolean isLast = (nextChild == node.child);
				appendTree(child, prefix + (isTail ? "    " : "│   "), isLast, sb);
				child = nextChild;
				first = false;
			} while (child != node.child);
		}
	}

}