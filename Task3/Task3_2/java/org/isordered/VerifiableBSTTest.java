package org.isordered;

public class VerifiableBSTTest {

    public static void main(String[] args) {
        testIsOrdered();
    }

    private static void testIsOrdered() {
        VerifiableBST<Integer> bst = new VerifiableBST<>();

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
        VerifiableBST<Integer> unbalancedBST = new VerifiableBST<>();
        unbalancedBST.insert(20);
        unbalancedBST.insert(30);
        unbalancedBST.insert(10);
        unbalancedBST.insert(15);
        System.out.println("Testing unbalanced ordered tree (20, 30, 10, 15): " + (unbalancedBST.isOrdered() ? "Passed" : "Failed")); // Should be true
    }
}
