package org.Task4.Task4_3.java.org.AVLTree;

public class AVLTreeTest {

    public static void main(String[] args) {
        // Создаем экземпляр AVL-дерева
        AVLTree tree = new AVLTree();

        // Тестирование вставки
        System.out.println("Вставка ключей: 10, 20, 30, 40, 50, 25");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        // Печать деревьев после вставки
        System.out.print("Pre-order traversal: ");
        tree.preOrder();
        System.out.println();

        System.out.print("In-order traversal: ");
        tree.inOrder();
        System.out.println();

        System.out.print("Post-order traversal: ");
        tree.postOrder();
        System.out.println();

        // Тестирование поиска
        System.out.println("\nПоиск ключа 25:");
        AVLTree.Node result = tree.search(25);
        if (result != null) {
            System.out.println("Ключ найден: " + result.key);
        } else {
            System.out.println("Ключ не найден.");
        }

        // Тестирование удаления
        System.out.println("\nУдаление ключа 20:");
        tree.delete(20);

        // Печать дерева после удаления
        System.out.print("In-order traversal после удаления: ");
        tree.inOrder();
        System.out.println();

        // Тестирование удаления несуществующего ключа
        System.out.println("\nПопытка удалить ключ 100:");
        tree.delete(100);
        System.out.print("In-order traversal после удаления ключа 100: ");
        tree.inOrder();
        System.out.println();
    }
}