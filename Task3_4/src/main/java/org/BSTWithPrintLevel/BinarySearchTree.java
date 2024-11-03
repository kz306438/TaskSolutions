package org.BSTWithPrintLevel;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary search tree (BST) implementation that supports basic operations
 * such as insertion, deletion, and level-order traversal.
 */
class BinarySearchTree {

    /**
     * Represents a single node in the binary search tree.
     */
    private class Node {
        int key;
        Node left, right;

        /**
         * Constructs a new Node with the specified key.
         *
         * @param key the key value of the new node
         */
        Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    private Node root;

    /**
     * Inserts a key into the binary search tree. If the key already exists,
     * no new node is created.
     *
     * @param key the key to be inserted
     */
    public void insert(int key) {
        root = insertRec(root, key);
    }

    /**
     * Recursively inserts a key into the tree, starting from the given node.
     *
     * @param root the root node of the current subtree
     * @param key  the key to be inserted
     * @return the root node of the modified subtree
     */
    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    /**
     * Deletes a key from the binary search tree. If the key is not found,
     * the tree remains unchanged.
     *
     * @param key the key to be deleted
     */
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    /**
     * Recursively deletes a key from the tree, starting from the given node.
     *
     * @param root the root node of the current subtree
     * @param key  the key to be deleted
     * @return the root node of the modified subtree
     */
    private Node deleteRec(Node root, int key) {
        if (root == null) return root;

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            root.key = findMin(root.right).key;
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    /**
     * Finds the node with the minimum key in the given subtree.
     *
     * @param node the root node of the subtree
     * @return the node with the minimum key
     */
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Prints the keys of the nodes in the subtree rooted at the given node
     * in level-order. Each level is printed from left to right.
     *
     * @param node the root node of the subtree to print
     */
    public void printLevel(Node node) {
        if (node == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.key + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * Prints the keys of the nodes in the tree in level-order, starting from
     * the root. Each level is printed from left to right.
     */
    public void printLevel() {
        printLevel(root);
    }
}
