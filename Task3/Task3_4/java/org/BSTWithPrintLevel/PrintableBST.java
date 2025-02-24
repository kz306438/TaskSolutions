package org.BSTWithPrintLevel;

import java.util.LinkedList;
import java.util.Queue;

import Task3.BinarySearchTree;
import Task3.Node;

/**
 * A binary search tree (BST) implementation that supports basic operations
 * such as insertion, deletion, and level-order traversal.
 */
class PrintableBST<T extends Comparable<T>> extends BinarySearchTree<T> {
    /**
     * Prints the keys of the nodes in the subtree rooted at the given node
     * in level-order. Each level is printed from left to right.
     *
     * @param node the root node of the subtree to print
     */
    private void printLevel(Node<T> node) {
        if (node == null) return;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            System.out.print(current.getKey() + " ");

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
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

    /**
     * Deletes a key from the binary search tree. If the key is not found,
     * the tree remains unchanged.
     *
     * @param key the key to be deleted
     */
    public void delete(T key) {
        root = deleteRec(root, key);
    }

    /**
     * Recursively deletes a key from the tree, starting from the given node.
     *
     * @param root the root node of the current subtree
     * @param key  the key to be deleted
     * @return the root node of the modified subtree
     */
    private Node<T> deleteRec(Node<T> root, T key) {
        if (root == null) return root;

        if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(deleteRec(root.getLeft(), key));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRight(deleteRec(root.getRight(), key));
        } else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();

            root.setKey(findMin(root.getRight()).getKey());
            root.setRight(deleteRec(root.getRight(), root.getKey()));
        }
        return root;
    }

    /**
     * Finds the node with the minimum key in the given subtree.
     *
     * @param node the root node of the subtree
     * @return the node with the minimum key
     */
    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
}
