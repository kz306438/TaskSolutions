package org.queueontwostacks;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @brief A queue implementation using two stacks.
 *        This implementation simulates a queue (FIFO) using two stacks (LIFO).
 * @param <Item> The type of elements held in this queue.
 */
public class QueueOnTwoStacks<Item> implements QueueInterface<Item>, Iterable<Item> {

    private Stack<Item> stackPush; ///< Stack used for enqueuing elements
    private Stack<Item> stackPop;  ///< Stack used for dequeuing elements

    /**
     * @brief Initializes an empty queue.
     */
    public QueueOnTwoStacks() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /**
     * @brief Adds an element to the end of the queue.
     * @param item The element to be added.
     */
    @Override
    public void enqueue(Item item) {
        stackPush.push(item);
    }

    /**
     * @brief Removes and returns the element from the front of the queue.
     * @return The element removed from the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    @Override
    public Item dequeue() {
        if (stackPop.isEmpty()) {
            if (stackPush.isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    /**
     * @brief Returns (but does not remove) the element from the front of the queue.
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    @Override
    public Item peek() {
        if (stackPop.isEmpty()) {
            if (stackPush.isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }

    /**
     * @brief Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return stackPush.isEmpty() && stackPop.isEmpty();
    }

    /**
     * @brief Returns the number of elements in the queue.
     * @return The size of the queue.
     */
    @Override
    public int size() {
        return stackPush.size() + stackPop.size();
    }

    /**
     * @brief Returns an iterator over the elements in the queue.
     * @return An iterator for the queue.
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Stack<Item> tempStack = new Stack<>();
            private Iterator<Item> popIterator = stackPop.iterator();
            private Iterator<Item> pushIterator;

            {
                for (Item item : stackPush) {
                    tempStack.push(item);
                }
                pushIterator = tempStack.iterator();
            }

            @Override
            public boolean hasNext() {
                return popIterator.hasNext() || pushIterator.hasNext();
            }

            @Override
            public Item next() {
                if (popIterator.hasNext()) {
                    return popIterator.next();
                } else if (pushIterator.hasNext()) {
                    return pushIterator.next();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

}
