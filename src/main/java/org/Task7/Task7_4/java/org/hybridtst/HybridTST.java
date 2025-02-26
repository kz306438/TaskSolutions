package org.Task7.Task7_4.java.org.hybridtst;

/**
 * @brief A hybrid Ternary Search Tree (TST) with R² branching at the root and second level.
 * This implementation supports efficient insertion and search of strings.
 */
public class HybridTST {

    /**
     * @brief Represents a single node in the hybrid TST.
     */
    private static class Node {
        char character;         ///< The character stored in this node.
        Node left, middle, right; ///< Pointers to the left, middle, and right children.
        Node[] children;        ///< Array for branching on the first two levels.
        boolean isEndOfWord;    ///< Indicates if this node marks the end of a word.

        /**
         * @brief Constructs a new node with the specified character.
         * @param character The character to store in this node.
         */
        Node(char character) {
            this.character = character;
            this.isEndOfWord = false;
        }
    }

    private final int R;      ///< The size of the alphabet (branching factor).
    private final Node root; ///< The root node of the tree.

    /**
     * @brief Constructs a new Hybrid TST.
     * @param alphabetSize The size of the alphabet (e.g., 26 for English letters).
     */
    public HybridTST(int alphabetSize) {
        this.R = alphabetSize;
        this.root = new Node('\0'); // Root node with a dummy character
        this.root.children = new Node[R];
    }

    /**
     * @brief Inserts a key into the tree.
     * @param key The string to insert.
     */
    public void put(String key) {
        if (key == null || key.isEmpty()) return;
        put(root, key, 0);
    }

    /**
     * @brief Recursively inserts a key into the tree.
     * @param node The current node.
     * @param key The string to insert.
     * @param depth The current depth in the tree.
     * @return The updated node.
     */
    private Node put(Node node, String key, int depth) {
        char c = key.charAt(depth);

        if (depth < 2) { // Handle first two levels
            if (node.children == null) {
                node.children = new Node[R]; // Initialize children array
            }
            int index = c - 'a'; // Assuming alphabet starts with 'a'
            if (node.children[index] == null) {
                node.children[index] = new Node(c);
                if (depth == 1) {
                    node.children[index].children = new Node[R]; // Initialize children for second level
                }
            }
            if (depth == key.length() - 1) {
                node.children[index].isEndOfWord = true;
            } else {
                put(node.children[index], key, depth + 1);
            }
            return node;
        } else { // Standard TST behavior
            if (node == null) {
                node = new Node(c);
            }
            if (c < node.character) {
                node.left = put(node.left, key, depth);
            } else if (c > node.character) {
                node.right = put(node.right, key, depth);
            } else {
                if (depth == key.length() - 1) {
                    node.isEndOfWord = true;
                } else {
                    node.middle = put(node.middle, key, depth + 1);
                }
            }
            return node;
        }
    }

    /**
     * @brief Checks if the tree contains the given key.
     * @param key The string to search for.
     * @return True if the key is found, false otherwise.
     */
    public boolean contains(String key) {
        if (key == null || key.isEmpty()) return false;
        return contains(root, key, 0);
    }

    /**
     * @brief Recursively checks if the tree contains the given key.
     * @param node The current node.
     * @param key The string to search for.
     * @param depth The current depth in the tree.
     * @return True if the key is found, false otherwise.
     */
    private boolean contains(Node node, String key, int depth) {
        char c = key.charAt(depth);

        if (depth < 2) { // Handle first two levels
            int index = c - 'a';
            if (node.children == null || node.children[index] == null) return false;
            if (depth == key.length() - 1) {
                return node.children[index].isEndOfWord;
            }
            return contains(node.children[index], key, depth + 1);
        } else { // Standard TST behavior
            if (node == null) return false;
            if (c < node.character) {
                return contains(node.left, key, depth);
            } else if (c > node.character) {
                return contains(node.right, key, depth);
            } else {
                if (depth == key.length() - 1) {
                    return node.isEndOfWord;
                }
                return contains(node.middle, key, depth + 1);
            }
        }
    }

    /**
     * @brief Main method for testing the HybridTST implementation.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        HybridTST tst = new HybridTST(26); // English alphabet
        tst.put("cat");
        tst.put("car");
        tst.put("bat");
        tst.put("bar");

        System.out.println(tst.contains("cat")); // true
        System.out.println(tst.contains("car")); // true
        System.out.println(tst.contains("bat")); // true
        System.out.println(tst.contains("bar")); // true
        System.out.println(tst.contains("cap")); // false
    }
}
