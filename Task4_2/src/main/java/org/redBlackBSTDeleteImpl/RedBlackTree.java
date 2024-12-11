package org.redBlackBSTDeleteImpl;


public class RedBlackTree<T extends Comparable<T>> {
    enum NodeColor {
        RED,
        BLACK,
        NONE
    }

    public class Node {
        private T value;
        private NodeColor nodeColor;
        private Node parent;
        private Node left;
        private Node right;

        public Node() {
            value = null;
            nodeColor = NodeColor.NONE;
            parent = null;
            left = null;
            right = null;
        }

        public Node(T value, NodeColor color) {
            this.value = value;
            nodeColor = color;
            parent = nil;
            left = nil;
            right = nil;
        }

        public Node(Node node) {
            value = node.value;
            nodeColor = node.nodeColor;
            parent = node.parent;
            left = node.left;
            right = node.right;
        }

        public boolean isFree() {
            return value == null || value == nil;
        }

        public boolean isLeftFree() {
            return left == null || left == nil;
        }

        public boolean isRightFree() {
            return right == null || right == nil;
        }

        public boolean isParentFree() {
            return parent == null || parent == nil;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node node) {
            parent = node;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node node) {
            left = node;
            if(left != null && left != nil) left.parent = this;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node node) {
            right = node;
            if(right != null && right != nil) right.parent = this;
        }

        public boolean isBlack() {
            return nodeColor == NodeColor.BLACK;
        }

        public void makeBlack() {
            nodeColor = NodeColor.BLACK;
        }

        public boolean isRed() {
            return nodeColor == NodeColor.RED;
        }

        public void makeRed() {
            nodeColor = NodeColor.RED;
        }

        public NodeColor getColor() {
            return nodeColor;
        }

        public void setColor(NodeColor color) {
            nodeColor = color;
        }

        public Node getGrandfather() {
            if(parent != null && parent != nil)
                return parent.parent;
            return null;
        }

        public Node getUncle() {
            Node grand = getGrandfather();
            if(grand != null) {
                if(grand.left == parent)
                    return grand.right;
                else if(grand.right == parent)
                    return grand.left;
            }
            return null;
        }

        public Node getSuccessor()
        {
            Node temp = null;
            Node node = this;
            if(!node.isRightFree()) {
                temp = node.getRight();
                while(!temp.isLeftFree())
                    temp = temp.getLeft();
                return temp;
            }
            temp = node.getParent();
            while(temp != nil && node == temp.getRight()) {
                node = temp;
                temp = temp.getParent();
            }
            return temp;
        }

        public String getColorName() {
            return ((isBlack()) ? "B" : "R");
        }

    }
    private Node root;
    private final Node nil; // Ограничитель, который обозначает нулевую ссылку.
    private Node current;
    private boolean isRemoved;

    public RedBlackTree() {
        root = new Node();
        nil = new Node();
        nil.nodeColor = NodeColor.BLACK;
        root.parent = nil;
        root.right = nil;
        root.left = nil;
        isRemoved = false;
    }

    /**
     * Static method that performs a left rotation of the tree relative to the node node.
     * @param tree - tree.
     * @param node - the node relative to which a left turn is performed.
     */
    private static <T extends Comparable<T>> void leftRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
        RedBlackTree<T>.Node nodeParent = node.getParent();
        RedBlackTree<T>.Node nodeRight = node.getRight();
        if(nodeParent != tree.nil) {
            if(nodeParent.getLeft() == node)
                nodeParent.setLeft(nodeRight);
            else
                nodeParent.setRight(nodeRight);
        }
        else {
            tree.root = nodeRight;
            tree.root.setParent(tree.nil);
        }
        node.setRight(nodeRight.getLeft());
        nodeRight.setLeft(node);
    }

    /**
     * Static method that performs a right rotation of the tree relative to the node node.
     * @param tree - tree.
     * @param node - the node relative to which a right turn is performed.
     */
    private static <T extends Comparable<T>> void rightRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
        RedBlackTree<T>.Node nodeParent = node.getParent();
        RedBlackTree<T>.Node nodeLeft = node.getLeft();
        if(nodeParent != tree.nil) {
            if(nodeParent.getLeft() == node)
                nodeParent.setLeft(nodeLeft);
            else
                nodeParent.setRight(nodeLeft);
        }
        else {
            tree.root = nodeLeft;
            tree.root.setParent(tree.nil);
        }
        node.setLeft(nodeLeft.getRight());
        nodeLeft.setRight(node);
    }

    /**
     * Implementation of a method for adding a gift item. Based on added value
     * a tree node of type {@link Node} is created, colored red.
     * @param o - value of type {@link Comparable} to insert into the tree.
     */
    public void insert(T o) {
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
    }


    /**
     * Tree fix to preserve red-black tree properties.
     * @param node - added node.
     */
    private void fixInsert(Node node) {
        Node temp;
        while(!node.isParentFree() && node.getParent().isRed()) {
            if(node.getParent() == node.getGrandfather().getLeft()) {
                temp = node.getGrandfather().getRight();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    node = node.getGrandfather();
                }
                else {
                    if(node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(this, node);
                    }
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    rightRotate(this, node.getGrandfather());
                }
            }
            else {
                temp = node.getGrandfather().getLeft();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    node = node.getGrandfather();
                }
                else {
                    if(node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(this, node);
                    }
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    leftRotate(this, node.getGrandfather());
                }
            }
        }
        root.makeBlack();
    }

    /**
     * Implementation of deleting a tree element.
     * @param o - value of type {@link Comparable} to remove from the tree.
     * @return true - if the element was removed;
     * false - if there is no element in the tree and it cannot be deleted.
     */
    public boolean delete(T o) {
        return delete(findNode(o));
    }

    private boolean delete(Node node)
    {
        Node temp = nil, successor = nil;

        if(node == null || node == nil)
            return false;

        if(node.isLeftFree() || node.isRightFree())
            successor = node;
        else
            successor = node.getSuccessor();

        if(!successor.isLeftFree())
            temp = successor.getLeft();
        else
            temp = successor.getRight();
        temp.setParent(successor.getParent());

        if(successor.isParentFree())
            root = temp;
        else if(successor == successor.getParent().getLeft())
            successor.getParent().setLeft(temp);
        else
            successor.getParent().setRight(temp);

        if(successor != node) {
            node.setValue(successor.getValue());
        }
        if(successor.isBlack())
            fixDelete(temp);
        return true;
    }

    /**
     * Fixes the tree after deleting an element to save
     * red-black properties of wood.
     * @param node - the value relative to which to produce
     * tree correction.
     */
    private void fixDelete(Node node)
    {
        Node temp;
        while(node != root && node.isBlack()) {
            if(node == node.getParent().getLeft()) {
                temp = node.getParent().getRight();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeRed();
                    leftRotate(this, node.getParent());
                    temp = node.getParent().getRight();
                }
                if(temp.getLeft().isBlack() && temp.getRight().isBlack()) {
                    temp.makeRed();
                    node = node.getParent();
                }
                else {
                    if(temp.getRight().isBlack()) {
                        temp.getLeft().makeBlack();
                        temp.makeRed();
                        rightRotate(this, temp);
                        temp = node.getParent().getRight();
                    }
                    temp.setColor(node.getParent().getColor());
                    node.getParent().makeBlack();
                    temp.getRight().makeBlack();
                    leftRotate(this, node.getParent());
                    node = root;
                }
            }
            else {
                temp = node.getParent().getLeft();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeRed();
                    rightRotate(this, node.getParent());
                    temp = node.getParent().getLeft();
                }
                if(temp.getLeft().isBlack() && temp.getRight().isBlack()) {
                    temp.makeRed();
                    node = node.getParent();
                }
                else {
                    if(temp.getLeft().isBlack()) {
                        temp.getRight().makeBlack();
                        temp.makeRed();
                        leftRotate(this, temp);
                        temp = node.getParent().getLeft();
                    }
                    temp.setColor(node.getParent().getColor());
                    node.getParent().makeBlack();
                    temp.getLeft().makeBlack();
                    rightRotate(this, node.getParent());
                    node = root;
                }
            }
        }
        node.makeBlack();
    }

    /**
     * Implements the function of checking for the content of an element in a tree.
     * @param o - value of type {@link Comparable} to search in the tree.
     * @return true - if the element is found; false - if the element is not found.
     */
    public boolean find(T o) {
        return (findNode(o) != nil);
    }

    /**
     * Search for tree node with value o.
     * @param o - value of type {@link Comparable} to search in the tree.
     * @return tree node;
     */
    private Node findNode(T o) {
        Node node = root;
        while(node != null && node != nil && node.getValue().compareTo(o) != 0) {
            if(node.getValue().compareTo(o) > 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return node;
    }

    /**
     * Removes the maximum element from the tree.
     * @return the removed maximum element, or null if the tree is empty.
     */
    public T deleteMax() {
        if (root == null || root.isFree()) {
            return null;
        }

        Node maxNode = root;
        while (!maxNode.isRightFree()) {
            maxNode = maxNode.getRight();
        }

        T maxValue = maxNode.getValue();
        delete(maxNode);
        return maxValue;
    }


    public void delete() {
        if(current != null && !isRemoved) {
            delete(current);
            isRemoved = true;
        }
        else
            throw new IllegalStateException();
    }
}