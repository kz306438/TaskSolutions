package org.Task1.Task1_2.java.org.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @brief A generic deque implementation using a resizing array.
 * @param <Item> The type of elements held in the deque.
 */
public class ResizingArrayDeque<Item> implements DequeInterface<Item>, Iterable<Item> {

    private static final int DEFAULT_CAPACITY = 4;
    /// < Standard initial capacity
    private static final int RESIZE_THRESHOLD = 4;
    /// < Threshold for reducing capacity

    private Item[] deque;
    /// < Internal array holding deque elements
    private int first;
    /// < Index of the first element in the deque
    private int last;
    /// < Index of the last element in the deque
    private int size;
    /// < Current number of elements in the deque
    private int capacity; ///< Current capacity of the deque

    /**
     * @param capacity Initial capacity of the deque.
     * @brief Constructs a deque with the specified capacity.
     */
    public ResizingArrayDeque(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        size = 0;
        first = 0;
        last = 0;
        deque = (Item[]) new Object[this.capacity];
    }

    /**
     * @return An iterator for the deque.
     * @brief Returns an iterator over the elements in the deque.
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Item item = deque[(first + currentIndex) % capacity];
                currentIndex++;
                return item;
            }
        };
    }

    /**
     * @return true if the deque is empty, false otherwise.
     * @brief Checks if the deque is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return The current size of the deque.
     * @brief Returns the number of elements in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @param item The element to add.
     * @brief Adds an element to the front (left) of the deque.
     */
    @Override
    public void pushLeft(Item item) {
        if (size == capacity) {
            resize(2 * capacity);
        }
        first = (first - 1 + capacity) % capacity;
        deque[first] = item;
        size++;
    }

    /**
     * @param item The element to add.
     * @brief Adds an element to the end (right) of the deque.
     */
    @Override
    public void pushRight(Item item) {
        if (size == capacity) {
            resize(2 * capacity);
        }
        deque[last] = item;
        last = (last + 1) % capacity;
        size++;
    }

    /**
     * @return The element removed from the front.
     * @throws IllegalStateException if the deque is empty.
     * @brief Removes and returns the element from the front (left) of the deque.
     */
    @Override
    public Item popLeft() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        Item item = deque[first];
        deque[first] = null;
        first = (first + 1) % capacity;
        size--;

        if (size > 0 && size == capacity / RESIZE_THRESHOLD) {
            resize(capacity / 2);
        }

        return item;
    }

    /**
     * @return The element removed from the end.
     * @throws IllegalStateException if the deque is empty.
     * @brief Removes and returns the element from the end (right) of the deque.
     */
    @Override
    public Item popRight() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        last = (last - 1 + capacity) % capacity;
        Item item = deque[last];
        deque[last] = null;
        size--;

        if (size > 0 && size <= capacity / RESIZE_THRESHOLD) {
            resize(capacity / 2);
        }

        return item;
    }

    /**
     * @param newCapacity The new capacity for the deque.
     * @throws IllegalArgumentException if the new capacity is less than the number of elements in the deque.
     * @brief Resizes the internal array to the specified capacity.
     */
    public void resize(int newCapacity) {
        if (newCapacity < size) {
            throw new IllegalArgumentException("New capacity cannot be less than the deque size");
        }

        Item[] newDeque = (Item[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newDeque[i] = deque[(first + i) % capacity];
        }

        first = 0;
        last = size;
        deque = newDeque;
        capacity = newCapacity;
    }
}
