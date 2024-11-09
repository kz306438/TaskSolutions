package org.redBlackBSTDeleteImpl;

public class RedBlackBSTClient {
    public static void main(String[] args) {
        RedBlackBST<Integer, String> rbTree = new RedBlackBST<>();

        // Вставка элементов
        rbTree.put(10, "Ten");
        rbTree.put(20, "Twenty");
        rbTree.put(5, "Five");
        rbTree.put(15, "Fifteen");
        rbTree.put(25, "Twenty-Five");
        rbTree.put(35, "Thirty-Five");

        // Вывод значений
        System.out.println("Получаем значения:");
        System.out.println("Ключ 10: " + rbTree.get(10));
        System.out.println("Ключ 20: " + rbTree.get(20));
        System.out.println("Ключ 5: " + rbTree.get(5));
        System.out.println("Ключ 15: " + rbTree.get(15));
        System.out.println("Ключ 25: " + rbTree.get(25));
        System.out.println("Ключ 35: " + rbTree.get(35));

        // Удаление максимального элемента
        System.out.println("\nУдаляем максимальный элемент:");
        rbTree.deleteMax();
        System.out.println("После удаления максимального элемента:");
        System.out.println("Ключ 35: " + rbTree.get(35));

        // Удаляем все элементы
        System.out.println("\nУдаляем все элементы:");
        rbTree.deleteMax();
        rbTree.deleteMax();
        rbTree.deleteMax();
        rbTree.deleteMax();
        rbTree.deleteMax();
        rbTree.deleteMax();
    }
}
