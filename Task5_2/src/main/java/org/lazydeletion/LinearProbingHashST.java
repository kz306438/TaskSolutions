package org.lazydeletion;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a symbol table with linear probing for hash collision resolution.
 * Supports lazy deletion of entries and automatically resizes the table when necessary.
 *
 * @param <Key>   the type of keys maintained by this symbol table
 * @param <Value> the type of mapped values
 */
public class LinearProbingHashST<Key, Value> {
    private int n;           // number of key-value pairs in the table
    private int m = 16;      // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] values;  // the values
    private int nullCount;   // count of lazy-deleted entries

    /**
     * Initializes an empty symbol table with the default initial capacity.
     */
    public LinearProbingHashST() {
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return the number of key-value pairs
     */
    public int size() {
        return n;
    }

    /**
     * Returns an iterable collection of keys in the table.
     *
     * @return an iterable collection of keys
     */
    public Iterable<Key> keys() {
        List<Key> keyList = new ArrayList<>();
        for (Key key : keys) {
            if (key != null) {
                keyList.add(key);
            }
        }
        return keyList;
    }

    /**
     * Inserts the specified key-value pair into the table. If the key already exists, its
     * associated value is updated. If the specified value is null, the key is lazily deleted.
     *
     * @param key   the key to insert
     * @param value the value to associate with the key
     * @throws IllegalArgumentException if the key is null
     */
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        if (value == null) {
            delete(key);
            return;
        }

        if (n >= m / 2) resize(2 * m);  // double table size if 50% full

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                if (values[i] == null) nullCount--;
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    /**
     * Returns the value associated with the specified key, or null if the key is not present.
     *
     * @param key the key to search for
     * @return the value associated with the key, or null if the key is not present
     * @throws IllegalArgumentException if the key is null
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) return values[i];
        }
        return null;
    }

    /**
     * Lazily deletes the specified key from the table by setting its associated value to null.
     * If the number of key-value pairs drops below a threshold, the table is resized to a smaller capacity.
     *
     * @param key the key to delete
     * @throws IllegalArgumentException if the key is null
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                if (values[i] != null) {
                    values[i] = null;  // lazy deletion
                    nullCount++;
                }
                break;
            }
        }

        if (n > 0 && n <= m / 8) resize(m / 2);  // shrink table if necessary
    }

    /**
     * Computes the hash value for the specified key, which determines its position in the table.
     *
     * @param key the key to hash
     * @return the hash value of the key
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * Resizes the hash table to the specified capacity and rehashes all the entries.
     *
     * @param newSize the new size of the hash table
     */
    private void resize(int newSize) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>();
        temp.m = newSize;
        temp.keys = (Key[]) new Object[newSize];
        temp.values = (Value[]) new Object[newSize];

        for (int i = 0; i < m; i++) {
            if (keys[i] != null && values[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }

        keys = temp.keys;
        values = temp.values;
        m = temp.m;
        nullCount = 0;  // reset null count after resizing
    }
}
