package org.Task5.Task5_4.java.org.cuckoohashtable;

public class CuckooHashTableTest {
    public static void main(String[] args) {
        // Initialize the cuckoo hash table
        CuckooHashTable<String, Integer> hashTable = new CuckooHashTable<>();

        // Test insertion
        System.out.println("Adding key-value pairs...");
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        hashTable.put("four", 4);

        System.out.println("Size after insertion: " + hashTable.size()); // Expected: 4

        // Test retrieval
        System.out.println("\nRetrieving values...");
        System.out.println("Key 'one': " + hashTable.get("one"));   // Expected: 1
        System.out.println("Key 'two': " + hashTable.get("two"));   // Expected: 2
        System.out.println("Key 'three': " + hashTable.get("three")); // Expected: 3
        System.out.println("Key 'four': " + hashTable.get("four")); // Expected: 4
        System.out.println("Key 'five': " + hashTable.get("five")); // Expected: null

        // Test removal
        System.out.println("\nRemoving key 'three'...");
        hashTable.remove("three");
        System.out.println("Key 'three': " + hashTable.get("three")); // Expected: null
        System.out.println("Size after removal: " + hashTable.size()); // Expected: 3

        // Test resizing and collision handling
        System.out.println("\nAdding more key-value pairs to trigger resizing...");
        for (int i = 5; i <= 20; i++) {
            hashTable.put("key" + i, i);
        }
        System.out.println("Size after resizing: " + hashTable.size()); // Expected: 19

        // Verify all inserted keys are present
        System.out.println("\nVerifying values...");
        for (int i = 5; i <= 20; i++) {
            System.out.println("Key 'key" + i + "': " + hashTable.get("key" + i)); // Expected: i
        }

        // Test edge cases
        System.out.println("\nTesting edge cases...");
        System.out.println("Key 'nonexistent': " + hashTable.get("nonexistent")); // Expected: null
        System.out.println("Removing 'nonexistent'...");
        hashTable.remove("nonexistent");
        System.out.println("Size after removing nonexistent key: " + hashTable.size()); // Expected: 19

        // Test isEmpty method
        System.out.println("\nIs the table empty? " + hashTable.isEmpty()); // Expected: false

        System.out.println("\nAll tests completed.");
    }
}
