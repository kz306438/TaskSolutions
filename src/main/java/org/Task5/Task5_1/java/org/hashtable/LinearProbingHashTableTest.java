package org.Task5.Task5_1.java.org.hashtable;

public class LinearProbingHashTableTest {
    public static void main(String[] args) {
        // Create a hash table with initial capacity of 5
        LinearProbingHashTable<String, Integer> hashTable = new LinearProbingHashTable<>(5);

        // Add key-value pairs to the hash table
        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        hashTable.put("cherry", 3);

        // Retrieve the value for the key 'banana'
        System.out.println("Value for key 'banana': " + hashTable.get("banana"));

        // Print all keys in the hash table
        System.out.println("All keys in the hash table:");
        for (String key : hashTable.keys()) {
            System.out.println(key);
        }

        // Delete the key 'banana'
        hashTable.delete("banana");

        // Print all keys after deletion
        System.out.println("After deleting 'banana':");
        for (String key : hashTable.keys()) {
            System.out.println(key);
        }
    }
}
