//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class FibonacciHeapTest {
    private static final int c = 5000;
    private static double score = (double)70.0F;
    private static final double pointsPerTest;

    public static void main(String[] var0) {
        System.out.println("Starting");
        testFindMinAfterSingleInsert();
        testFindMinAfterTwoInserts();
        testDeleteMin();
        testRemoveMinSequentially();
        testReverseInsertionAndRemoveMin();
        testDecreaseKey();
        testDecreaseKeyEdgeCases();
        testHeapIntegrityAfterRemovals();
        testHeapIntegrityAfterLargeInsertions();
        testTreeStructureAfterOperations();
        testHeapProperty();
        testHeapPropertySmall();
        System.out.println("Final score: " + Math.round(score) + "/70");
    }

    private static void testFindMinAfterSingleInsert() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);
            var0.insert(1, "one");

            assert var0.findMin() != null && var0.findMin().key == 1 : "Min is incorrect after insert";
        } catch (AssertionError var1) {
            System.out.println("Test failed: testFindMinAfterSingleInsert");
            score -= pointsPerTest;
        } catch (Exception var2) {
            System.out.println("Test failed due to exception: testFindMinAfterSingleInsert");
            score -= pointsPerTest;
        }

    }

    private static void testFindMinAfterTwoInserts() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);
            var0.insert(1, "one");
            var0.insert(2, "two");

            assert var0.findMin() != null && var0.findMin().key == 1 : "Min is incorrect after two inserts";
        } catch (AssertionError var1) {
            System.out.println("Test failed: testFindMinAfterTwoInserts");
            score -= pointsPerTest;
        } catch (Exception var2) {
            System.out.println("Test failed due to exception: testFindMinAfterTwoInserts");
            score -= pointsPerTest;
        }

    }

    private static void testDeleteMin() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);
            var0.insert(1, "one");
            var0.insert(2, "two");
            var0.deleteMin();

            assert var0.findMin() != null && var0.findMin().key == 2 : "Min is incorrect after deleteMin";
        } catch (AssertionError var1) {
            System.out.println("Test failed: testDeleteMin");
            score -= pointsPerTest;
        } catch (Exception var2) {
            System.out.println("Test failed due to exception: testDeleteMin");
            score -= pointsPerTest;
        }

    }

    private static void testRemoveMinSequentially() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 100; ++var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            for(int var4 = 1; var4 < 100; ++var4) {
                var0.deleteMin();
            }

            assert var0.findMin() != null && var0.findMin().key == 100 : "Min is incorrect after sequential removeMin";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testRemoveMinSequentially");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testRemoveMinSequentially");
            score -= pointsPerTest;
        }

    }

    private static void testReverseInsertionAndRemoveMin() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 100; var1 >= 1; --var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            for(int var4 = 1; var4 < 100; ++var4) {
                var0.deleteMin();
            }

            assert var0.findMin() != null && var0.findMin().key == 100 : "Min is incorrect after reverse insertion";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testReverseInsertionAndRemoveMin");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testReverseInsertionAndRemoveMin");
            score -= pointsPerTest;
        }

    }

    private static void testDecreaseKey() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);
            FibonacciHeap.HeapNode var1 = var0.insert(100, "hundred");
            var0.decreaseKey(var1, 50);

            assert var0.findMin() != null && var0.findMin().key == 50 : "Min is incorrect after decreaseKey";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testDecreaseKey");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testDecreaseKey");
            score -= pointsPerTest;
        }

    }

    private static void testDecreaseKeyEdgeCases() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);
            FibonacciHeap.HeapNode var1 = var0.insert(100, "hundred");
            var0.insert(75, "seventy-five");
            var0.decreaseKey(var1, 50);

            assert var0.findMin() != null && var0.findMin().key == 50 : "Min is incorrect after decreaseKey edge case";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testDecreaseKeyEdgeCases");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testDecreaseKeyEdgeCases");
            score -= pointsPerTest;
        }

    }

    private static void testHeapIntegrityAfterRemovals() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 100; var1 += 2) {
                var0.insert(var1, String.valueOf(var1));
                var0.insert(101 - var1, String.valueOf(101 - var1));
            }

            var0.deleteMin();

            assert var0.findMin() != null && var0.findMin().key == 2 : "Min is incorrect after heap integrity check";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testHeapIntegrityAfterRemovals");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testHeapIntegrityAfterRemovals");
            score -= pointsPerTest;
        }

    }

    private static void testHeapIntegrityAfterLargeInsertions() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 100; ++var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            assert var0.size() == 100 : "Heap size incorrect after large insertions";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testHeapIntegrityAfterLargeInsertions");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testHeapIntegrityAfterLargeInsertions");
            score -= pointsPerTest;
        }

    }

    private static void testTreeStructureAfterOperations() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 100; ++var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            var0.deleteMin();
            var0.decreaseKey(var0.insert(50, "fifty"), 49);

            assert var0.findMin() != null && var0.findMin().key == 1 : "Min is incorrect after tree operations";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testTreeStructureAfterOperations");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testTreeStructureAfterOperations");
            score -= pointsPerTest;
        }

    }

    private static void testHeapProperty() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 100; ++var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            assert var0.findMin() != null && checkHeapProperty(var0.findMin()) : "Heap property violated";
        } catch (AssertionError var2) {
            System.out.println("Test failed: testHeapProperty");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testHeapProperty");
            score -= pointsPerTest;
        }

    }

    private static void testHeapPropertySmall() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 10; ++var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            assert var0.findMin() != null && checkHeapProperty(var0.findMin()) : "Heap property violated on small heap";

            System.out.println(checkHeapProperty(var0.findMin()));
        } catch (AssertionError var2) {
            System.out.println("Test failed: testHeapPropertySmall");
            score -= pointsPerTest;
        } catch (Exception var3) {
            System.out.println("Test failed due to exception: testHeapPropertySmall");
            score -= pointsPerTest;
        }

    }

    private static boolean checkHeapProperty(FibonacciHeap.HeapNode var0) {
        if (var0 == null) {
            return true;
        } else {
            for(FibonacciHeap.HeapNode var1 = var0.child; var1 != null; var1 = var1.next) {
                if (var1.key < var0.key) {
                    return false;
                }

                if (!checkHeapProperty(var1)) {
                    return false;
                }
            }

            return true;
        }
    }

    private static void testHeapStructureAfterInsertionsDeletionsAndDecreaseKey() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 10; ++var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            for(int var8 = 0; var8 < 5; ++var8) {
                var0.deleteMin();
            }

            FibonacciHeap.HeapNode var9 = var0.insert(20, "twenty");
            var0.decreaseKey(var9, 15);
            int var2 = var0.size();
            int var3 = var0.numTrees();
            int var4 = var0.totalLinks();
            int var5 = var0.totalCuts();

            assert var2 - var3 == var4 - var5 : "Nodes - Trees == Links - Cuts";

            var0.deleteMin();
            var2 = var0.size();
            var3 = var0.numTrees();
            var4 = var0.totalLinks();
            var5 = var0.totalCuts();

            assert var2 - var3 == var4 - var5 : "Nodes - Trees == Links - Cuts";
        } catch (AssertionError var6) {
            System.out.println("Test failed: testHeapStructureAfterInsertionsDeletionsAndDecreaseKey");
            score -= pointsPerTest;
        } catch (Exception var7) {
            System.out.println("Test failed due to exception: testHeapStructureAfterInsertionsDeletionsAndDecreaseKey");
            score -= pointsPerTest;
        }

    }

    private static void testHeapStructureAfterMultipleDeletionsAndDecreaseKey() {
        try {
            FibonacciHeap var0 = new FibonacciHeap(c);

            for(int var1 = 1; var1 <= 20; ++var1) {
                var0.insert(var1, String.valueOf(var1));
            }

            int var8 = var0.size();
            int var2 = var0.numTrees();
            int var3 = var0.totalLinks();
            int var4 = var0.totalCuts();

            for(int var5 = 0; var5 < 10; ++var5) {
                var0.deleteMin();
            }

            FibonacciHeap.HeapNode var9 = var0.insert(25, "twenty-five");
            var0.decreaseKey(var9, 20);

            assert var8 - var2 == var3 - var4 : "Nodes - Trees == Links - Cuts";
        } catch (AssertionError var6) {
            System.out.println("Test failed: testHeapStructureAfterMultipleDeletionsAndDecreaseKey");
            score -= pointsPerTest;
        } catch (Exception var7) {
            System.out.println("Test failed due to exception: testHeapStructureAfterMultipleDeletionsAndDecreaseKey");
            score -= pointsPerTest;
        }

    }

    static {
        pointsPerTest = score / (double)12.0F;
    }
}