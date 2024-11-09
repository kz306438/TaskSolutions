package org.cachedRedBlackBST;

public class RedBlackBSTClient {
    public static void main(String[] args) {
        // Create a RedBlackBST instance with Integer keys and String values
        RedBlackBST<Integer, String> bst = new RedBlackBST<>();

        // Test putting key-value pairs into the RedBlackBST
        bst.put(1, "One");
        bst.put(2, "Two");
        bst.put(3, "Three");

        // Test getting values for the inserted keys
        System.out.println("Key: 1, Value: " + bst.get(1)); // Expected: One
        System.out.println("Key: 2, Value: " + bst.get(2)); // Expected: Two
        System.out.println("Key: 3, Value: " + bst.get(3)); // Expected: Three

        // Test updating the value of an existing key
        bst.put(2, "Updated Two");
        System.out.println("Key: 2, Updated Value: " + bst.get(2)); // Expected: Updated Two

        // Test getting a key that doesn't exist
        System.out.println("Key: 4, Value: " + bst.get(4)); // Expected: null

        // Add more test cases as needed
        bst.put(4, "Four");
        bst.put(5, "Five");

        System.out.println("Key: 4, Value: " + bst.get(4)); // Expected: Four
        System.out.println("Key: 5, Value: " + bst.get(5)); // Expected: Five
    }
}
