package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @brief A generic deque implementation using a resizing array.
 * @param <Item> The type of elements held in the deque.
 */
public class ResizingArrayDeque<Item> implements DequeInterface<Item>, Iterable<Item> {

    private static final int DEFAULT_CAPACITY = 4; ///< Standard initial capacity
    private static final int RESIZE_THRESHOLD = 4; ///< Threshold for reducing capacity

    private Item[] deque; ///< Internal array holding deque elements
    private int first; ///< Index of the first element in the deque
    private int last; ///< Index of the last element in the deque
    private int size; ///< Current number of elements in the deque
    private int capacity; ///< Current capacity of the deque

    /**
     * @brief Constructs a deque with the specified capacity.
     * @param capacity Initial capacity of the deque.
     */
    public ResizingArrayDeque(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        size = 0;
        first = 0;
        last = 0;
        deque = (Item[]) new Object[this.capacity];
    }

    /**
     * @brief Returns an iterator over the elements in the deque.
     * @return An iterator for the deque.
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
     * @brief Checks if the deque is empty.
     * @return true if the deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @brief Returns the number of elements in the deque.
     * @return The current size of the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @brief Adds an element to the front (left) of the deque.
     * @param item The element to add.
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
     * @brief Adds an element to the end (right) of the deque.
     * @param item The element to add.
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
     * @brief Removes and returns the element from the front (left) of the deque.
     * @return The element removed from the front.
     * @throws IllegalStateException if the deque is empty.
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
     * @brief Removes and returns the element from the end (right) of the deque.
     * @return The element removed from the end.
     * @throws IllegalStateException if the deque is empty.
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
     * @brief Resizes the internal array to the specified capacity.
     * @param newCapacity The new capacity for the deque.
     * @throws IllegalArgumentException if the new capacity is less than the number of elements in the deque.
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

    /**
     * @brief Main method for testing the ResizingArrayDeque functionality.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<>(4);

        System.out.println("Тест добавления элементов справа:");
        deque.pushRight(10);
        deque.pushRight(20);
        deque.pushRight(30);
        deque.pushRight(40);

        for (int item : deque) {
            System.out.println(item);
        }

        System.out.println("\nТест удаления элемента слева:");
        System.out.println("Удалено: " + deque.popLeft());
        System.out.println("Удалено: " + deque.popLeft());

        System.out.println("\nТекущие элементы после удаления слева:");
        for (int item : deque) {
            System.out.println(item);
        }

        System.out.println("\nТест добавления элементов слева:");
        deque.pushLeft(50);
        deque.pushLeft(60);

        for (int item : deque) {
            System.out.println(item);
        }

        System.out.println("\nТест удаления элемента справа:");
        System.out.println("Удалено: " + deque.popRight());
        System.out.println("Удалено: " + deque.popRight());

        System.out.println("\nТекущие элементы после удаления справа:");
        for (int item : deque) {
            System.out.println(item);
        }
    }
}
