package org.Task4.Task4_1.java.org.twoThreeST;

public class Node<T extends Comparable<T>> {
    Node left;
    Node mid;
    Node right;
    T leftElement;
    T rightElement;

    public Node() {
        left = mid = right = null;
        leftElement = rightElement = null;
    }

    public Node(T leftElement, T rightElement) {
        this.leftElement = leftElement;
        this.rightElement = rightElement;
        left = mid = right = null;
    }

    public Node(T leftElement, T rightElement, Node left, Node mid) {
        this.leftElement = leftElement;
        this.rightElement = rightElement;
        this.left = left;
        this.mid = mid;
    }

    public T getLeftElement() {
        return this.leftElement;
    }

    public T getRightElement() {
        return this.rightElement;
    }

    public void setLeftElement(T element) {
        this.leftElement = element;
    }

    public void setRightElement(T element) {
        this.rightElement = element;
    }

    public void setLeftSon(Node son) {
        this.left = son;
    }

    public Node getLeftSon() {
        return left;
    }

    public void setMidSon(Node son) {
        this.mid = son;
    }

    public Node getMidSon() {
        return mid;
    }

    public void setRightSon(Node son) {
        this.right = son;
    }

    public Node getRightSon() {
        return right;
    }

    public boolean isLeaf() {
        return left == null && mid == null && right == null;
    }

    public boolean is2Node() {
        return rightElement == null;
    }

    public boolean is3Node() {
        return rightElement != null;
    }

    public boolean isBalanced() {
        boolean balanced = false;
        if (isLeaf()) {
            balanced = true;
        } else if (left.getLeftElement() != null && mid.getLeftElement() != null) {
            if (rightElement != null) {
                if (right.getLeftElement() != null) {
                    balanced = true;
                }
            } else {
                balanced = true;
            }
        }
        return balanced;
    }

    public T replaceMax() {
        T max = null;
        if (!isLeaf()) {
            if (getRightElement() != null) {
                max = (T) right.replaceMax();
            } else {
                max = (T) mid.replaceMax();
            }
        } else {
            if (getRightElement() != null) {
                max = getRightElement();
                setRightElement(null);
            } else {
                max = getLeftElement();
                setLeftElement(null);
            }
        }
        if (!isBalanced()) rebalance();
        return max;
    }

    public T replaceMin() {
        T min = null;
        if (!isLeaf()) {
            min = (T) left.replaceMin();
        } else {
            min = leftElement;
            leftElement = null;
            if (rightElement != null) {
                leftElement = rightElement;
                rightElement = null;
            }
        }
        if (!isBalanced()) {
            rebalance();
        }
        return min;
    }

    public void rebalance() {
        while (!isBalanced()) {
            if (getLeftSon().getLeftElement() == null) {
                getLeftSon().setLeftElement(getLeftElement());
                setLeftElement((T) getMidSon().getLeftElement());
                if (getMidSon().getRightElement() != null) {
                    getMidSon().setLeftElement(getMidSon().getRightElement());
                    getMidSon().setRightElement(null);
                } else {
                    getMidSon().setLeftElement(null);
                }
            } else if (getMidSon().getLeftElement() == null) {
                if (getRightElement() == null) {
                    if (getLeftSon().getLeftElement() != null && getLeftSon().getRightElement() == null && getMidSon().getLeftElement() == null) {
                        setRightElement(getLeftElement());
                        setLeftElement((T) getLeftSon().getLeftElement());
                        setLeftSon(null);
                        setMidSon(null);
                        setRightSon(null);
                    } else {
                        getMidSon().setLeftElement(getLeftElement());
                        if (getLeftSon().getRightElement() == null) {
                            setLeftElement((T) getLeftSon().getLeftElement());
                            getLeftSon().setLeftElement(null);
                        } else {
                            setLeftElement((T) getLeftSon().getRightElement());
                            getLeftSon().setRightElement(null);
                        }
                        if (getLeftSon().getLeftElement() == null && getMidSon().getLeftElement() == null) {
                            setLeftSon(null);
                            setMidSon(null);
                            setRightSon(null);
                        }
                    }
                } else {
                    getMidSon().setLeftElement(getRightElement());
                    setRightElement((T) getRightSon().getLeftElement());
                    if (getRightSon().getRightElement() != null) {
                        getRightSon().setLeftElement(getRightSon().getRightElement());
                        getRightSon().setRightElement(null);
                    } else {
                        getRightSon().setLeftElement(null);
                    }
                }
            } else if (getRightElement() != null && getRightSon().getLeftElement() == null) {
                if (getMidSon().getRightElement() != null) {
                    getRightSon().setLeftElement(getRightElement());
                    setRightElement((T) getMidSon().getRightElement());
                    getMidSon().setRightElement(null);
                } else {
                    getMidSon().setRightElement(getRightElement());
                    setRightElement(null);
                }
            }
        }
    }
}
