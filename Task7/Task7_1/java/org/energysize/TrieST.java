package org.energysize;

import java.util.HashMap;

/**
 * A generic Trie symbol table implementation that supports storing values associated with string keys.
 *
 * @param <Value> the type of values to be stored in the trie
 */
public class TrieST<Value> {
    private Node root = new Node();

    /**
     * A node in the trie structure.
     */
    private static class Node {
        private Object value; // The value associated with the key ending at this node
        private HashMap<Character, Node> next = new HashMap<>(); // Map of character to child nodes
        private int size = 0; // Number of keys in the subtree rooted at this node
    }

    /**
     * Inserts a key-value pair into the trie.
     * If the key already exists, its value will be updated.
     *
     * @param key the key to insert
     * @param val the value to associate with the key
     */
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.value == null) x.size++; // Increment size if adding a new key
            x.value = val;
            return x;
        }
        char c = key.charAt(d);
        Node child = put(x.next.get(c), key, val, d + 1);
        x.next.put(c, child);
        x.size = calculateSize(x); // Update the size
        return x;
    }

    private int calculateSize(Node x) {
        if (x == null) return 0; // Size is 0 if the node is null
        int size = x.value != null ? 1 : 0; // Count this node if it contains a value
        for (Node child : x.next.values()) {
            if (child != null) {
                size += child.size;
            }
        }
        return size;
    }

    /**
     * Retrieves the value associated with a given key.
     *
     * @param key the key to search for
     * @return the value associated with the key, or {@code null} if the key is not in the trie
     */
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next.get(c), key, d + 1);
    }

    /**
     * Deletes a key and its associated value from the trie.
     *
     * @param key the key to delete
     */
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.value != null) x.size--; // Decrement size if removing a key
            x.value = null;
        } else {
            char c = key.charAt(d);
            Node child = delete(x.next.get(c), key, d + 1);
            if (child == null) {
                x.next.remove(c); // Remove empty branch
            } else {
                x.next.put(c, child);
            }
        }
        x.size = calculateSize(x); // Update the size
        if (x.value == null && x.size == 0) return null; // Remove empty nodes
        return x;
    }

    /**
     * Returns the number of keys currently stored in the trie.
     *
     * @return the number of keys in the trie
     */
    public int size() {
        return root == null ? 0 : root.size;
    }
}
