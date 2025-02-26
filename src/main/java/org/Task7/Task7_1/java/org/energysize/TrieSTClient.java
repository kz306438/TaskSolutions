package org.Task7.Task7_1.java.org.energysize;

public class TrieSTClient {
    public static void main(String[] args) {
        TrieST<Integer> trie = new TrieST<>();

        // Тест 1: Добавление ключей
        System.out.println("=== Test 1: Adding Keys ===");
        trie.put("apple", 1);
        trie.put("app", 2);
        trie.put("banana", 3);
        trie.put("band", 4);
        trie.put("bat", 5);
        trie.put("ball", 6);
        System.out.println("Size after adding keys: " + trie.size()); // Ожидается: 6

        // Тест 2: Получение значений
        System.out.println("\n=== Test 2: Retrieving Values ===");
        System.out.println("Value for 'apple': " + trie.get("apple")); // Ожидается: 1
        System.out.println("Value for 'app': " + trie.get("app"));     // Ожидается: 2
        System.out.println("Value for 'banana': " + trie.get("banana"));// Ожидается: 3
        System.out.println("Value for 'band': " + trie.get("band"));   // Ожидается: 4
        System.out.println("Value for 'bat': " + trie.get("bat"));     // Ожидается: 5
        System.out.println("Value for 'ball': " + trie.get("ball"));   // Ожидается: 6
        System.out.println("Value for 'cat': " + trie.get("cat"));     // Ожидается: null

        // Тест 3: Удаление ключей
        System.out.println("\n=== Test 3: Deleting Keys ===");
        trie.delete("bat");
        trie.delete("banana");
        System.out.println("Size after deleting 'bat' and 'banana': " + trie.size()); // Ожидается: 4
        System.out.println("Value for 'bat' after deletion: " + trie.get("bat"));     // Ожидается: null
        System.out.println("Value for 'banana' after deletion: " + trie.get("banana")); // Ожидается: null
        System.out.println("Value for 'ball': " + trie.get("ball"));   // Ожидается: 6 (проверяем, что другие ключи остались)

        // Тест 4: Добавление существующего ключа с новым значением
        System.out.println("\n=== Test 4: Updating Existing Keys ===");
        trie.put("app", 10);
        System.out.println("Updated value for 'app': " + trie.get("app")); // Ожидается: 10
        System.out.println("Size after updating 'app': " + trie.size());  // Ожидается: 4 (размер не должен измениться)

        // Тест 5: Добавление новых ключей после удаления
        System.out.println("\n=== Test 5: Adding New Keys After Deletion ===");
        trie.put("cat", 7);
        trie.put("dog", 8);
        System.out.println("Size after adding 'cat' and 'dog': " + trie.size()); // Ожидается: 6
        System.out.println("Value for 'cat': " + trie.get("cat"));       // Ожидается: 7
        System.out.println("Value for 'dog': " + trie.get("dog"));       // Ожидается: 8

        // Тест 6: Пустая структура
        System.out.println("\n=== Test 6: Clearing All Keys ===");
        trie.delete("apple");
        trie.delete("app");
        trie.delete("band");
        trie.delete("ball");
        trie.delete("cat");
        trie.delete("dog");
        System.out.println("Size after deleting all keys: " + trie.size()); // Ожидается: 0
        System.out.println("Value for 'apple': " + trie.get("apple"));     // Ожидается: null
    }
}
