package org.stackdeque;

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

    /**
     * @brief The main method for testing the stack-queue implementation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        StackQueueImpl<Integer> stackQueue = new StackQueueImpl<>();

        System.out.println("Testing push operation:");
        stackQueue.push(10);
        stackQueue.push(20);
        stackQueue.push(30);
        System.out.println("Stack-Queue after pushing 10, 20, 30 (expected order: 30, 20, 10):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
        System.out.println("\n");

        System.out.println("Testing enqueue operation:");
        stackQueue.enqueue(40);
        stackQueue.enqueue(50);
        System.out.println("Stack-Queue after enqueuing 40, 50 (expected order: 30, 20, 10, 40, 50):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
        System.out.println("\n");

        System.out.println("Testing pop operation:");
        System.out.println("Popped element: " + stackQueue.pop()); // Should pop 30
        System.out.println("Stack-Queue after popping (expected order: 20, 10, 40, 50):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
        System.out.println("\n");

        System.out.println("Testing pop until empty:");
        while (!stackQueue.isEmpty()) {
            System.out.println("Popped element: " + stackQueue.pop());
            System.out.println("Stack-Queue: ");
            for (Integer element : stackQueue) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        System.out.println("\nTesting pop on empty Stack-Queue (expect exception):");
        try {
            stackQueue.pop();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("\nTesting enqueue and push after clearing:");
        stackQueue.enqueue(60);
        stackQueue.push(70);
        System.out.println("Stack-Queue after enqueuing 60 and pushing 70 (expected order: 70, 60):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
    }

}
