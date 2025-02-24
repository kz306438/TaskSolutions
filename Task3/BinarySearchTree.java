package Task3;

/**
 * A generic implementation of a Binary Search Tree (BST).
 *
 * @param <T> the type of elements stored in the BST, must be comparable
 */
public class BinarySearchTree<T extends Comparable<T>> {

    protected Node<T> root; // Root node of the BST

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
    private Node<T> insert(Node<T> node, T key) {
        if (node == null) return new Node<T>(key);

        int cmp = key.compareTo(node.getKey());

        if (cmp < 0) node.setLeft(insert(node.getLeft(), key));
        else if (cmp > 0) node.setRight(insert(node.getRight(), key));

        node.setSize(1 + size(node.getLeft()) + size(node.getRight()));

        return node;
    }

    /**
     * Finds the minimum key in the binary search tree.
     *
     * @return the minimum key, or null if the tree is empty
     */
    public T min() {
        if (root == null) return null;
        Node<T> node = root;

        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getKey();
    }

    /**
     * Finds the maximum key in the binary search tree.
     *
     * @return the maximum key, or null if the tree is empty
     */
    public T max() {
        if (root == null) return null;
        Node<T> node = root;

        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getKey();
    }

    /**
     * Finds the number of keys less than the given key.
     *
     * @param key the key to compare
     * @return the number of keys less than the given key
     */
    public int rank(T key) {
        Node<T> node = root;
        int rank = 0;

        while (node != null) {
            int cmp = key.compareTo(node.getKey());

            if (cmp < 0) {
                node = node.getLeft();
            } else {
                rank += size(node.getLeft()) + 1;
                if (cmp == 0) return rank - 1;
                node = node.getRight();
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
        Node<T> node = root;

        while (node != null) {
            int leftSize = size(node.getLeft());
            if (leftSize > k) {
                node = node.getLeft();
            } else if (leftSize < k) {
                k = k - leftSize - 1;
                node = node.getRight();
            } else {
                return node.getKey();
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
    private int size(Node<T> node) {
        return node == null ? 0 : node.getSize();
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
