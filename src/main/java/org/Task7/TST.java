package org.Task7;

/**
 * A generic Ternary Search Trie (TST) implementation that supports storing values associated with string keys.
 *
 * @param <Value> the type of values to be stored in the trie
 */
public class TST<Value> {
    protected Node<Value> root;

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

    private Node<Value> put(String key, Value value, Node<Value> x, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>();
            x.setC(c);
        }

        if (c < x.getC()) {
            x.setLeft(put(key, value, x.getLeft(), d));
        } else if (c > x.getC()) {
            x.setRight(put(key, value, x.getRight(), d));
        } else if (d < key.length() - 1) {
            x.setMid(put(key, value, x.getMid(), d + 1));
        } else {
            if (x.getValue() == null) x.setSize(x.getSize() + 1); // Increment size if adding a new key
            x.setValue(value);
        }

        x.setSize(calculateSize(x)); // Update the size
        return x;
    }

    /**
     * Returns the number of keys currently stored in the TST.
     *
     * @return the number of keys in the TST
     */
    public int size() {
        return root == null ? 0 : root.getSize();
    }

    private int calculateSize(Node<Value> x) {
        if (x == null) return 0;
        int size = x.getValue() != null ? 1 : 0; // Count this node if it contains a value
        size += calculateSize(x.getLeft()) + calculateSize(x.getMid()) + calculateSize(x.getRight());
        return size;
    }

    /**
     * Retrieves the value associated with a given key.
     *
     * @param key the key to search for
     * @return the value associated with the key, or {@code null} if the key is not in the TST
     */
    public Value get(String key) {
        Node<Value> x = get(key, root, 0);
        return x == null ? null : x.getValue();
    }

    protected Node<Value> get(String key, Node<Value> x, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.getC()) return get(key, x.getLeft(), d);
        else if (c > x.getC()) return get(key, x.getRight(), d);
        else if (d < key.length() - 1) return get(key, x.getMid(), d + 1);
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

    private Node<Value> delete(String key, Node<Value> x, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.getC()) {
            x.setLeft(delete(key, x.getLeft(), d));
        } else if (c > x.getC()) {
            x.setRight(delete(key, x.getRight(), d));
        } else if (d < key.length() - 1) {
            x.setMid(delete(key, x.getMid(), d + 1));
        } else {
            if (x.getValue() != null) x.setSize(x.getSize() - 1); // Decrement size if removing a key
            x.setValue(null);
        }

        x.setSize(calculateSize(x)); // Update the size
        if (x.getValue() == null && x.getSize() == 0) return null; // Remove empty nodes
        return x;
    }
}
