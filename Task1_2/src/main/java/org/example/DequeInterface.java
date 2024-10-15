package org.example;

/**
 * @brief A generic interface for a deque (double-ended queue) structure.
 * @param <Item> The type of elements held in the deque.
 */
public interface DequeInterface<Item> {

    /**
     * @brief Checks if the deque is empty.
     * @return true if the deque is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * @brief Returns the number of elements in the deque.
     * @return The current size of the deque.
     */
    int size();

    /**
     * @brief Adds an element to the front (left) of the deque.
     * @param item The element to add.
     */
    void pushLeft(Item item);

    /**
     * @brief Adds an element to the end (right) of the deque.
     * @param item The element to add.
     */
    void pushRight(Item item);

    /**
     * @brief Removes and returns the element from the front (left) of the deque.
     * @return The element removed from the front.
     * @throws java.util.NoSuchElementException if the deque is empty.
     */
    Item popLeft();

    /**
     * @brief Removes and returns the element from the end (right) of the deque.
     * @return The element removed from the end.
     * @throws java.util.NoSuchElementException if the deque is empty.
     */
    Item popRight();
}
