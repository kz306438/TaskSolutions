package org.Task3.Task3_2.java.org.isordered;

import org.Task3.Node;
import org.Task3.BinarySearchTree;

/**
 * A generic implementation of a Binary Search Tree (BST).
 *
 * @param <T> the type of elements stored in the BST, must be comparable
 */
public class VerifiableBST<T extends Comparable<T>> extends BinarySearchTree<T> {
    /**
     * Checks if the binary search tree is ordered according to BST properties.
     *
     * @return true if the BST is ordered; false otherwise
     */
    public boolean isOrdered() {
        return isOrdered(root, null, null);
    }

    /**
     * Recursive helper method to check if the binary search tree rooted at the given node
     * is ordered within the specified min and max bounds.
     *
     * @param node the current node
     * @param min the minimum bound (exclusive) or null if unbounded
     * @param max the maximum bound (exclusive) or null if unbounded
     * @return true if the subtree rooted at the given node is ordered; false otherwise
     */
    private boolean isOrdered(Node<T> node, T min, T max) {
        if (node == null) {
            return true;
        }

        T key = node.getKey();

        if ((min != null && key.compareTo(min) <= 0) ||
                (max != null && key.compareTo(max) >= 0)) {
            return false;
        }

        // Check left and right subtrees with updated bounds
        return isOrdered(node.getLeft(), min, key) &&
                isOrdered(node.getRight(), key, max);
    }
}
