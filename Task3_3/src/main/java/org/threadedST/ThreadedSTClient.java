package org.threadedST;

public class ThreadedSTClient {
    public static void main(String[] args) {
        ThreadedST<Integer> bst = new ThreadedST<>();

        System.out.println("=== Binary Search Tree Testing ===");

        // Insert elements
        System.out.println("\nInserting elements: 20, 10, 30, 5, 15, 25, 35");
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(15);
        bst.insert(25);
        bst.insert(35);
        printTreeState(bst);

        // Test next and previous operations
        System.out.println("\nTesting next and previous operations:");
        testNextPrevious(bst, 15); // Should return next = 20, previous = 10
        testNextPrevious(bst, 10); // Should return next = 15, previous = 5
        testNextPrevious(bst, 5);  // Should return next = 10, previous = null
        testNextPrevious(bst, 35); // Should return next = null, previous = 30

        // Test min and max retrieval
        System.out.println("\nTesting min and max retrieval:");
        System.out.println("Min: " + bst.min()); // Expected: 5
        System.out.println("Max: " + bst.max()); // Expected: 35

        // Test deleteMin and deleteMax
        System.out.println("\nTesting deleteMin and deleteMax:");
        bst.deleteMin();
        System.out.println("After deleteMin:");
        printTreeState(bst);

        bst.deleteMax();
        System.out.println("After deleteMax:");
        printTreeState(bst);

        // Test delete operation
        System.out.println("\nTesting delete operation:");
        System.out.println("Deleting element 20...");
        bst.delete(20);
        printTreeState(bst);

        System.out.println("Deleting element 10...");
        bst.delete(10);
        printTreeState(bst);

        // Final tree state
        System.out.println("\nFinal tree state:");
        printTreeState(bst);
    }

    // Helper method to test next and previous for a given key
    private static void testNextPrevious(ThreadedST<Integer> bst, int key) {
        Integer next = bst.next(key);
        Integer previous = bst.previous(key);
        System.out.println("Key: " + key);
        System.out.println("  Next: " + (next != null ? next : "null"));
        System.out.println("  Previous: " + (previous != null ? previous : "null"));
    }

    // Helper method to print the current state of the tree
    private static void printTreeState(ThreadedST<Integer> bst) {
        System.out.println("Tree state:");
        System.out.println("  Min: " + (bst.min() != null ? bst.min() : "null"));
        System.out.println("  Max: " + (bst.max() != null ? bst.max() : "null"));
        System.out.println();
    }
}



