package org.Task3.Task3_1.java.org.nonrecursiveBST;

import org.Task3.BinarySearchTree;
import org.Task3.Node;

/**
 * A generic implementation of a Binary Search Tree (BST).
 *
 * @param <T> the type of elements stored in the BST, must be comparable
 */
public class NonRecursiveBST<T extends Comparable<T>> extends BinarySearchTree<T> {
    /**
     * Finds the largest key less than or equal to the given key.
     *
     * @param key the key to compare
     * @return the largest key less than or equal to the given key, or null if no such key exists
     */
    public T floor(T key) {
        Node<T> node = root;
        T floor = null;

        while (node != null) {
            int cmp = key.compareTo(node.getKey());

            if (cmp == 0) return node.getKey();
            if (cmp > 0) {
                floor = node.getKey();
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }
        return floor;
    }

    /**
     * Finds the smallest key greater than or equal to the given key.
     *
     * @param key the key to compare
     * @return the smallest key greater than or equal to the given key, or null if no such key exists
     */
    public T ceiling(T key) {
        Node<T> node = root;
        T ceiling = null;

        while (node != null) {
            int cmp = key.compareTo(node.getKey());

            if (cmp == 0) return node.getKey();
            if (cmp < 0) {
                ceiling = node.getKey();
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return ceiling;
    }
}
