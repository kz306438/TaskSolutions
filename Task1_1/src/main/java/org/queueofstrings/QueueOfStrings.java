package org.queueofstrings;

/**
 * @brief Interface for a queue of strings.
 */
public interface QueueOfStrings {
    /**
     * @brief Adds a string to the end of the queue.
     * @param str The string to be added.
     */
    void enqueue(String str);

    /**
     * @brief Removes and returns the string from the front of the queue.
     * @return The string at the front of the queue.
     */
    String dequeue();

    /**
     * @brief Checks if the queue is empty.
     * @return True if the queue is empty, otherwise false.
     */
    boolean isEmpty();

    /**
     * @brief Returns the number of elements in the queue.
     * @return The current size of the queue.
     */
    int size();
}
