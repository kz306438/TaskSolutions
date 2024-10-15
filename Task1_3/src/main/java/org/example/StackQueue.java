package org.example;

import java.util.NoSuchElementException;

/**
 * @brief Stack-Queue interface with stack and queue behavior.
 * Supports push, pop, and enqueue operations.
 *
 * @param <Item> the type of elements in this stack-queue
 */
public interface StackQueue<Item> {
    /**
     * @brief Pushes an element onto the front of the stack-queue (stack behavior).
     *
     * @param element the element to push
     */
    void push(Item element);

    /**
     * @brief Pops and removes the front element from the stack-queue (stack behavior).
     *
     * @return the popped element
     * @throws NoSuchElementException if the stack-queue is empty
     */
    Item pop();

    /**
     * @brief Enqueues an element to the end of the stack-queue (queue behavior).
     *
     * @param element the element to enqueue
     */
    void enqueue(Item element);

    /**
     * @brief Checks if the stack-queue is empty.
     *
     * @return true if the stack-queue is empty, false otherwise
     */
    boolean isEmpty();
}
