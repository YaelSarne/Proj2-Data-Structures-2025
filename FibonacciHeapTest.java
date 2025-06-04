
public class FibonacciHeapTest {
    public static void main(String[] args) {
        FibonacciHeap heap = new FibonacciHeap(2);

        System.out.println("Inserting nodes...");
        heap.insert(9, "nine");
        heap.insert(15, "fifteen");
        heap.insert(23, "twenty-three");
        heap.insert(40, "forty");
        heap.insert(33, "thirty-three");
        heap.insert(45, "forty-five");
        heap.insert(58, "fifty-eight");
        heap.insert(20, "twenty");
        heap.insert(35, "thirty-five");
        heap.insert(67, "sixty-seven");

        System.out.println("\nHeap structure after insertions:");
        System.out.println(heap);

        // (Optional) Simulate manually linking children if you’re not implementing extractMin yet
        System.out.println("Manually linking 15 -> 40, and 40 -> 45, 58 for structure test (simulated)");

        // Simulate structure manually:
      //  FibonacciHeap.HeapNode node15 = heap.insert(15, "another fifteen");
      //  FibonacciHeap.HeapNode node40 = heap.insert(40, "child of 15");
       // FibonacciHeap.HeapNode node45 = heap.insert(45, "child of 40");
       // FibonacciHeap.HeapNode node58 = heap.insert(58, "another child of 40");

       // node15.child = node40;
      // node40.parent = node15;
      //node40.next = node40.prev = node40;
      // node40.child = node45;
      // node45.parent = node40;
      //  node45.next = node58;
       // node58.prev = node45;
       // node58.next = node45;
       // node45.prev = node58;

      //  System.out.println("\nHeap structure after manual child linking:");
       // System.out.println(heap);
    }
}
