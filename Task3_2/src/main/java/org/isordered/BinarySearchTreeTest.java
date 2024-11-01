package org.isordered;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        testIsOrdered();
    }

    private static void testIsOrdered() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test with an empty tree
        System.out.println("Testing empty tree: " + (bst.isOrdered() ? "Passed" : "Failed")); // Should be true

        // Test with one element
        bst.insert(10);
        System.out.println("Testing single element (10): " + (bst.isOrdered() ? "Passed" : "Failed")); // Should be true

        // Test with two elements (ordered)
        bst.insert(5);
        System.out.println("Testing two elements (10, 5): " + (bst.isOrdered() ? "Passed" : "Failed")); // Should be true

        // Test with two elements (not ordered)
        bst.insert(15);
        bst.insert(3); // This should still keep the tree ordered
        System.out.println("Testing with multiple elements (10, 5, 15, 3): " + (bst.isOrdered() ? "Passed" : "Failed")); // Should be true

        // Creating an unbalanced tree that is ordered
        BinarySearchTree<Integer> unbalancedBST = new BinarySearchTree<>();
        unbalancedBST.insert(20);
        unbalancedBST.insert(30);
        unbalancedBST.insert(10);
        unbalancedBST.insert(15);
        System.out.println("Testing unbalanced ordered tree (20, 30, 10, 15): " + (unbalancedBST.isOrdered() ? "Passed" : "Failed")); // Should be true

        // Creating a tree that is not ordered
        BinarySearchTree<Integer> notOrderedBST = new BinarySearchTree<>();
        notOrderedBST.insert(10);
        notOrderedBST.insert(5);
        notOrderedBST.insert(15);
        notOrderedBST.insert(3);
        notOrderedBST.insert(12);
        notOrderedBST.insert(14); // This will break the ordering for 15
        System.out.println("Testing tree with an invalid order (10, 5, 15, 3, 12, 14): " + (notOrderedBST.isOrdered() ? "Passed" : "Failed")); // Should be false
    }
}
