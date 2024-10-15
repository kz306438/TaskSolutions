package org.deque;

public class DoublyLinkedDequeTest {

    public static void main(String[] args) {
        DoublyLinkedDeque<Integer> deque = new DoublyLinkedDeque<>();

        // Тестирование добавления элементов
        System.out.println("Тестирование добавления элементов слева и справа:");
        deque.pushLeft(1);
        deque.pushRight(2);
        deque.pushLeft(3);
        deque.pushRight(4);

        System.out.println("Элементы в деке после добавления (ожидается: 3 1 2 4):");
        printDeque(deque);

        // Тестирование удаления элементов слева
        System.out.println("\nУдаление элементов слева:");
        System.out.println("Удалено: " + deque.popLeft()); // 3
        System.out.println("Удалено: " + deque.popLeft()); // 1

        System.out.println("\nТекущие элементы после удаления слева (ожидается: 2 4):");
        printDeque(deque);

        // Тестирование удаления элементов справа
        System.out.println("\nУдаление элементов справа:");
        System.out.println("Удалено: " + deque.popRight()); // 4
        System.out.println("Удалено: " + deque.popRight()); // 2

        // Проверка состояния деки после удаления всех элементов
        System.out.println("\nДек пуст: " + deque.isEmpty());

        // Повторное добавление элементов
        deque.pushRight(10);
        deque.pushLeft(20);
        deque.pushRight(30);

        System.out.println("\nЭлементы в деке после повторного добавления (ожидается: 20 10 30):");
        printDeque(deque);

        System.out.println("\nТекущий размер деки: " + deque.size());
    }

    // Вспомогательный метод для вывода всех элементов деки
    private static void printDeque(DoublyLinkedDeque<?> deque) {
        for (Object element : deque) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
