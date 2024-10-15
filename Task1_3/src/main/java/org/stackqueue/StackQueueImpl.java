package org.stackqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @brief Implementation of a Stack-Queue (Deque with stack and queue behavior)
 *        using a doubly linked list.
 *
 * @param <Item> the type of elements in this stack-queue
 */
public class StackQueueImpl<Item> implements StackQueue<Item>, Iterable<Item> {

    /**
     * @brief A private inner class representing a node in the doubly linked list.
     */
    private class Node {
        Item data;
        Node next;
        Node prev;

        Node(Item data) {
            this.data = data;
        }
    }

    private Node first; // The front of the stack-queue
    private Node last;  // The end of the stack-queue
    private int size;   // The number of elements in the stack-queue

    /**
     * @brief Initializes an empty stack-queue.
     */
    public StackQueueImpl() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * @brief Pushes an element onto the front of the stack-queue (stack behavior).
     *
     * @param element the element to push
     */
    @Override
    public void push(Item element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }

        size++;
    }

    /**
     * @brief Pops and removes the front element from the stack-queue (stack behavior).
     *
     * @return the popped element
     * @throws NoSuchElementException if the stack-queue is empty
     */
    @Override
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("StackQueue is empty");
        }

        Item item = first.data;

        if (first == last) {  // If there's only one element
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        size--;
        return item;
    }

    /**
     * @brief Enqueues an element to the end of the stack-queue (queue behavior).
     *
     * @param element the element to enqueue
     */
    @Override
    public void enqueue(Item element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }

        size++;
    }

    /**
     * @brief Checks if the stack-queue is empty.
     *
     * @return true if the stack-queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @brief Returns an iterator over the elements in this stack-queue.
     *
     * @return an iterator that iterates over the elements in order
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Item data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
