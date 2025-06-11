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
	private int totalLinks = 0;
	private int totalCuts = 0;
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
		int linksBeforeDelete = totalLinks;
	    if (min == null) {
	        return 0;
	    }

	    HeapNode oldMin = min;

	    if (oldMin.child != null) {
	        HeapNode child = oldMin.child;
	        do {
	            child.parent = null;
	            child = child.next;
	        } while (child != oldMin.child);

	        HeapNode minNext = min.next;
	        HeapNode childLast = oldMin.child.prev;
	        min.next = oldMin.child;
	        oldMin.child.prev = min;
	        minNext.prev = childLast;
	        childLast.next = minNext;
	    }

	    oldMin.prev.next = oldMin.next;
	    oldMin.next.prev = oldMin.prev;

	    if (oldMin == oldMin.next) {
	        min = null;
	        n = 0;
	    } else {
	        min = oldMin.next;
	        consolidate();
	        n--;
	    }
	    
		return (totalLinks - linksBeforeDelete);
	}

	private void consolidate() {
	    double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
	    int maxDegree = (int) Math.floor(Math.log(n > 0 ? n : 1) / Math.log(phi)) + 5;
	    HeapNode[] heapArray = new HeapNode[maxDegree];

	    int numRoots = 0;
	    HeapNode current = min;
	    if (current != null) {
	        do {
	            numRoots++;
	            current = current.next;
	        } while (current != min);
	    } else {
	        return;
	    }
	    
	    HeapNode[] rootNodes = new HeapNode[numRoots];
	    current = min;
	    for (int i = 0; i < numRoots; i++) {
	        rootNodes[i] = current;
	        current = current.next;
	    }

	    for (int i = 0; i < numRoots; i++) {
	        HeapNode x = rootNodes[i];
	        int d = x.rank;
	        while (heapArray[d] != null) {
	            HeapNode y = heapArray[d];
	            if (x.key > y.key) {
	                HeapNode temp = x;
	                x = y;
	                y = temp;
	            }
	            link(y, x);
	            heapArray[d] = null;
	            d++;
	        }
	        heapArray[d] = x;
	    }

	    min = null;
	    for (int i = 0; i < maxDegree; i++) {
	        HeapNode node = heapArray[i];
	        if (node != null) {
	            if (min == null) {
	                min = node;
	                node.next = node;
	                node.prev = node;
	            } else {
	                node.next = min.next;
	                node.prev = min;
	                min.next.prev = node;
	                min.next = node;
	                if (node.key < min.key) {
	                    min = node;
	                }
	            }
	        }
	    }
	}

	private void link(HeapNode child, HeapNode parent) {
	    child.prev.next = child.next;
	    child.next.prev = child.prev;
	    child.parent = parent;
	    if (parent.child == null) {
	        parent.child = child;
	        child.next = child;
	        child.prev = child;
	    } else {
	        child.next = parent.child.next;
	        child.prev = parent.child;
	        parent.child.next.prev = child;
	        parent.child.next = child;
	    }
	    parent.rank++;
	    child.mark = 0;
		totalLinks++;
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

			x.key -= diff;
			HeapNode y = x.parent;

			if (y != null && x.key < y.key) {
				cut(x, y);
				cascadeCut(y);
			}

			if (x.key < min.key) {
				min = x;
			}

			return x.key;

		}

	/**
	 * 
	 * Delete the x from the heap. Return the number of links.
	 *
	 */
	public int delete(HeapNode x) {
		decreaseKey(x, Integer.MAX_VALUE);
		return deleteMin();
	}

	/**
	 * 
	 * Return the total number of links.
	 * 
	 */
	public int totalLinks() {
		return totalLinks;
	}

	/**
	 * 
	 * Return the total number of cuts.
	 * 
	 */
	public int totalCuts() {
		// Implementation needed
		return totalCuts;
	}

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(FibonacciHeap heap2) {
		if (heap2 == null || heap2.min == null) {
			return; // Nothing to meld
		}

		if (this.min == null) {
			// If this heap is empty, just take heap2's properties
			this.min = heap2.min;
			this.n = heap2.n;
			return;
		}

		// Merge the root lists
		HeapNode thisNext = this.min.next;
		HeapNode heap2Prev = heap2.min.prev;

		this.min.next = heap2.min;
		heap2.min.prev = this.min;
		thisNext.prev = heap2Prev;
		heap2Prev.next = thisNext;

		// Update min if necessary
		if (heap2.min.key < this.min.key) {
			this.min = heap2.min;
		}

		// Update size
		this.n += heap2.n;
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 * 
	 */
	public int size() {
		return n;
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees() {
		if (min == null) return 0;
		int count = 0;
		HeapNode current = min;
		do {
			count++;
			current = current.next;
		} while (current != min);
		return count;
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
}