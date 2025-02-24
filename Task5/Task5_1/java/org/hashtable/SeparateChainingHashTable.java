package org.hashtable;

import java.util.ArrayList;

/**
 * Hash table implementation using separate chaining to handle collisions.
 * This hash table supports generic key-value pairs and offers standard operations
 * like insertion, deletion, retrieval, and a method to retrieve all keys.
 *
 * @param <Key>   the type of keys in this hash table
 * @param <Value> the type of values in this hash table
 */
public class SeparateChainingHashTable<Key, Value> extends AbstractHashTable<Key, Value> {

    /** Array of chains where each chain is a list of nodes containing key-value pairs. */
    private ArrayList<Node<Key, Value>>[] table;

    /**
     * Node class representing an individual key-value pair within a chain.
     *
     * @param <Key>   the type of the key
     * @param <Value> the type of the value
     */
    private static class Node<Key, Value> {
        Key key;
        Value value;

        /**
         * Constructs a new Node with the specified key and value.
         *
         * @param key   the key of the node
         * @param value the value associated with the key
         */
        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Initializes a hash table with a specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the hash table
     */
    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int initialCapacity) {
        super(initialCapacity);
        table = (ArrayList<Node<Key, Value>>[]) new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new ArrayList<>();
        }
    }

    /**
     * Computes the index for a given key by hashing it and mapping it to a valid array index.
     *
     * @param key the key to hash
     * @return the array index corresponding to the key's hash code
     */
    @Override
    protected int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /**
     * Inserts a key-value pair into the hash table. If the key already exists,
     * updates its value with the specified new value.
     *
     * @param key   the key to insert or update
     * @param value the value associated with the key
     */
    @Override
    public void put(Key key, Value value) {
        int index = hash(key);
        ArrayList<Node<Key, Value>> chain = table[index];

        for (Node<Key, Value> node : chain) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        chain.add(new Node<>(key, value));
        size++;
    }

    /**
     * Retrieves the value associated with a specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or {@code null} if the key is not found
     */
    @Override
    public Value get(Key key) {
        int index = hash(key);
        ArrayList<Node<Key, Value>> chain = table[index];

        for (Node<Key, Value> node : chain) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    /**
     * Deletes a key-value pair from the hash table by its key.
     *
     * @param key the key of the pair to be deleted
     */
    @Override
    public void delete(Key key) {
        int index = hash(key);
        ArrayList<Node<Key, Value>> chain = table[index];

        for (Node<Key, Value> node : chain) {
            if (node.key.equals(key)) {
                chain.remove(node);
                size--;
                return;
            }
        }
    }

    /**
     * Returns an iterable collection of all keys in the hash table.
     *
     * @return an iterable of all keys
     */
    @Override
    public Iterable<Key> keys() {
        ArrayList<Key> allKeys = new ArrayList<>();
        for (ArrayList<Node<Key, Value>> chain : table) {
            for (Node<Key, Value> node : chain) {
                allKeys.add(node.key);
            }
        }
        return allKeys;
    }
}
