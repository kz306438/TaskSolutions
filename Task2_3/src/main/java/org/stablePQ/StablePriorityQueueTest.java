package org.stablePQ;

public class StablePriorityQueueTest {

    public static void main(String[] args) {
        StablePriorityQueue<String> queue = new StablePriorityQueue<>();

        System.out.println("Adding elements to the queue...");
        queue.add("Alice", 2);
        queue.add("Bob", 1);
        queue.add("Charlie", 2);
        queue.add("Diana", 3);
        queue.add("Eve", 1);

        System.out.println("Polling elements from the queue in priority order:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        // Additional test cases to verify stability and functionality
        System.out.println("\nTesting stability with same-priority elements:");
        queue.add("First", 1);
        queue.add("Second", 1);
        queue.add("Third", 1);

        System.out.println("Polling elements (should follow insertion order for equal priorities):");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        System.out.println("\nTesting priority order:");
        queue.add("Low priority", 5);
        queue.add("Medium priority", 3);
        queue.add("High priority", 1);

        System.out.println("Peeking element (should be 'High priority'):");
        System.out.println(queue.peek());

        System.out.println("Polling elements in order:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        System.out.println("\nTesting behavior with an empty queue:");
        System.out.println("Polling from an empty queue (should return null): " + queue.poll());
        System.out.println("Peeking from an empty queue (should return null): " + queue.peek());
    }
}
