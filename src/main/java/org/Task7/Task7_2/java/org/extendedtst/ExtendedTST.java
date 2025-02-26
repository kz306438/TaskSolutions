package org.Task7.Task7_2.java.org.extendedtst;

import org.Task7.TST;
import org.Task7.Node;
import java.util.LinkedList;
import java.util.Queue;

public class ExtendedTST<Value> extends TST<Value> {
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
        collect(x.getLeft(), prefix, queue);
        if (x.getValue() != null) queue.add(prefix.toString() + x.getC());
        collect(x.getMid(), prefix.append(x.getC()), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.getRight(), prefix, queue);
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
            if (c < x.getC()) x = x.getLeft();
            else if (c > x.getC()) x = x.getRight();
            else {
                i++;
                if (x.getValue() != null) length = i;
                x = x.getMid();
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
        Node<Value> x = get(prefix, root, 0);
        if (x == null) return queue;
        if (x.getValue() != null) queue.add(prefix);
        collect(x.getMid(), new StringBuilder(prefix), queue);
        return queue;
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

        if (nextChar == '.' || nextChar < x.getC())
            collect(x.getLeft(), prefix, d, pattern, queue);

        if (nextChar == '.' || nextChar == x.getC()) {
            if (d == pattern.length() - 1 && x.getValue() != null) {
                queue.add(prefix.toString() + x.getC());
            }
            if (d < pattern.length() - 1) {
                collect(x.getMid(), prefix.append(x.getC()), d + 1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1); // Restore state
            }
        }

        if (nextChar == '.' || nextChar > x.getC())
            collect(x.getRight(), prefix, d, pattern, queue);
    }
}
