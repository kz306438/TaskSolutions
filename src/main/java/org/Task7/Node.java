package org.Task7;

public class Node<Value> {
    protected char c;
    protected Node<Value> left, mid, right;
    protected Value value;
    protected int size = 0;

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public Node<Value> getLeft() {
        return left;
    }

    public void setLeft(Node<Value> left) {
        this.left = left;
    }

    public Node<Value> getMid() {
        return mid;
    }

    public void setMid(Node<Value> mid) {
        this.mid = mid;
    }

    public Node<Value> getRight() {
        return right;
    }

    public void setRight(Node<Value> right) {
        this.right = right;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
