package org.deque;

public class ResizingArrayDequeTest {

    public static void main(String[] args) {
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<>(4);

        // Тест добавления элементов справа
        System.out.println("Тест добавления элементов справа:");
        deque.pushRight(10);
        deque.pushRight(20);
        deque.pushRight(30);
        deque.pushRight(40);
        printDeque(deque);

        // Тест удаления элементов слева
        System.out.println("\nТест удаления элементов слева:");
        System.out.println("Удалено: " + deque.popLeft());
        System.out.println("Удалено: " + deque.popLeft());
        printDeque(deque);

        // Тест добавления элементов слева
        System.out.println("\nТест добавления элементов слева:");
        deque.pushLeft(50);
        deque.pushLeft(60);
        printDeque(deque);

        // Тест удаления элементов справа
        System.out.println("\nТест удаления элементов справа:");
        System.out.println("Удалено: " + deque.popRight());
        System.out.println("Удалено: " + deque.popRight());
        printDeque(deque);

        // Тест повторного добавления и проверки изменения размера
        System.out.println("\nТест добавления большего количества элементов:");
        deque.pushLeft(70);
        deque.pushRight(80);
        deque.pushLeft(90);
        deque.pushRight(100);
        printDeque(deque);

        System.out.println("\nТекущий размер деки: " + deque.size());
    }

    // Вспомогательный метод для вывода всех элементов деки
    private static void printDeque(ResizingArrayDeque<?> deque) {
        for (Object item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
