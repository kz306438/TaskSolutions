package org.Task4.Task4_2.java.org.redBlackBSTDeleteImpl;

import org.Task4.RedBlackTree;

/**
 * @brief Testing client for RedBlackTree implementation
 */
public class RedBlackTreeTest {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        System.out.println("Test Client for Red-Black Tree\n");

        System.out.println("Inserting values: 10, 20, 30, 40, 50, 25");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("Searching for values: 20, 35");
        System.out.println("Find 20: " + tree.find(20)); // Expected: true
        System.out.println("Find 35: " + tree.find(35)); // Expected: false

        System.out.println("Deleting value 20");
        boolean deleted20 = tree.delete(20);
        System.out.println("Deleted 20: " + deleted20); // Expected: true

        System.out.println("Deleting maximum value");
        Integer maxValue = tree.deleteMax();
        System.out.println("Deleted max value: " + maxValue); // Expected: 50

        System.out.println("Deleting value 100 (not in tree)");
        boolean deleted100 = tree.delete(100);
        System.out.println("Deleted 100: " + deleted100); // Expected: false

        System.out.println("Test client execution completed.");
    }
}
