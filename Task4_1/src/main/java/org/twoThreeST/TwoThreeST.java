package org.twoThreeST;

/**
 * A symbol table implementation using a 2-3 tree.
 * The 2-3 tree is a balanced search tree where each node can hold 1 or 2 keys and 2 or 3 children.
 * This class supports basic symbol table operations such as put, get, delete, and finding the min and max keys.
 *
 * @param <Key> The type of the keys in the symbol table, must extend Comparable.
 * @param <Value> The type of the values associated with the keys.
 */
public class TwoThreeST<Key extends Comparable<Key>, Value> {

    private Node root;

    private abstract class Node {
        Key[] keys;
        Value[] values;
        int n; // Number of keys in the node
    }

    private class TwoNode extends Node {
        Node left, right;

        /**
         * @brief Constructs a new TwoNode with the specified key and value.
         * @param key The key to store in the node.
         * @param value The value associated with the key.
         */
        @SuppressWarnings("unchecked")
        TwoNode(Key key, Value value) {
            this.keys = (Key[]) new Comparable[1];
            this.values = (Value[]) new Object[1];
            this.keys[0] = key;
            this.values[0] = value;
            this.n = 1;
        }
    }

    private class ThreeNode extends Node {
        Node left, middle, right;

        /**
         * @brief Constructs a new ThreeNode with the specified keys and values.
         * @param key1 The first key.
         * @param value1 The first value associated with the first key.
         * @param key2 The second key.
         * @param value2 The second value associated with the second key.
         */
        @SuppressWarnings("unchecked")
        ThreeNode(Key key1, Value value1, Key key2, Value value2) {
            this.keys = (Key[]) new Comparable[2];
            this.values = (Value[]) new Object[2];
            this.keys[0] = key1;
            this.values[0] = value1;
            this.keys[1] = key2;
            this.values[1] = value2;
            this.n = 2;
        }
    }

    /**
     * @brief Returns the value associated with the specified key, or null if the key is not found.
     * @param key The key to search for.
     * @return The value associated with the key, or null if the key is not found.
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        if (node instanceof TwoNode twoNode) {
            int cmp = key.compareTo(twoNode.keys[0]);
            if (cmp == 0) return twoNode.values[0];
            else if (cmp < 0) return get(twoNode.left, key);
            else return get(twoNode.right, key);
        } else {
            ThreeNode threeNode = (ThreeNode) node;
            int cmp1 = key.compareTo(threeNode.keys[0]);
            int cmp2 = key.compareTo(threeNode.keys[1]);
            if (cmp1 == 0) return threeNode.values[0];
            else if (cmp2 == 0) return threeNode.values[1];
            else if (cmp1 < 0) return get(threeNode.left, key);
            else if (cmp2 < 0) return get(threeNode.middle, key);
            else return get(threeNode.right, key);
        }
    }

    /**
     * @brief Inserts a key-value pair into the tree.
     * If the tree contains the key, its value is updated.
     * @param key The key to insert.
     * @param value The value associated with the key.
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
        if (root instanceof ThreeNode oldRoot) {
            // Split root if necessary
            Key middleKey = oldRoot.keys[0];
            Value middleValue = oldRoot.values[0];

            TwoNode newRoot = new TwoNode(middleKey, middleValue);
            newRoot.left = new TwoNode(oldRoot.keys[0], oldRoot.values[0]);
            newRoot.right = new TwoNode(oldRoot.keys[1], oldRoot.values[1]);
            root = newRoot;
        }
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new TwoNode(key, value);

        if (node instanceof TwoNode twoNode) {
            int cmp = key.compareTo(twoNode.keys[0]);
            if (cmp == 0) {
                twoNode.values[0] = value;
            } else if (cmp < 0) {
                twoNode.left = put(twoNode.left, key, value);
            } else {
                twoNode.right = put(twoNode.right, key, value);
            }
            return splitIfNeeded(twoNode);
        } else {
            ThreeNode threeNode = (ThreeNode) node;
            int cmp1 = key.compareTo(threeNode.keys[0]);
            int cmp2 = key.compareTo(threeNode.keys[1]);

            if (cmp1 == 0) {
                threeNode.values[0] = value;
            } else if (cmp2 == 0) {
                threeNode.values[1] = value;
            } else if (cmp1 < 0) {
                threeNode.left = put(threeNode.left, key, value);
            } else if (cmp2 < 0) {
                threeNode.middle = put(threeNode.middle, key, value);
            } else {
                threeNode.right = put(threeNode.right, key, value);
            }
            return splitIfNeeded(threeNode);
        }
    }

    private Node splitIfNeeded(TwoNode node) {
        if (node.n == 1) return node;

        // If the node contains two keys, it becomes a ThreeNode
        Key newKey = node.keys[0];
        Value newValue = node.values[0];
        Key secondKey = node.keys[1];
        Value secondValue = node.values[1];

        // Create a new ThreeNode
        ThreeNode threeNode = new ThreeNode(newKey, newValue, secondKey, secondValue);
        threeNode.left = node.left;
        threeNode.middle = node.right;

        return threeNode;
    }

    private Node splitIfNeeded(ThreeNode node) {
        // If the ThreeNode is full, split it into two parts
        Key middleKey = node.keys[1];
        Value middleValue = node.values[1];

        // Create a new TwoNode with the middle key and value
        TwoNode newRoot = new TwoNode(middleKey, middleValue);

        // Move the keys and values to the appropriate TwoNodes
        newRoot.left = new TwoNode(node.keys[0], node.values[0]);
        newRoot.right = new TwoNode(node.keys[2], node.values[2]);

        return newRoot;
    }

    /**
     * @brief Deletes the key-value pair associated with the specified key.
     * If the key is not found, no changes are made.
     * @param key The key to delete.
     */
    public void delete(Key key) {
        root = delete(root, key);
        if (root instanceof TwoNode && ((TwoNode) root).left == null && ((TwoNode) root).right == null) {
            root = null;
        }
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        if (node instanceof TwoNode) {
            return handleTwoNodeDelete((TwoNode) node, key);
        } else {
            return handleThreeNodeDelete((ThreeNode) node, key);
        }
    }

    private TwoNode handleTwoNodeDelete(TwoNode node, Key key) {
        int cmp = key.compareTo(node.keys[0]);

        if (cmp == 0) {
            if (node.left == null && node.right == null) {
                return null; // Leaf node, delete directly
            }
            if (node.right != null) {
                replaceWithMin(node, 0, node.right);
                node.right = delete(node.right, node.keys[0]);
            } else {
                replaceWithMax(node, 0, node.left);
                node.left = delete(node.left, node.keys[0]);
            }
        } else if (cmp < 0) {
            node.left = delete(node.left, key);
        } else {
            node.right = delete(node.right, key);
        }
        return node;
    }

    private ThreeNode handleThreeNodeDelete(ThreeNode node, Key key) {
        int cmp1 = key.compareTo(node.keys[0]);
        int cmp2 = key.compareTo(node.keys[1]);

        if (cmp1 == 0 || cmp2 == 0) {
            int pos = (cmp1 == 0) ? 0 : 1;
            if (pos == 0) {
                replaceWithMax(node, pos, node.left);
                node.left = delete(node.left, node.keys[pos]);
            } else {
                replaceWithMin(node, pos, node.right);
                node.right = delete(node.right, node.keys[pos]);
            }
        } else {
            if (cmp1 < 0) {
                node.left = delete(node.left, key);
            } else if (cmp2 < 0) {
                node.middle = delete(node.middle, key);
            } else {
                node.right = delete(node.right, key);
            }
        }
        return node;
    }

    private void replaceWithMin(Node node, int pos, Node subtree) {
        Key minKey = findMin(subtree);
        Value minValue = get(subtree, minKey);
        node.keys[pos] = minKey;
        node.values[pos] = minValue;
    }

    private void replaceWithMax(Node node, int pos, Node subtree) {
        Key maxKey = findMax(subtree);
        Value maxValue = get(subtree, maxKey);
        node.keys[pos] = maxKey;
        node.values[pos] = maxValue;
    }


    /**
     * @brief Returns the minimum key in the tree.
     * @return The minimum key, or null if the tree is empty.
     */
    public Key min() {
        if (root == null) return null;
        return findMin(root);
    }

    /**
     * @brief Returns the maximum key in the tree.
     * @return The maximum key, or null if the tree is empty.
     */
    public Key max() {
        if (root == null) return null;
        return findMax(root);
    }

    private Key findMax(Node node) {
        if (node instanceof TwoNode twoNode) {
            return twoNode.keys[0];
        }
        ThreeNode threeNode = (ThreeNode) node;
        return threeNode.keys[1];
    }

    private Key findMin(Node node) {
        if (node instanceof TwoNode twoNode) {
            return twoNode.keys[0];
        }
        ThreeNode threeNode = (ThreeNode) node;
        return threeNode.keys[0];
    }
}
