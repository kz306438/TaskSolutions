package org.hashtable;

public class SeparateChainingHashTableTest {

    public static void main(String[] args) {
        // Создаем хеш-таблицу с начальной емкостью 10
        SeparateChainingHashTable<String, Integer> hashTable = new SeparateChainingHashTable<>(10);

        // Тестирование метода put() и get()
        System.out.println("Добавление элементов:");
        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        hashTable.put("cherry", 3);

        System.out.println("Значение для 'apple': " + hashTable.get("apple"));   // Ожидается: 1
        System.out.println("Значение для 'banana': " + hashTable.get("banana")); // Ожидается: 2
        System.out.println("Значение для 'cherry': " + hashTable.get("cherry")); // Ожидается: 3

        // Проверка обновления значения
        System.out.println("\nОбновление значения для 'banana':");
        hashTable.put("banana", 20);
        System.out.println("Новое значение для 'banana': " + hashTable.get("banana")); // Ожидается: 20

        // Тестирование метода delete()
        System.out.println("\nУдаление элемента 'apple':");
        hashTable.delete("apple");
        System.out.println("Значение для 'apple': " + hashTable.get("apple"));   // Ожидается: null

        // Тестирование метода keys()
        System.out.println("\nТекущие ключи в хеш-таблице:");
        for (String key : hashTable.keys()) {
            System.out.println(key);
        }

        // Добавление дополнительных элементов для тестирования коллизий
        System.out.println("\nДобавление элементов с потенциальными коллизиями:");
        hashTable.put("dog", 4);
        hashTable.put("cat", 5);
        hashTable.put("fish", 6);

        // Вывод значений и ключей
        System.out.println("Значение для 'dog': " + hashTable.get("dog")); // Ожидается: 4
        System.out.println("Значение для 'cat': " + hashTable.get("cat")); // Ожидается: 5
        System.out.println("Значение для 'fish': " + hashTable.get("fish")); // Ожидается: 6

        System.out.println("\nВсе ключи в хеш-таблице после добавления:");
        for (String key : hashTable.keys()) {
            System.out.println(key);
        }
    }
}
