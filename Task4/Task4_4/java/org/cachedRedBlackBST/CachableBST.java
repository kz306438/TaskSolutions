package org.cachedRedBlackBST;

import Task4.RedBlackTree;

public class CachableBST<T extends Comparable<T>> extends RedBlackTree<T> {
    private Node lastAccessedNode; // Stores the last accessed node.

    public CachableBST() {
        root = new Node();
        nil = new Node();
        nil.nodeColor = NodeColor.BLACK;
        root.parent = nil;
        root.right = nil;
        root.left = nil;
        isRemoved = false;
        lastAccessedNode = null;
    }

    /**
     * Implementation of a method for adding a gift item. Based on added value
     * a tree node of type {@link Node} is created, colored red.
     * @param o - value of type {@link Comparable} to insert into the tree.
     */
    public void insert(T o) {
        if (lastAccessedNode != null && lastAccessedNode.getValue() != null &&
                lastAccessedNode.getValue().compareTo(o) == 0) {
            // Key matches the last accessed node; no need to search.
            System.out.println("was inserted!");
            return;
        }

        Node node = root, temp = nil;
        Node newNode = new Node((T)o, NodeColor.RED);
        while(node != null && node != nil && !node.isFree()) {
            temp = node;
            if(newNode.getValue().compareTo(node.getValue()) < 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        newNode.setParent(temp);
        if(temp == nil)
            root.setValue(newNode.getValue());
        else {
            if(newNode.getValue().compareTo(temp.getValue()) < 0)
                temp.setLeft(newNode);
            else
                temp.setRight(newNode);
        }
        newNode.setLeft(nil);
        newNode.setRight(nil);
        fixInsert(newNode);

        lastAccessedNode = newNode;
    }

    /**
     * Implements the function of checking for the content of an element in a tree.
     * @param o - value of type {@link Comparable} to search in the tree.
     * @return true - if the element is found; false - if the element is not found.
     */
    public boolean find(T o) {
        Node node = findNode(o);
        lastAccessedNode = node; // Update last accessed node.
        return node != nil;
    }

    private Node findNode(T o) {
        if (lastAccessedNode != null && lastAccessedNode.getValue() != null &&
                lastAccessedNode.getValue().compareTo(o) == 0) {
            // Key matches the last accessed node; return it directly.
            System.out.println("was founded!");
            return lastAccessedNode;
        }


        Node node = root;
        while(node != null && node != nil && node.getValue().compareTo(o) != 0) {
            if(node.getValue().compareTo(o) > 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return node;
    }

}