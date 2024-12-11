package org.cachedRedBlackBST;

public class RedBlackTreeClient {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        System.out.println("\n--- Testing Insertion ---");
        int[] valuesToInsert = {20, 15, 15, 10, 18, 22, 30, 5};
        for (int value : valuesToInsert) {
            System.out.println("Inserting: " + value);
            tree.insert(value);
        }

        System.out.println("\n--- Testing Find ---");
        int[] valuesToFind = {10, 22, 22, 100};
        for (int value : valuesToFind) {
            boolean found = tree.find(value);
            System.out.println("Finding " + value + ": " + (found ? "Found" : "Not Found"));
        }

        System.out.println("\n--- Testing Delete ---");
        int[] valuesToDelete = {10, 22, 30};
        for (int value : valuesToDelete) {
            System.out.println("Deleting: " + value);
            tree.delete(value);
            boolean found = tree.find(value);
            System.out.println("Finding after delete " + value + ": " + (found ? "Still in tree" : "Successfully deleted"));
        }

        System.out.println("\n--- Testing Last Accessed Node Optimization ---");
        int optimizedKey = 18;
        System.out.println("Finding key: " + optimizedKey);
        tree.find(optimizedKey); // Access the node first.
        System.out.println("Inserting key again without traversal: " + optimizedKey);
        tree.insert(optimizedKey); // Should use the last accessed node.

        System.out.println("\n--- Test Complete ---");
    }
}
