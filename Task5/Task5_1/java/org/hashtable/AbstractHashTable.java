package org.hashtable;

/**
 * Abstract class for implementing hash tables.
 * This class provides common methods for working with a hash table, including basic operations
 * like insertion, retrieval, deletion of elements, and getting all keys.
 * Concrete implementations should provide the hashing method and collision resolution
 * strategies.
 *
 * @param <Key>   the type of keys in the hash table
 * @param <Value> the type of values in the hash table
 */
public abstract class AbstractHashTable<Key, Value> {

    /** The current size of the hash table (number of elements). */
    protected int size;

    /** The capacity of the hash table. */
    protected int capacity;

    /**
     * Constructor to initialize the hash table with a specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the hash table
     */
    public AbstractHashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
    }

    /**
     * Abstract method for computing the hash code for a key.
     * The concrete implementation should provide a way to hash the key.
     *
     * @param key the key to hash
     * @return the array index corresponding to the key's hash code
     */
    protected abstract int hash(Key key);

    /**
     * Inserts or updates a key-value pair in the hash table.
     * If the key already exists, its value is updated.
     *
     * @param key   the key to insert or update
     * @param value the value associated with the key
     */
    public abstract void put(Key key, Value value);

    /**
     * Retrieves the value associated with a specified key.
     * Returns {@code null} if the key is not found.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or {@code null} if the key is not found
     */
    public abstract Value get(Key key);

    /**
     * Deletes a key-value pair from the hash table by its key.
     * If the key is not found, nothing happens.
     *
     * @param key the key of the pair to be deleted
     */
    public abstract void delete(Key key);

    /**
     * Returns an iterable collection of all keys in the hash table.
     *
     * @return an iterable object containing all keys in the hash table
     */
    public abstract Iterable<Key> keys();

    /**
     * Returns the current number of elements in the hash table.
     *
     * @return the number of elements in the hash table
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the hash table is empty.
     *
     * @return {@code true} if the hash table is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
