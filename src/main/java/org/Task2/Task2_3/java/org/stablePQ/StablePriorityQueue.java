package org.Task2.Task2_3.java.org.stablePQ;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Stable priority queue implementation.
 * @param <T> Type of elements in the queue.
 */
public class StablePriorityQueue<T> {

    /**
     * @param value          Element value
     * @param priority       Priority of the element
     * @param sequenceNumber Insertion order to ensure stability
     * @brief Node representing an entry in the queue with value, priority, and insertion order.
     */
        private record Node<T>(T value, int priority, long sequenceNumber) {
    }

    private final List<Node<T>> heap;    // Min-heap for the priority queue
    private long sequenceNumber = 0;     // Counter to maintain insertion order

    /**
     * @brief Constructs an empty stable priority queue.
     */
    public StablePriorityQueue() {
        heap = new ArrayList<>();
    }

    /**
     * @brief Adds an element to the queue with a given priority.
     * @param value Element to be added.
     * @param priority Priority level of the element.
     */
    public void add(T value, int priority) {
        Node<T> node = new Node<>(value, priority, sequenceNumber++);
        heap.add(node);
        siftUp(heap.size() - 1);
    }

    /**
     * @brief Removes and returns the element with the highest priority.
     * @return Element with the highest priority or null if the queue is empty.
     */
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T result = heap.getFirst().value;
        swap(0, heap.size() - 1);
        heap.removeLast();
        siftDown(0);
        return result;
    }

    /**
     * @brief Returns the element with the highest priority without removing it.
     * @return Element with the highest priority or null if the queue is empty.
     */
    public T peek() {
        return isEmpty() ? null : heap.getFirst().value;
    }

    /**
     * @brief Checks if the queue is empty.
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * @brief Returns the size of the queue.
     * @return Number of elements in the queue.
     */
    public int size() {
        return heap.size();
    }

    /**
     * @brief Restores heap property by moving the element at the given index up.
     * @param index Index of the element to sift up.
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * @brief Restores heap property by moving the element at the given index down.
     * @param index Index of the element to sift down.
     */
    private void siftDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < heap.size() && compare(heap.get(leftChild), heap.get(smallest)) < 0) {
                smallest = leftChild;
            }
            if (rightChild < heap.size() && compare(heap.get(rightChild), heap.get(smallest)) < 0) {
                smallest = rightChild;
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    /**
     * @brief Compares two nodes first by priority, then by insertion order.
     * @param a First node to compare.
     * @param b Second node to compare.
     * @return Negative if a has higher priority, positive if b does, zero if they are equal.
     */
    private int compare(Node<T> a, Node<T> b) {
        if (a.priority != b.priority) {
            return Integer.compare(a.priority, b.priority);
        }
        return Long.compare(a.sequenceNumber, b.sequenceNumber);
    }

    /**
     * @brief Swaps two elements in the heap.
     * @param i Index of the first element.
     * @param j Index of the second element.
     */
    private void swap(int i, int j) {
        Node<T> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
