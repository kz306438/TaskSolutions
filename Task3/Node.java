package Task3;

/**
 * Represents a node in the binary search tree.
 */
public class Node<T extends Comparable<T>> {
    private T key;        // Key value of the node
    private Node<T> left;    // Left child node
    private Node<T> right;   // Right child node
    private int size;     // Size of the subtree rooted at this node

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

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