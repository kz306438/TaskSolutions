package org.extendedtst;

import java.util.LinkedList;
import java.util.Queue;

public class TST<Value> {
    private Node<Value> root;

    private static class Node<Value> {
        char c;
        Node<Value> left, mid, right;
        Value value;
    }

    /**
     * @brief Inserts a key-value pair into the TST.
     * @param key The key to insert.
     * @param value The value associated with the key.
     */
    public void put(String key, Value value) {
        if (key == null || key.isEmpty()) throw new IllegalArgumentException("Key must not be null or empty.");
        root = put(root, key, value, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, value, d);
        else if (c > x.c) x.right = put(x.right, key, value, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, value, d + 1);
        else x.value = value;
        return x;
    }

    /**
     * @brief Returns all keys in the TST.
     * @return Iterable collection of all keys.
     */
    public Iterable<String> keys() {
        Queue<String> queue = new LinkedList<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left, prefix, queue);
        if (x.value != null) queue.add(prefix.toString() + x.c);
        collect(x.mid, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    /**
     * @brief Returns the longest prefix of the given query present in the TST.
     * @param query The query string.
     * @return The longest prefix in the TST.
     */
    public String longestPrefixOf(String query) {
        if (query == null || query.isEmpty()) return null;
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.value != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    /**
     * @brief Returns all keys in the TST that start with the given prefix.
     * @param prefix The prefix string.
     * @return Iterable collection of keys with the given prefix.
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedList<>();
        if (prefix == null) throw new IllegalArgumentException("Prefix must not be null.");
        if (prefix.isEmpty()) {
            collect(root, new StringBuilder(), queue);
            return queue;
        }
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.value != null) queue.add(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    public Value get(String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.value;
    }


    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d + 1);
        else return x;
    }

    /**
     * @brief Returns all keys in the TST that match the given pattern.
     * @param pattern The pattern with '.' as a wildcard.
     * @return Iterable collection of keys that match the pattern.
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedList<>();
        if (pattern == null) throw new IllegalArgumentException("Pattern must not be null.");
        if (pattern.isEmpty()) return queue;
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    private void collect(Node<Value> x, StringBuilder prefix, int d, String pattern, Queue<String> queue) {
        if (x == null) return;

        char nextChar = pattern.charAt(d);

        if (nextChar == '.' || nextChar < x.c) collect(x.left, prefix, d, pattern, queue);
        if (nextChar == '.' || nextChar == x.c) {
            if (d == pattern.length() - 1 && x.value != null) {
                queue.add(prefix.toString() + x.c);
            }
            if (d < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), d + 1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1); // Restore state
            }
        }
        if (nextChar == '.' || nextChar > x.c) collect(x.right, prefix, d, pattern, queue);
    }
}
