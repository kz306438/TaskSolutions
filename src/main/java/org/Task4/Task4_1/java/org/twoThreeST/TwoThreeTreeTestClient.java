package org.Task4.Task4_1.java.org.twoThreeST;

import java.util.Arrays;

/**
 * @brief Test client for the TwoThreeTree data structure.
 */
public class TwoThreeTreeTestClient {
    public static void main(String[] args) {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>();

        // Test insertion
        System.out.println("Inserting elements: 10, 20, 5, 15, 30");
        tree.Insert(10);
        tree.Insert(20);
        tree.Insert(5);
        tree.Insert(15);
        tree.Insert(30);

        // Test in-order traversal
        System.out.print("In-order traversal: ");
        tree.inOrder();
        System.out.println();

        // Test find
        System.out.println("Find 15: " + (tree.find(15) != null ? "Found" : "Not Found"));
        System.out.println("Find 25: " + (tree.find(25) != null ? "Found" : "Not Found"));

        // Test findMin and findMax
        System.out.println("Minimum element: " + tree.findMin());
        System.out.println("Maximum element: " + tree.findMax());

        // Test removal
        System.out.println("Removing 30");
        tree.remove(30);
        System.out.println("Removing 5");
        tree.remove(5);
        System.out.print("In-order traversal after removal: ");
        tree.inOrder();
        System.out.println();

        // Test findMin and findMax
        System.out.println("Minimum element: " + tree.findMin());
        System.out.println("Maximum element: " + tree.findMax());

        // Test bulk insertion using a collection
        System.out.println("Bulk inserting elements: [40, 50, 35]");
        tree = new TwoThreeTree<>(Arrays.asList(40, 50, 35));
        System.out.print("In-order traversal after bulk insertion: ");
        tree.inOrder();
        System.out.println();
    }
}
