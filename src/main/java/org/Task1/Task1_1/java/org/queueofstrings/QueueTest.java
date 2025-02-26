package org.Task1.Task1_1.java.org.queueofstrings;

/**
 * @brief Client class to test the functionality of ResizingArrayQueueOfStrings.
 */
public class QueueTest {
    public static void main(String[] args) {
        ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings(4);

        // Test: Adding elements and automatic resizing
        System.out.println("=== Test 1: Enqueue and automatic resizing ===");
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("fourth");
        queue.enqueue("fifth");
        queue.enqueue("sixth");

        System.out.println("Queue size after enqueueing: " + queue.size());  // Expected: 6
        printQueue(queue);

        // Test: Removing elements and shrinking
        System.out.println("\n=== Test 2: Dequeue and automatic shrinking ===");
        System.out.println("Dequeue: " + queue.dequeue());  // Expected: first
        System.out.println("Dequeue: " + queue.dequeue());  // Expected: second
        System.out.println("Dequeue: " + queue.dequeue());  // Expected: third

        System.out.println("Queue size after dequeueing: " + queue.size());  // Expected: 3
        printQueue(queue);

        // Test: Remaining elements
        System.out.println("\nDequeue: " + queue.dequeue());  // Expected: fourth
        System.out.println("Dequeue: " + queue.dequeue());  // Expected: fifth
        System.out.println("Dequeue: " + queue.dequeue());  // Expected: sixth

        System.out.println("Queue is empty: " + queue.isEmpty());  // Expected: true

        // Test: Exception when dequeuing from an empty queue
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("\nCaught exception: " + e.getMessage());  // Expected: Queue is empty
        }

        // Test: Enqueueing after emptying the queue
        System.out.println("\n=== Test 3: Enqueue after emptying the queue ===");
        queue.enqueue("seventh");
        queue.enqueue("eighth");
        queue.enqueue("ninth");
        printQueue(queue);
    }

    /**
     * @brief Helper method to print the contents of the queue.
     * @param queue The queue to be printed.
     */
    private static void printQueue(ResizingArrayQueueOfStrings queue) {
        System.out.println("Queue contents:");
        ResizingArrayQueueOfStrings tempQueue = new ResizingArrayQueueOfStrings(queue.size());

        // Temporary queue for display, so the original queue remains intact
        while (!queue.isEmpty()) {
            String item = queue.dequeue();
            System.out.println(item);
            tempQueue.enqueue(item);
        }

        // Restore original queue after display
        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }
    }
}
