package org.isordered;

/**
 * A generic implementation of a Binary Search Tree (BST).
 *
 * @param <T> the type of elements stored in the BST, must be comparable
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * Represents a node in the binary search tree.
     */
    private class Node {
        T key;        // Key value of the node
        Node left;    // Left child node
        Node right;   // Right child node
        int size;     // Size of the subtree rooted at this node

        /**
         * Constructs a node with the given key.
         *
         * @param key the key value of the node
         */
        Node(T key) {
            this.key = key;
            this.size = 1;
        }
    }

    private Node root; // Root node of the BST

    /**
     * Inserts a key into the binary search tree.
     *
     * @param key the key to insert
     */
    public void insert(T key) {
        root = insert(root, key);
    }

    /**
     * Helper method to recursively insert a key into the subtree rooted at the given node.
     *
     * @param node the root of the subtree
     * @param key  the key to insert
     * @return the root of the subtree after insertion
     */
    private Node insert(Node node, T key) {
        if (node == null) return new Node(key);

        int cmp = key.compareTo(node.key);

        if (cmp < 0) node.left = insert(node.left, key);
        else if (cmp > 0) node.right = insert(node.right, key);

        node.size = 1 + size(node.left) + size(node.right);

        return node;
    }

    /**
     * Finds the minimum key in the binary search tree.
     *
     * @return the minimum key, or null if the tree is empty
     */
    public T min() {
        if (root == null) return null;
        Node node = root;

        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    /**
     * Finds the maximum key in the binary search tree.
     *
     * @return the maximum key, or null if the tree is empty
     */
    public T max() {
        if (root == null) return null;
        Node node = root;

        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    /**
     * Finds the number of keys less than the given key.
     *
     * @param key the key to compare
     * @return the number of keys less than the given key
     */
    public int rank(T key) {
        Node node = root;
        int rank = 0;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) {
                node = node.left;
            } else {
                rank += size(node.left) + 1;
                if (cmp == 0) return rank - 1;
                node = node.right;
            }
        }
        return rank;
    }

    /**
     * Finds the key at the specified rank (index) in the binary search tree.
     *
     * @param k the rank (0-based index)
     * @return the key at the specified rank, or null if no such key exists
     */
    public T select(int k) {
        Node node = root;

        while (node != null) {
            int leftSize = size(node.left);
            if (leftSize > k) {
                node = node.left;
            } else if (leftSize < k) {
                k = k - leftSize - 1;
                node = node.right;
            } else {
                return node.key;
            }
        }

        return null;
    }

    /**
     * Returns the size of the subtree rooted at the given node.
     *
     * @param node the root of the subtree
     * @return the size of the subtree, or 0 if the node is null
     */
    private int size(Node node) {
        return node == null ? 0 : node.size;
    }


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
    private boolean isOrdered(Node node, T min, T max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.key.compareTo(min) <= 0) ||
                (max != null && node.key.compareTo(max) >= 0)) {
            return false;
        }

        // Check left and right subtrees with updated bounds
        return isOrdered(node.left, min, node.key) &&
                isOrdered(node.right, node.key, max);
    }

    /**
     * Returns the total number of nodes in the binary search tree.
     *
     * @return the size of the tree
     */
    public int size() {
        return size(root);
    }
}
