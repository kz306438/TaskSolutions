package org.stackqueue;

import java.util.NoSuchElementException;

public class StackQueueTest {

    public static void main(String[] args) {
        StackQueueImpl<Integer> stackQueue = new StackQueueImpl<>();

        System.out.println("Testing push operation:");
        stackQueue.push(10);
        stackQueue.push(20);
        stackQueue.push(30);
        System.out.println("Stack-Queue after pushing 10, 20, 30 (expected order: 30, 20, 10):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
        System.out.println("\n");

        System.out.println("Testing enqueue operation:");
        stackQueue.enqueue(40);
        stackQueue.enqueue(50);
        System.out.println("Stack-Queue after enqueuing 40, 50 (expected order: 30, 20, 10, 40, 50):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
        System.out.println("\n");

        System.out.println("Testing pop operation:");
        System.out.println("Popped element: " + stackQueue.pop()); // Should pop 30
        System.out.println("Stack-Queue after popping (expected order: 20, 10, 40, 50):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
        System.out.println("\n");

        System.out.println("Testing pop until empty:");
        while (!stackQueue.isEmpty()) {
            System.out.println("Popped element: " + stackQueue.pop());
            System.out.println("Stack-Queue: ");
            for (Integer element : stackQueue) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        System.out.println("\nTesting pop on empty Stack-Queue (expect exception):");
        try {
            stackQueue.pop();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("\nTesting enqueue and push after clearing:");
        stackQueue.enqueue(60);
        stackQueue.push(70);
        System.out.println("Stack-Queue after enqueuing 60 and pushing 70 (expected order: 70, 60):");
        for (Integer element : stackQueue) {
            System.out.print(element + " ");
        }
    }
}
