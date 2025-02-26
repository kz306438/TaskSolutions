package org.Task3.Task3_3.java.org.threadedST;

/**
 * A binary search tree implementation with threaded nodes for efficient next and previous key retrieval.
 *
 * @param <Key> the generic type of keys in the tree, which must be comparable
 */
public class ThreadedST<Key extends Comparable<Key>> {

    private class Node {
        Key key;
        Node left, right;
        Node predecessor, successor;

        /**
         * Constructs a new node with the specified key.
         *
         * @param key the key associated with this node
         */
        Node(Key key) {
            this.key = key;
        }
    }

    private Node root;

    /**
     * Inserts a key into the binary search tree.
     *
     * @param key the key to insert
     */
    public void insert(Key key) {
        root = insert(root, key, null, null);
    }

    private Node insert(Node node, Key key, Node pred, Node succ) {
        if (node == null) {
            Node newNode = new Node(key);
            newNode.predecessor = pred;
            newNode.successor = succ;
            if (pred != null) pred.successor = newNode;
            if (succ != null) succ.predecessor = newNode;
            return newNode;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, pred, node);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, node, succ);
        }
        return node;
    }

    /**
     * Deletes a key from the binary search tree.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.successor != null) node.successor.predecessor = node.predecessor;
            if (node.predecessor != null) node.predecessor.successor = node.successor;

            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    /**
     * Deletes the minimum key from the binary search tree.
     */
    public void deleteMin() {
        if (root != null) root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            if (node.successor != null) node.successor.predecessor = null;
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * Deletes the maximum key from the binary search tree.
     */
    public void deleteMax() {
        if (root != null) root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            if (node.predecessor != null) node.predecessor.successor = null;
            return node.left;
        }
        node.right = deleteMax(node.right);
        return node;
    }

    /**
     * Finds the next key relative to a given key.
     *
     * @param key the reference key
     * @return the next key, or {@code null} if there is no next key
     */
    public Key next(Key key) {
        Node node = getNode(root, key);
        return (node != null && node.successor != null) ? node.successor.key : null;
    }

    /**
     * Finds the previous key relative to a given key.
     *
     * @param key the reference key
     * @return the previous key, or {@code null} if there is no previous key
     */
    public Key previous(Key key) {
        Node node = getNode(root, key);
        return (node != null && node.predecessor != null) ? node.predecessor.key : null;
    }

    private Node getNode(Node node, Key key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node;
        }
        return null;
    }

    /**
     * Retrieves the minimum key in the binary search tree.
     *
     * @return the minimum key, or {@code null} if the tree is empty
     */
    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    /**
     * Retrieves the maximum key in the binary search tree.
     *
     * @return the maximum key, or {@code null} if the tree is empty
     */
    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    private Node min(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node max(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }
}
