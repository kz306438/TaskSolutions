package org.twoThreeST;

import java.util.*;

class TwoThreeTree<T extends Comparable<T>> extends Node {

    private Node root;
    private int size;
    private static final int BigRoot = 1;
    private static final int SmallRoot = -1;
    private boolean addition;

    public TwoThreeTree() {

        this.root = new Node();
        size = 0;
    }

    public TwoThreeTree(Collection<T> elements) {
        this.root = new Node();
        this.size = 0;
        elements.forEach(this::Insert);
    }
    public boolean Insert(T element) {
        size++;
        addition = false;
        if(root == null || root.getLeftElement() == null) {
            if(root == null)
                root = new Node();
            root.setLeftElement(element);
            addition = true;   }
        else {
            Node newRoot = InsertNotFull(root, element);
            if(newRoot != null)
                root = newRoot;
        }
        if(!addition)
            size--;
        return addition;
    }

    private Node InsertNotFull(Node current, T element) {
        Node newParent = null;
        if(!current.isLeaf()) {
            Node NewLeaf = null;
             if (current.leftElement.compareTo(element) == BigRoot) {
                NewLeaf = InsertNotFull(current.left, element);
                if (NewLeaf != null) {
                    if (current.is2Node()) {
                        current.rightElement    = current.leftElement;
                        current.leftElement     = NewLeaf.leftElement;
                        current.right           = current.mid;
                        current.mid             = NewLeaf.mid;
                        current.left            = NewLeaf.left; }
                    else {
                        Node rightCopy = new Node(current.rightElement, null, current.mid, current.right);
                        newParent = new Node(current.leftElement, null, NewLeaf, rightCopy);
                    }
                }
            }
            else if (current.is2Node() || (current.is3Node() && current.rightElement.compareTo(element) == BigRoot)) {
                NewLeaf = InsertNotFull(current.mid, element);

                if (NewLeaf != null) {
                    if (current.is2Node()) {
                        current.rightElement    = NewLeaf.leftElement;
                        current.right           = NewLeaf.mid;
                        current.mid             = NewLeaf.left;
                    }
                    else {
                        Node left 	= new Node(current.leftElement, null, current.left, NewLeaf.left);
                        Node mid 	= new Node(current.rightElement, null, NewLeaf.mid, current.right);
                        newParent 	= new Node(NewLeaf.leftElement, null, left, mid);
                    }
                }
            }
            else if (current.is3Node() && current.rightElement.compareTo(element) == SmallRoot) {
                NewLeaf = InsertNotFull(current.right, element);
                if (NewLeaf != null) { // Split, the right element goes up
                    Node leftCopy   = new Node(current.leftElement, null, current.left, current.mid);
                    newParent       = new Node(current.rightElement, null, leftCopy, NewLeaf);  }
            }
        }
        else {
            addition = true;
            if (current.leftElement.compareTo(element) == 0 || (current.is3Node() && current.rightElement.compareTo(element) == 0)) {
                addition = false;  }
            else if (current.is2Node()) {
                if (current.leftElement.compareTo(element) == BigRoot) {
                    current.rightElement    = current.leftElement;
                    current.leftElement     = element;          }
                else if (current.leftElement.compareTo(element) == SmallRoot)
                    current.rightElement = element;         }
            else newParent = split(current, element);
        }
        return newParent;
    }

    // split the Node
    private Node split(Node current, T element) {
        Node newParent = null;

        // The left element is bigger, so it will go up letting the new element on the left
        if (current.leftElement.compareTo(element) == BigRoot) {

            Node left   = new Node(element, null);
            Node right  = new Node(current.rightElement, null);
            newParent   = new Node(current.leftElement, null, left, right);

        } else if (current.leftElement.compareTo(element) == SmallRoot) {

            if (current.rightElement.compareTo(element) == BigRoot) {

                Node left   = new Node(current.leftElement, null);
                Node right  = new Node(current.rightElement, null);
                newParent   = new Node(element, null, left, right);

            } else { // The new element is the biggest one, so the current right element goes up

                Node left   = new Node(current.leftElement, null);
                Node right  = new Node(element, null);
                newParent   = new Node(current.rightElement, null, left, right);
            }
        }

        return newParent;
    }

    public T find(T element) {

        return find(root, element);
    }
    private T find(Node current, T element) {

        T found = null;
        if(current != null) {
            // Trivial case, we have found the element
            if(current.leftElement != null && current.leftElement.equals(element))
                found = (T) current.leftElement;
            else {
                // Second trivial case, the element to find equals the right element
                if(current.rightElement != null && current.rightElement.equals(element))
                    found = (T) current.rightElement;
                else {
                    // Recursive cases
                    if(current.leftElement.compareTo(element) == BigRoot) {
                        found = find(current.left, element);
                    }
                    else if(current.right == null || current.rightElement.compareTo(element) == BigRoot) {
                        found = find(current.mid, element);
                    }
                    else if (current.rightElement.compareTo(element) == SmallRoot) {
                        found = find(current.right, element);
                    }
                    else
                        return null;          }
            }
        }
        return found;
    }
    /**
     * @return The min element of the tree
     */
    public T findMin() {
        if (isEmpty()) return null;
        return findMin(root);   }
    private T findMin(Node current) {

        if(current.getLeftSon() == null) return (T) current.leftElement;	// trivial case
        else{
            Node temp=current;
            while(temp.left!=null){
                temp=temp.left;  }
            return (T) temp.leftElement;	}        }

    //return The max element of the tree
    public T findMax() {
        if (isEmpty()) return null;
        return findMax(root);	}

    private T findMax(Node current) {

        if(current.rightElement != null && current.getRightSon() != null) return findMax(current.getRightSon());
        else if(current.getMidSon() != null) return findMax(current.getMidSon());

        if(current.rightElement != null) return (T) current.rightElement;
        else return (T) current.leftElement;
    }
    private boolean isEmpty() {
        if(root == null) return true;
        if(root.getLeftElement() == null) return true;
        return false;
    }

    public void inOrder() {

        if(!isEmpty()) {

            inOrder(root);
        }
        else System.out.print("The tree is empty");
    }

    private void inOrder(Node current) {

        if(current != null) {

            if(current.isLeaf()) {

                System.out.print(" "+current.getLeftElement().toString());
                if(current.getRightElement() != null)
                    System.out.print(" "+current.getRightElement().toString());
            }
            else {

                inOrder(current.getLeftSon());
                System.out.print(" "+current.getLeftElement().toString());

                inOrder(current.getMidSon());

                if(current.getRightElement() != null) {

                    if(!current.isLeaf())
                        System.out.print(" "+current.getRightElement().toString());

                    inOrder(current.getRightSon());
                }
            }
        }
    }

    public boolean remove(T element) {
        boolean deleted;

        // We decrease the number of levels at the start, if the element is not deleted, we increase the value at the end
        this.size--;
        deleted = remove(root, element);
        root.rebalance();

        if(root.getLeftElement() == null) root = null;
        if(!deleted) this.size++;
        return deleted;
    }

    private boolean remove(Node current, T element) {
        boolean deleted = true;
        if(current == null)
            deleted = false;
        else {
            if(!current.getLeftElement().equals(element)) {
                if(current.getRightElement() == null || current.getRightElement().compareTo(element) == BigRoot) {
                    if(current.getLeftElement().compareTo(element) == BigRoot) {
                        deleted = remove(current.left, element);   }
                    else {
                        deleted = remove(current.mid, element);  }                                 }
                else {
                    if(!current.getRightElement().equals(element)) {
                        deleted = remove(current.right, element);   }
                    else {
                        if(current.isLeaf()) current.setRightElement(null);
                        else {
                            T replacement = (T) current.getRightSon().replaceMin();
                            current.setRightElement(replacement);               }
                    }               }
            }
            else {
                if(current.isLeaf()) {
                    if(current.getRightElement() != null) {
                        current.setLeftElement(current.getRightElement());
                        current.setRightElement(null);
                    }
                    else {
                        current.setLeftElement(null);
                        return true;
                    }
                }
                else {
                    T replacement = (T) current.getLeftSon().replaceMax();
                    current.setLeftElement(replacement);
                }
            }
        }
        if(current != null && !current.isBalanced()) {
            current.rebalance();
        }
        else if(current != null && !current.isLeaf()) {
            boolean balanced = false;
            while(!balanced) {
                if(current.getRightSon() == null) {
                    if(current.getLeftSon().isLeaf() && !current.getMidSon().isLeaf()) {
                        T replacement = (T) current.getMidSon().replaceMin();
                        T readdition = (T) current.getLeftElement();
                        current.setLeftElement(replacement);
                        Insert(readdition);
                    } else if(!current.getLeftSon().isLeaf() && current.getMidSon().isLeaf()) {
                        if(current.getRightElement() == null) {
                            T replacement = (T) current.getLeftSon().replaceMax();
                            T readdition = (T) current.getLeftElement();
                            current.setLeftElement(replacement);
                            Insert(readdition);
                        }
                    }
                }
                if(current.getRightSon() != null) {
                    if(current.getMidSon().isLeaf() && !current.getRightSon().isLeaf()) {
                        current.getRightSon().rebalance();   }
                    if(current.getMidSon().isLeaf() && !current.getRightSon().isLeaf()) {
                        T replacement = (T) current.getRightSon().replaceMin();
                        T readdition = (T) current.getRightElement();
                        current.setRightElement(replacement);
                        Insert(readdition);	}
                    else balanced = true;			}
                if(current.isBalanced()) balanced = true;		}
        }
        return deleted;  }

    private int size() {
        return size; 	}
}