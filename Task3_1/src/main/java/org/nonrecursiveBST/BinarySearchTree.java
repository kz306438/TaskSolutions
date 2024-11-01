package org.nonrecursiveBST;

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
     * Finds the largest key less than or equal to the given key.
     *
     * @param key the key to compare
     * @return the largest key less than or equal to the given key, or null if no such key exists
     */
    public T floor(T key) {
        Node node = root;
        T floor = null;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp == 0) return node.key;
            if (cmp > 0) {
                floor = node.key;
                node = node.right;
            } else {
                node = node.left;
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
        Node node = root;
        T ceiling = null;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp == 0) return node.key;
            if (cmp < 0) {
                ceiling = node.key;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return ceiling;
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
     * Returns the total number of nodes in the binary search tree.
     *
     * @return the size of the tree
     */
    public int size() {
        return size(root);
    }
}
