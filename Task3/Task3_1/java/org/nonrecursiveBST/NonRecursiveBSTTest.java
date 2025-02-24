package org.nonrecursiveBST;

/**
 * A test client for the BinarySearchTree class.
 * Demonstrates the functionality of the binary search tree by performing insertions
 * and testing various methods.
 */
public class NonRecursiveBSTTest {
    /**
     * The main method to run the test cases for BinarySearchTree.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        NonRecursiveBST<Integer> bst = new NonRecursiveBST<>();

        // Insert elements into the binary search tree
        bst.insert(15);
        bst.insert(10);
        bst.insert(20);
        bst.insert(8);
        bst.insert(12);
        bst.insert(17);
        bst.insert(25);

        // Test the methods of BinarySearchTree
        System.out.println("Minimum: " + bst.min()); // Expected: 8
        System.out.println("Maximum: " + bst.max()); // Expected: 25
        System.out.println("Floor of 13: " + bst.floor(13)); // Expected: 12
        System.out.println("Ceiling of 13: " + bst.ceiling(13)); // Expected: 15
        System.out.println("Rank of 17: " + bst.rank(17)); // Expected: 4
        System.out.println("Element with rank 3: " + bst.select(3)); // Expected: 15
        System.out.println("Size of the tree: " + bst.size()); // Expected: 7
    }
}
