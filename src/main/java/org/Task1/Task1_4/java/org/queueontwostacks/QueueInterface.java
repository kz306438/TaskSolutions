package org.Task1.Task1_4.java.org.queueontwostacks;

import java.util.NoSuchElementException;

/**
 * @brief Interface for a generic queue.
 * @param <Item> The type of elements held in the queue.
 */
public interface QueueInterface<Item> {
    /**
     * @brief Adds an item to the end of the queue.
     * @param item The item to be added.
     */
    void enqueue(Item item);

    /**
     * @brief Removes and returns the item at the front of the queue.
     * @return The item removed from the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    Item dequeue();

    /**
     * @brief Returns the item at the front of the queue without removing it.
     * @return The item at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    Item peek();

    /**
     * @brief Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * @brief Returns the size of the queue.
     * @return The number of elements in the queue.
     */
    int size();
}

