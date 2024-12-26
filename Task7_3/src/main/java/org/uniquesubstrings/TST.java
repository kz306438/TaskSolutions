package org.uniquesubstrings;

/**
 * A generic Ternary Search Trie (TST) implementation that supports storing values associated with string keys.
 *
 * @param <Value> the type of values to be stored in the trie
 */
public class TST<Value> {
    private Node root;

    /**
     * A node in the TST structure.
     */
    private class Node {
        private char c; // The character stored in this node
        private Node left, mid, right; // Left, middle, and right child nodes
        private Value value; // The value associated with the key ending at this node
        private int size = 0; // Number of keys in the subtree rooted at this node
    }

    /**
     * Inserts a key-value pair into the TST.
     * If the key already exists, its value will be updated.
     *
     * @param key the key to insert
     * @param value the value to associate with the key
     */
    public void put(String key, Value value) {
        root = put(key, value, root, 0);
    }

    private Node put(String key, Value value, Node x, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }

        if (c < x.c) {
            x.left = put(key, value, x.left, d);
        } else if (c > x.c) {
            x.right = put(key, value, x.right, d);
        } else if (d < key.length() - 1) {
            x.mid = put(key, value, x.mid, d + 1);
        } else {
            if (x.value == null) x.size++; // Increment size if adding a new key
            x.value = value;
        }

        x.size = calculateSize(x); // Update the size
        return x;
    }

    /**
     * Returns the number of keys currently stored in the TST.
     *
     * @return the number of keys in the TST
     */
    public int size() {
        return root == null ? 0 : root.size;
    }

    private int calculateSize(Node x) {
        if (x == null) return 0;
        int size = x.value != null ? 1 : 0; // Count this node if it contains a value
        size += calculateSize(x.left) + calculateSize(x.mid) + calculateSize(x.right);
        return size;
    }

    /**
     * Retrieves the value associated with a given key.
     *
     * @param key the key to search for
     * @return the value associated with the key, or {@code null} if the key is not in the TST
     */
    public Value get(String key) {
        Node x = get(key, root, 0);
        if (x == null) return null;
        return x.value;
    }

    private Node get(String key, Node x, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(key, x.left, d);
        else if (c > x.c) return get(key, x.right, d);
        else if (d < key.length() - 1) return get(key, x.mid, d + 1);
        else return x;
    }

    /**
     * Deletes a key and its associated value from the TST.
     *
     * @param key the key to delete
     */
    public void delete(String key) {
        root = delete(key, root, 0);
    }

    private Node delete(String key, Node x, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) {
            x.left = delete(key, x.left, d);
        } else if (c > x.c) {
            x.right = delete(key, x.right, d);
        } else if (d < key.length() - 1) {
            x.mid = delete(key, x.mid, d + 1);
        } else {
            if (x.value != null) x.size--; // Decrement size if removing a key
            x.value = null;
        }

        x.size = calculateSize(x); // Update the size
        if (x.value == null && x.size == 0) return null; // Remove empty nodes
        return x;
    }
}