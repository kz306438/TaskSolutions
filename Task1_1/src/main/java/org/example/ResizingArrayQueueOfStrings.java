package org.example;

/**
 * @brief A queue implementation that dynamically resizes an underlying array
 *        to accommodate more elements or shrink when needed.
 */
public class ResizingArrayQueueOfStrings implements QueueOfStrings {

    private static final int DEFAULT_CAPACITY = 4; // Стандартная начальная емкость
    private static final int RESIZE_THRESHOLD = 4; // Порог для уменьшения емкости

    private String[] queue;  // Подлежащий массив для хранения элементов
    private int first;       // Индекс первого элемента в очереди
    private int last;        // Индекс последнего элемента в очереди
    private int capacity;    // Текущая емкость массива
    private int size;        // Текущая размер очереди

    /**
     * @brief Constructs an empty queue with the specified initial capacity.
     * @param capacity The initial capacity of the queue.
     */
    public ResizingArrayQueueOfStrings(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY; // Установка минимальной емкости
        this.queue = new String[this.capacity];
        this.first = 0;
        this.last = 0;
        this.size = 0;
    }

    /**
     * @brief Resizes the internal array to a new capacity.
     * @param newCapacity The new capacity of the queue.
     * @throws IllegalArgumentException if the new capacity is less than the current size.
     */
    private void resize(int newCapacity) {
        if (newCapacity < size) {
            throw new IllegalArgumentException("New capacity cannot be less than the queue size");
        }

        String[] newQueue = new String[newCapacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(first + i) % capacity];
        }

        queue = newQueue;
        first = 0;
        last = size; // Обновление индексов после изменения массива
        capacity = newCapacity;
    }

    /**
     * @brief Adds a string to the end of the queue, resizing if necessary.
     * @param str The string to be added.
     */
    @Override
    public void enqueue(String str) {
        if (size == capacity) {
            resize(capacity * 2); // Увеличение емкости вдвое при переполнении
        }

        queue[last] = str;
        last = (last + 1) % capacity;
        size++;
    }

    /**
     * @brief Removes and returns the string at the front of the queue.
     * @return The string at the front of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    @Override
    public String dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        String str = queue[first];
        queue[first] = null; // Избежание утечек памяти
        first = (first + 1) % capacity;
        size--;

        // Уменьшение размера, если занятое место меньше четверти от емкости
        if (size > 0 && capacity / size >= RESIZE_THRESHOLD) {
            resize(capacity / 2); // Уменьшение емкости вдвое
        }

        return str;
    }

    /**
     * @brief Checks if the queue is empty.
     * @return True if the queue is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @brief Returns the number of elements in the queue.
     * @return The current size of the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @brief Main method for demonstration and testing.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings(5);

        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("fourth");
        queue.enqueue("fifth");
        queue.enqueue("sixth");

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
