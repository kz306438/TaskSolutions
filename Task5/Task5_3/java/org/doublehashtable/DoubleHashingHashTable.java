package org.doublehashtable;

import java.util.Arrays;

/// @brief Implementation of a hash table using double hashing for collision resolution.
/// This hash table supports generic keys and values and resolves collisions using
/// a second hash function. It maintains a fixed capacity and does not dynamically resize.
///
/// @param <K> The type of keys maintained by this hash table.
/// @param <V> The type of values associated with the keys.
public class DoubleHashingHashTable<K, V> {
    private static final int INIT_CAPACITY = 11;
    private final K[] keys;
    private final V[] values;
    private int size;
    private final int M;

    /**
     * @brief Constructs a hash table with the specified initial capacity.
     * @param capacity The initial capacity of the hash table.
     */
    @SuppressWarnings(value = "unchecked")
    public DoubleHashingHashTable(int capacity) {
        this.M = capacity;
        keys = (K[]) new Object[M];
        values = (V[]) new Object[M];
    }

    /**
     * @brief Constructs a hash table with the default initial capacity.
     */
    public DoubleHashingHashTable() {
        this(INIT_CAPACITY);
    }

    /**
     * @brief Computes the primary hash for the given key.
     * @param key The key to hash.
     * @return The primary hash value.
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * @brief Computes the secondary hash for the given key.
     * @param key The key to hash.
     * @return The secondary hash value.
     */
    private int secondHash(K key) {
        int Q = findPrimeLessThan(M);
        return Q - (hash(key) % Q);
    }

    /**
     * @brief Finds the largest prime number less than the given number.
     * @param num The upper limit for the prime number search.
     * @return The largest prime number less than the given number.
     */
    private int findPrimeLessThan(int num) {
        for (int i = num - 1; i >= 2; i--) {
            if (isPrime(i)) return i;
        }
        return 2;
    }

    /**
     * @brief Checks if a number is prime.
     * @param num The number to check.
     * @return True if the number is prime, otherwise false.
     */
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * @brief Inserts a key-value pair into the hash table.
     *        If the key already exists, updates its value.
     * @param key The key to insert or update.
     * @param value The value to associate with the key.
     * @throws IllegalStateException If the hash table is full.
     */
    public void put(K key, V value) {
        int hash1 = hash(key);
        int hash2 = secondHash(key);
        int i = hash1;
        int probes = 0;

        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
            i = (i + hash2) % M;
            probes++;
            if (probes >= M) throw new IllegalStateException("Hash table overflow");
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    /**
     * @brief Retrieves the value associated with the given key.
     * @param key The key to look up.
     * @return The value associated with the key, or null if the key does not exist.
     */
    public V get(K key) {
        int hash1 = hash(key);
        int hash2 = secondHash(key);
        int i = hash1;
        int probes = 0;

        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return values[i];
            }
            i = (i + hash2) % M;
            probes++;
            if (probes >= M) break;
        }
        return null;
    }

    /**
     * @brief Traces the insertion process for a string of characters.
     *        Each character is inserted as a key, with its associated value being a string representation.
     * @param input The string of characters to insert.
     */
    @SuppressWarnings("unchecked")
    public void traceInsert(String input) {
        System.out.println("Tracing insertions:");
        for (char c : input.toCharArray()) {
            put((K) Character.valueOf(c), (V) ("Value_" + c));
            System.out.println(Arrays.toString(keys));
        }
    }

    /**
     * @brief Main method for testing the hash table with double hashing.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        DoubleHashingHashTable<Character, String> table = new DoubleHashingHashTable<>(11);
        String keys = "EASYQUTIONB";
        table.traceInsert(keys);
    }
}
