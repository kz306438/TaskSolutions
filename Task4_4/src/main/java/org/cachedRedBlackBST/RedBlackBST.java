package org.cachedRedBlackBST;

/**
 * A generic Red-Black Binary Search Tree (BST) that supports efficient insertion, retrieval,
 * and updating of key-value pairs. This class implements a caching mechanism to store the last
 * accessed node for faster subsequent lookups.
 *
 * @param <Key> the type of the keys in the tree, which must be comparable
 * @param <Value> the type of the values associated with the keys
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private Node lastAccessedNode;  // Variable to store the last accessed node

    /**
     * Private inner class that represents a node in the Red-Black BST.
     */
    private class Node {
        Key key;
        Value val;
        Node left, right;
        int size;
        boolean color;

        /**
         * Constructs a new node with the given key, value, size, and color.
         *
         * @param key the key of the node
         * @param val the value associated with the key
         * @param size the size of the subtree rooted at this node
         * @param color the color of the node (true for red, false for black)
         */
        Node(Key key, Value val, int size, boolean color) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;
        }
    }

    /**
     * Retrieves the value associated with the given key.
     * If the key was accessed recently, it returns the cached value from the last accessed node.
     *
     * @param key the key whose associated value is to be retrieved
     * @return the value associated with the key, or null if the key does not exist
     */
    public Value get(Key key) {
        if (lastAccessedNode != null && key.equals(lastAccessedNode.key)) {
            return lastAccessedNode.val;  // Return value from the last accessed node
        }

        root = get(root, key);
        if (root != null) {
            lastAccessedNode = root;  // Update the last accessed node
        }
        return (root == null) ? null : root.val;
    }

    /**
     * Recursive helper method to retrieve a node with the given key.
     *
     * @param x the current node being examined
     * @param key the key to search for
     * @return the node containing the key, or null if not found
     */
    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x;
        }
    }

    /**
     * Inserts a key-value pair into the tree, updating the value if the key already exists.
     * If the key was accessed recently, it updates the cached value in the last accessed node.
     *
     * @param key the key to be inserted or updated
     * @param val the value to be associated with the key
     */
    public void put(Key key, Value val) {
        if (lastAccessedNode != null && key.equals(lastAccessedNode.key)) {
            lastAccessedNode.val = val;  // Update value in the last accessed node
            return;
        }

        root = put(root, key, val);
        if (root != null) {
            lastAccessedNode = root;  // Update the last accessed node
        }
    }

    /**
     * Recursive helper method to insert a key-value pair into the tree.
     *
     * @param x the current node being examined
     * @param key the key to be inserted or updated
     * @param val the value to be associated with the key
     * @return the updated node
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1, true);  // Create a new node if the tree is empty
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;  // Update value if key already exists
        }

        x.size = 1 + size(x.left) + size(x.right);  // Update the size of the subtree
        return x;
    }

    /**
     * Returns the size of the subtree rooted at the given node.
     *
     * @param x the node whose size is to be computed
     * @return the size of the subtree rooted at the given node
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }
}
