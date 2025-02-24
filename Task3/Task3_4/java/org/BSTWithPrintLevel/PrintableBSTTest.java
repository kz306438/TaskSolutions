package org.BSTWithPrintLevel;

public class PrintableBSTTest {
    public static void main(String[] args) {
        PrintableBST<Integer> bst = new PrintableBST<>();

        // Insert nodes
        System.out.println("Inserting nodes into the Binary Search Tree...");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Print level-order traversal of the BST
        System.out.println("\nLevel-order traversal after insertion:");
        bst.printLevel();
        // Expected output: 50 30 70 20 40 60 80

        // Test deletion of a leaf node
        System.out.println("\n\nDeleting a leaf node (20)...");
        bst.delete(20);
        System.out.println("Level-order traversal after deleting 20:");
        bst.printLevel();
        // Expected output: 50 30 70 40 60 80

        // Test deletion of a node with one child
        System.out.println("\n\nDeleting a node with one child (30)...");
        bst.delete(30);
        System.out.println("Level-order traversal after deleting 30:");
        bst.printLevel();
        // Expected output: 50 40 70 60 80

        // Test deletion of a node with two children
        System.out.println("\n\nDeleting a node with two children (50)...");
        bst.delete(50);
        System.out.println("Level-order traversal after deleting 50:");
        bst.printLevel();
        // Expected output: 60 40 70 80
    }
}
