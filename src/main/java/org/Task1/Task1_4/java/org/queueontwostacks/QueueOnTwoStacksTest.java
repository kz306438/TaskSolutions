package org.Task1.Task1_4.java.org.queueontwostacks;

import java.util.NoSuchElementException;

public class QueueOnTwoStacksTest {
    public static void main(String[] args) {
        QueueOnTwoStacks<Integer> queue = new QueueOnTwoStacks<>();

        // Тестирование добавления элементов (enqueue)
        System.out.println("Добавляем элементы: 1, 2, 3");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // Тестирование размера очереди
        System.out.println("Текущий размер очереди: " + queue.size()); // Ожидается: 3

        // Тестирование итерации
        System.out.println("Элементы в очереди (итерация):");
        for (int item : queue) {
            System.out.print(item + " ");
        }
        System.out.println(); // Ожидается: 1 2 3

        // Тестирование извлечения элементов (dequeue)
        System.out.println("Удаляем элемент: " + queue.dequeue()); // Ожидается: 1
        System.out.println("Удаляем элемент: " + queue.dequeue()); // Ожидается: 2

        // Текущий размер после удаления
        System.out.println("Текущий размер очереди: " + queue.size()); // Ожидается: 1

        // Добавление ещё одного элемента
        System.out.println("Добавляем элемент: 4");
        queue.enqueue(4);

        // Проверка метода peek
        System.out.println("Первый элемент (peek): " + queue.peek()); // Ожидается: 3

        // Тестирование итерации после изменений
        System.out.println("Элементы в очереди после изменений:");
        for (int item : queue) {
            System.out.print(item + " ");
        }
        System.out.println(); // Ожидается: 3 4

        // Тестирование удаления всех элементов
        System.out.println("Удаляем элемент: " + queue.dequeue()); // Ожидается: 3
        System.out.println("Удаляем элемент: " + queue.dequeue()); // Ожидается: 4

        // Тестирование пустоты очереди
        System.out.println("Очередь пуста? " + queue.isEmpty()); // Ожидается: true

        // Тестирование ошибки при извлечении из пустой очереди
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Ошибка: " + e.getMessage()); // Ожидается: Queue is empty
        }
    }
}
