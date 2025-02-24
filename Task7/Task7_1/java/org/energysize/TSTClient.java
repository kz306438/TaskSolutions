package org.energysize;

import Task7.TST;

public class TSTClient {
    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();

        // Тест 1: Добавление ключей
        System.out.println("=== Test 1: Adding Keys ===");
        tst.put("apple", 1);
        tst.put("app", 2);
        tst.put("banana", 3);
        tst.put("band", 4);
        tst.put("bat", 5);
        tst.put("ball", 6);
        System.out.println("Size after adding keys: " + tst.size()); // Ожидается: 6

        // Тест 2: Получение значений
        System.out.println("\n=== Test 2: Retrieving Values ===");
        System.out.println("Value for 'apple': " + tst.get("apple")); // Ожидается: 1
        System.out.println("Value for 'app': " + tst.get("app"));     // Ожидается: 2
        System.out.println("Value for 'banana': " + tst.get("banana"));// Ожидается: 3
        System.out.println("Value for 'band': " + tst.get("band"));   // Ожидается: 4
        System.out.println("Value for 'bat': " + tst.get("bat"));     // Ожидается: 5
        System.out.println("Value for 'ball': " + tst.get("ball"));   // Ожидается: 6
        System.out.println("Value for 'cat': " + tst.get("cat"));     // Ожидается: null

        // Тест 3: Удаление ключей
        System.out.println("\n=== Test 3: Deleting Keys ===");
        tst.delete("bat");
        tst.delete("banana");
        System.out.println("Size after deleting 'bat' and 'banana': " + tst.size()); // Ожидается: 4
        System.out.println("Value for 'bat' after deletion: " + tst.get("bat"));     // Ожидается: null
        System.out.println("Value for 'banana' after deletion: " + tst.get("banana")); // Ожидается: null
        System.out.println("Value for 'ball': " + tst.get("ball"));   // Ожидается: 6 (проверяем, что другие ключи остались)

        // Тест 4: Добавление существующего ключа с новым значением
        System.out.println("\n=== Test 4: Updating Existing Keys ===");
        tst.put("app", 10);
        System.out.println("Updated value for 'app': " + tst.get("app")); // Ожидается: 10
        System.out.println("Size after updating 'app': " + tst.size());  // Ожидается: 4 (размер не должен измениться)

        // Тест 5: Добавление новых ключей после удаления
        System.out.println("\n=== Test 5: Adding New Keys After Deletion ===");
        tst.put("cat", 7);
        tst.put("dog", 8);
        System.out.println("Size after adding 'cat' and 'dog': " + tst.size()); // Ожидается: 6
        System.out.println("Value for 'cat': " + tst.get("cat"));       // Ожидается: 7
        System.out.println("Value for 'dog': " + tst.get("dog"));       // Ожидается: 8

        // Тест 6: Пустая структура
        System.out.println("\n=== Test 6: Clearing All Keys ===");
        tst.delete("apple");
        tst.delete("app");
        tst.delete("band");
        tst.delete("ball");
        tst.delete("cat");
        tst.delete("dog");
        System.out.println("Size after deleting all keys: " + tst.size()); // Ожидается: 0
        System.out.println("Value for 'apple': " + tst.get("apple"));     // Ожидается: null
    }
}
