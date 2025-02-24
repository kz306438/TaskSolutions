package org.hashtable;

import java.util.ArrayList;

/**
 * Implementation of a hash table using open addressing with linear probing.
 * @param <Key> The type of the key
 * @param <Value> The type of the value
 */
public class LinearProbingHashTable<Key, Value> extends AbstractHashTable<Key, Value> {
    private static final Object DELETED = new Object();  // Object representing a deleted entry
    private Object[] keys;  // Array for storing keys
    private Object[] values;  // Array for storing values

    /**
     * Constructor to initialize the hash table with linear probing for open addressing.
     * @param initialCapacity The initial capacity of the hash table
     */
    public LinearProbingHashTable(int initialCapacity) {
        super(initialCapacity);
        keys = new Object[capacity];
        values = new Object[capacity];
    }

    /**
     * Hash function to compute the index in the table.
     * Uses a modification of the hash code to get an index.
     * @param key The key to be hashed
     * @return The index in the hash table
     */
    @Override
    protected int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /**
     * Adds a key-value pair to the hash table.
     * If the key already exists, its value is updated.
     * In case of collision, linear probing is used.
     * @param key The key
     * @param value The value
     */
    @Override
    public void put(Key key, Value value) {
        int index = hash(key);

        // Linear probing to find the next available index
        while (keys[index] != null && keys[index] != DELETED && !keys[index].equals(key)) {
            index = (index + 1) % capacity;
        }

        if (keys[index] == null || keys[index] == DELETED) {
            size++;
        }

        keys[index] = key;
        values[index] = value;
    }

    /**
     * Retrieves the value associated with the given key.
     * In case of a collision, linear probing is used.
     * @param key The key
     * @return The value associated with the key, or null if the key is not found
     */
    @Override
    public Value get(Key key) {
        int index = hash(key);

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return (Value) values[index];
            }
            index = (index + 1) % capacity;
        }

        return null;  // If the key is not found
    }

    /**
     * Deletes the key-value pair associated with the given key.
     * In case of a collision, linear probing is used.
     * @param key The key
     */
    @Override
    public void delete(Key key) {
        int index = hash(key);

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                keys[index] = DELETED;
                values[index] = null;
                size--;
                return;
            }
            index = (index + 1) % capacity;
        }
    }

    /**
     * Returns all keys present in the hash table.
     * The keys are collected from all non-null and non-DELETED entries.
     * @return An iterable collection of keys
     */
    @Override
    public Iterable<Key> keys() {
        ArrayList<Key> allKeys = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != DELETED) {
                allKeys.add((Key) keys[i]);
            }
        }
        return allKeys;
    }
}
