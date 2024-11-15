package org.AVLTree;

/**
 * This class implements an AVL Tree, which is a self-balancing binary search tree.
 * It maintains the balance property by performing rotations during insertions and deletions.
 * The tree ensures that the heights of two child subtrees of any node differ by at most one.
 */
public class AVLTree {

    /**
     * Node class representing each node of the AVL tree.
     */
    static class Node {
        int key;        /**< The key stored in the node */
        Node left, right; /**< References to the left and right children */
        int height;      /*< Height of the node in the tree */

        /**
         * Constructor to initialize a node with a key.
         *
         * @param key The key value of the node
         */
        public Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root; /*< The root node of the AVL tree */

    /**
     * Returns the height of a given node.
     *
     * @param node The node whose height is to be retrieved
     * @return The height of the node
     */
    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    /**
     * Computes the balance factor of a node.
     *
     * @param node The node for which the balance factor is to be calculated
     * @return The balance factor of the node
     */
    private int balanceFactor(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    /**
     * Updates the height of a node based on the heights of its children.
     *
     * @param node The node whose height is to be updated
     */
    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Performs a right rotation on the subtree rooted at the given node.
     *
     * @param node The node around which the right rotation is to be performed
     * @return The new root node after the rotation
     */
    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Performs a left rotation on the subtree rooted at the given node.
     *
     * @param node The node around which the left rotation is to be performed
     * @return The new root node after the rotation
     */
    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Balances the given node if it is unbalanced.
     *
     * @param node The node to be balanced
     * @return The balanced node
     */
    private Node balance(Node node) {
        updateHeight(node);

        int balanceFactor = balanceFactor(node);

        // Left heavy subtree
        if (balanceFactor > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left); // Left-Right case
            }
            node = rightRotate(node); // Left-Left case
        }
        // Right heavy subtree
        else if (balanceFactor < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right); // Right-Left case
            }
            node = leftRotate(node); // Right-Right case
        }

        return node;
    }

    /**
     * Inserts a key into the AVL tree.
     *
     * @param key The key to be inserted into the tree
     */
    public void insert(int key) {
        root = insert(root, key);
    }

    /**
     * Helper method to insert a key into the subtree rooted at the given node.
     *
     * @param node The node at which to insert the key
     * @param key The key to be inserted
     * @return The new root node of the subtree
     */
    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // Duplicate keys are not allowed
        }

        return balance(node);
    }

    /**
     * Deletes a key from the AVL tree.
     *
     * @param key The key to be deleted from the tree
     */
    public void delete(int key) {
        root = delete(root, key);
    }

    /**
     * Helper method to delete a key from the subtree rooted at the given node.
     *
     * @param node The node at which to delete the key
     * @param key  The key to be deleted
     * @return The new root node of the subtree
     */
    private Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            node = handleDeletion(node);
        }

        return (node == null) ? null : balance(node);
    }

    /**
     * Handles the deletion of a node when the key matches.
     *
     * @param node The node to delete
     * @return The new root node after deletion
     */
    private Node handleDeletion(Node node) {
        if (node.left == null || node.right == null) {
            return node.left != null ? node.left : node.right;
        }

        Node temp = minValueNode(node.right);
        node.key = temp.key;
        node.right = delete(node.right, temp.key);
        return node;
    }

    /**
     * Finds the node with the minimum key value in the subtree rooted at the given node.
     *
     * @param node The node from which to start searching
     * @return The node with the minimum key value
     */
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Searches for a key in the AVL tree.
     *
     * @param key The key to search for
     * @return The node containing the key, or null if not found
     */
    public Node search(int key) {
        return search(root, key);
    }

    /**
     * Helper method to search for a key in the subtree rooted at the given node.
     *
     * @param node The node to start the search from
     * @param key The key to search for
     * @return The node containing the key, or null if not found
     */
    private Node search(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }

        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    /**
     * Performs a pre-order traversal of the AVL tree.
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * Helper method for pre-order traversal of the tree.
     *
     * @param node The node to start the traversal from
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * Performs an in-order traversal of the AVL tree.
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * Helper method for in-order traversal of the tree.
     *
     * @param node The node to start the traversal from
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    /**
     * Performs a post-order traversal of the AVL tree.
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * Helper method for post-order traversal of the tree.
     *
     * @param node The node to start the traversal from
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }
}
