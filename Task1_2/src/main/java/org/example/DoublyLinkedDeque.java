package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @brief A generic deque implementation using a doubly linked list.
 * @param <Item> The type of elements held in the deque.
 */
public class DoublyLinkedDeque<Item> implements DequeInterface<Item>, Iterable<Item> {

    /**
     * @brief A private inner class representing a node in the doubly linked list.
     */
    private class Node {
        Item data; ///< Data stored in the node
        Node prev; ///< Previous node
        Node next; ///< Next node

        /**
         * @brief Constructs a node with the given data.
         * @param data The data to store in the node.
         */
        Node(Item data) {
            this.data = data;
        }
    }

    private Node first; ///< Pointer to the first node
    private Node last; ///< Pointer to the last node
    private int size; ///< Current number of elements in the deque

    /**
     * @brief Constructs an empty doubly linked deque.
     */
    public DoublyLinkedDeque() {
        first = null;
        last = null;
        size = 0;
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
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    /**
     * @brief Adds an element to the end (right) of the deque.
     * @param item The element to add.
     */
    @Override
    public void pushRight(Item item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;
    }

    /**
     * @brief Removes and returns the element from the front (left) of the deque.
     * @return The element removed from the front.
     * @throws NoSuchElementException if the deque is empty.
     */
    @Override
    public Item popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item data = first.data;

        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        size--;
        return data;
    }

    /**
     * @brief Removes and returns the element from the end (right) of the deque.
     * @return The element removed from the end.
     * @throws NoSuchElementException if the deque is empty.
     */
    @Override
    public Item popRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item data = last.data;

        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        size--;
        return data;
    }

    /**
     * @brief Returns an iterator over the elements in the deque.
     * @return An iterator for the deque.
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Item data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * @brief Main method for testing the DoublyLinkedDeque functionality.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        DoublyLinkedDeque<Integer> deque = new DoublyLinkedDeque<>();

        // Тестирование добавления элементов
        System.out.println("Тестирование добавления элементов слева и справа:");
        deque.pushLeft(1);
        deque.pushRight(2);
        deque.pushLeft(3);
        deque.pushRight(4);

        System.out.println("Элементы в деке после добавления:");
        for (Integer element : deque) {
            System.out.println(element);
        }

        // Тестирование удаления элементов
        System.out.println("\nУдаление элементов слева:");
        System.out.println("Удалено: " + deque.popLeft());
        System.out.println("Удалено: " + deque.popLeft());

        System.out.println("\nТекущие элементы после удаления слева:");
        for (Integer element : deque) {
            System.out.println(element);
        }

        System.out.println("\nУдаление элементов справа:");
        System.out.println("Удалено: " + deque.popRight());
        System.out.println("Удалено: " + deque.popRight());

        // Проверка состояния деки после удаления всех элементов
        System.out.println("\nДек пуст: " + deque.isEmpty());

        // Повторное добавление элементов для проверки корректной работы после очистки
        deque.pushRight(10);
        deque.pushLeft(20);
        deque.pushRight(30);

        System.out.println("\nЭлементы в деке после повторного добавления:");
        for (Integer element : deque) {
            System.out.println(element);
        }

        System.out.println("\nТекущий размер деки: " + deque.size());
    }

}
