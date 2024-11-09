package org.twoThreeST;

public class TwoThreeSTTestClient {
    public static void main(String[] args) {
        TwoThreeST<Integer, String> tree = new TwoThreeST<>();

        System.out.println("Testing insertion...");
        tree.put(10, "Ten");
        tree.put(20, "Twenty");
        tree.put(5, "Five");
        tree.put(15, "Fifteen");
        tree.put(25, "Twenty-Five");
        tree.put(30, "Thirty");

        System.out.println("\nTesting get operation...");
        testGet(tree, 10);  // Expected: "Get key 10: Ten"
        testGet(tree, 15);  // Expected: "Get key 15: Fifteen"
        testGet(tree, 100); // Expected: "Get key 100: Not found"

        System.out.println("\nTesting min and max operations...");
        testMinMax(tree);  // Expected min: 5, max: 30

        System.out.println("\nTesting deletion...");
        testDelete(tree, 5);   // Expected min: 10, max: 30, "Get after delete for key 5: Not found"
        testDelete(tree, 10);  // Expected min: 15, max: 30, "Get after delete for key 10: Not found"
        testDelete(tree, 30);  // Expected min: 15, max: 25, "Get after delete for key 30: Not found"

        System.out.println("\nTesting tree after multiple deletions...");
        tree.put(40, "Forty");
        tree.put(35, "Thirty-Five");
        testGet(tree, 20); // Expected: "Get key 20: Twenty"
        testGet(tree, 25); // Expected: "Get key 25: Twenty-Five"
        testGet(tree, 35); // Expected: "Get key 35: Thirty-Five"
        testGet(tree, 40); // Expected: "Get key 40: Forty"

        System.out.println("\nTesting edge cases...");
        testEdgeCases();  // See specific comments below for expected output
    }

    private static void testGet(TwoThreeST<Integer, String> tree, Integer key) {
        String value = tree.get(key);
        System.out.println("Get key " + key + ": " + (value != null ? value : "Not found"));
    }

    private static void testMinMax(TwoThreeST<Integer, String> tree) {
        Integer minKey = tree.min();
        Integer maxKey = tree.max();
        System.out.println("Minimum key: " + (minKey != null ? minKey : "Tree is empty"));
        System.out.println("Maximum key: " + (maxKey != null ? maxKey : "Tree is empty"));
    }

    private static void testDelete(TwoThreeST<Integer, String> tree, Integer key) {
        System.out.println("Deleting key " + key + "...");
        tree.delete(key);
        System.out.println("Get after delete for key " + key + ": " + (tree.get(key) != null ? tree.get(key) : "Not found"));
        testMinMax(tree);
    }

    private static void testEdgeCases() {
        TwoThreeST<Integer, String> edgeTree = new TwoThreeST<>();

        // Testing get, min, and max on an empty tree
        System.out.println("Testing operations on an empty tree...");
        System.out.println("Get (empty tree): " + edgeTree.get(1));               // Expected: "Get (empty tree): null"
        System.out.println("Min (empty tree): " + edgeTree.min());               // Expected: "Min (empty tree): null"
        System.out.println("Max (empty tree): " + edgeTree.max());               // Expected: "Max (empty tree): null"

        // Insert and delete on single-node tree
        edgeTree.put(50, "Fifty");
        System.out.println("Added single key 50.");
        System.out.println("Get single key 50: " + edgeTree.get(50));            // Expected: "Get single key 50: Fifty"
        System.out.println("Min (single-node tree): " + edgeTree.min());         // Expected: "Min (single-node tree): 50"
        System.out.println("Max (single-node tree): " + edgeTree.max());         // Expected: "Max (single-node tree): 50"

        edgeTree.delete(50);
        System.out.println("Deleted key 50.");
        System.out.println("Get (after deletion): " + edgeTree.get(50));         // Expected: "Get (after deletion): Not found"
        System.out.println("Min (after deletion): " + edgeTree.min());           // Expected: "Min (after deletion): null"
        System.out.println("Max (after deletion): " + edgeTree.max());           // Expected: "Max (after deletion): null"
    }
}
