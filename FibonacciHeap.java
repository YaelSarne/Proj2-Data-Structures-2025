/**
 * FibonacciHeap
 *
 * An implementation of Fibonacci heap over positive integers.
 *
 */
public class FibonacciHeap {
	public HeapNode min;
	private int n = 0;
	private final int c;
	/**
	 *
	 * Constructor to initialize an empty heap. pre: c >= 2.
	 *
	 */
	public FibonacciHeap(int c) {
		this.c = c;
	}

	/**
	 * 
	 * pre: key > 0
	 *
	 * Insert (key,info) into the heap and return the newly generated HeapNode.
	 *
	 */
	public HeapNode insert(int key, String info) {
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
	public HeapNode findMin() {
		return min;
	}

	/**
	 * 
	 * Delete the minimal item. Return the number of links.
	 *
	 */
	public int deleteMin() {
	    if (min == null) {
	        return 0; // Heap is empty, no links performed
	    }

	    int linksMade = 0;

	    // Meld children into the root list and count the links
	    linksMade += meldChildrenIntoRootList(min.child);

	    // Remove min from the root list
	    if (min.next == min) {
	        min = null; // Heap is empty now
	    } else {
	        min.prev.next = min.next;
	        min.next.prev = min.prev;
	        min = min.next; // Update min to a different node
	    }

	    // Consolidate the heap and count additional links
	    HeapNode newMin = consolidate(min);
	    min = newMin;

	    n--;
	    return linksMade; // Return the total number of links made
	}

	private int meldChildrenIntoRootList(HeapNode child) {
	    if (child == null) return 0; // No children to meld

	    int links = 0; // Counter for the number of links made
	    HeapNode current = child;
	    do {
	        current.parent = null; // Detach from parent
	        links++; // Increment the counter for each child
	        current = current.next;
	    } while (current != child);

	    if (min == null) {
	        min = child; // The children become the new root list
	    } else {
	        // Merge the children into the root list
	        HeapNode minNext = min.next;
	        HeapNode childPrev = child.prev;

	        min.next = child;
	        child.prev = min;

	        minNext.prev = childPrev;
	        childPrev.next = minNext;
	    }

	    return links; // Return the total number of links made
	}

	private HeapNode consolidate(HeapNode x) {
	    HeapNode[] B = toBuckets(x);
	    return fromBuckets(B);
	}

	private HeapNode[] toBuckets(HeapNode x) {
	    int maxDegree = (int) Math.ceil(Math.log(n) / Math.log(1.618)) + 1;
	    HeapNode[] B = new HeapNode[maxDegree];

	    // Break the circular linked list
	    x.prev.next = null;

	    while (x != null) {
	        HeapNode y = x;
	        x = x.next;

	        while (B[y.rank] != null) {
	            y = link(y, B[y.rank]);
	            B[y.rank - 1] = null;
	        }
	        B[y.rank] = y;
	    }

	    return B;
	}

	private HeapNode fromBuckets(HeapNode[] B) {
	    HeapNode x = null;

	    for (HeapNode node : B) {
	        if (node != null) {
	            if (x == null) {
	                x = node;
	                x.next = x;
	                x.prev = x;
	            } else {
	                insertAfter(x, node);
	                if (node.key < x.key) {
	                    x = node;
	                }
	            }
	        }
	    }

	    return x;
	}

	private HeapNode link(HeapNode y, HeapNode z) {
	    if (y.key > z.key) {
	        HeapNode temp = y;
	        y = z;
	        z = temp;
	    }

	    // Remove z from root list
	    z.prev.next = z.next;
	    z.next.prev = z.prev;

	    // Make z a child of y
	    z.parent = y;
	    if (y.child == null) {
	        y.child = z;
	        z.next = z;
	        z.prev = z;
	    } else {
	        z.next = y.child.next;
	        z.prev = y.child;
	        y.child.next.prev = z;
	        y.child.next = z;
	    }

	    y.rank++;
	    z.mark = false; // Reset the marked status
	    return y;
	}
	
	private void insertAfter(HeapNode x, HeapNode newNode) {
	    // Insert newNode immediately after x in the circular linked list
	    newNode.next = x.next;
	    newNode.prev = x;
	    x.next.prev = newNode;
	    x.next = newNode;
	}


	private void cascadeCut(HeapNode y) {
		HeapNode z = y.parent;

		if (z != null) {
			y.mark++;

			if (y.mark >= c - 1) {
				cut(y, z);
				cascadeCut(z);
			}
		}
	}



	private void cut(HeapNode x, HeapNode y) {
		// Remove x from y's child list
		if (x.next == x) {
			y.child = null;
		} else {
			x.next.prev = x.prev;
			x.prev.next = x.next;
			if (y.child == x) {
				y.child = x.next;
			}
		}

		y.rank--;

		// Add x to root list
		x.prev = min;
		x.next = min.next;
		min.next.prev = x;
		min.next = x;

		x.parent = null;
		x.mark = 0; // reset mark count on cut
	}


	/**
	 * 
	 * pre: 0<diff<x.key
	 * 
	 * Decrease the key of x by diff and fix the heap. Return the number of cuts.
	 * 
	 */
	public int decreaseKey(HeapNode x, int diff) 
	{    
		return 46; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the x from the heap. Return the number of links.
	 *
	 */
	public int delete(HeapNode x) {
		return 46; // should be replaced by student code
	}

	/**
	 * 
	 * Return the total number of links.
	 * 
	 */
	public int totalLinks() {
		return 46; // should be replaced by student code
	}

	/**
	 * 
	 * Return the total number of cuts.
	 * 
	 */
	public int totalCuts() {
		return 46; // should be replaced by student code
	}

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(FibonacciHeap heap2) {
		return; // should be replaced by student code
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 * 
	 */
	public int size() {
		return n; // should be replaced by student code
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees() {
		return 46; // should be replaced by student code
	}

	/**
	 * Class implementing a node in a Fibonacci Heap.
	 * 
	 */
	public static class HeapNode {
		public int key;
		public String info;
		public HeapNode child;
		public HeapNode next;
		public HeapNode prev;
		public HeapNode parent;
		public int rank;
		private int mark;
	}

/////////////////this is not part of this
	@Override
	public String toString() {
		if (min == null)
			return "(empty heap)";
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
		sb.append(prefix).append(isTail ? "└── " : "├── ").append("(").append(node.key).append(")\n");

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