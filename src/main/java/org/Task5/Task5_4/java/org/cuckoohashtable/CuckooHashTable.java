package org.Task5.Task5_4.java.org.cuckoohashtable;

/// @brief A hash table implementation using the cuckoo hashing technique.
///
/// This implementation uses two hash functions and two tables to resolve collisions.
/// If both tables are full, the hash table resizes and rehashes the elements.
///
/// @param <K> the type of keys stored in this hash table.
/// @param <V> the type of values stored in this hash table.
public class CuckooHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.5;
    private K[] table1Keys, table2Keys;
    private V[] table1Values, table2Values;
    private int size;

    /**
     * @brief Constructs an empty CuckooHashTable with an initial capacity.
     */
    @SuppressWarnings("unchecked")
    public CuckooHashTable() {
        table1Keys = (K[]) new Object[INITIAL_CAPACITY];
        table2Keys = (K[]) new Object[INITIAL_CAPACITY];
        table1Values = (V[]) new Object[INITIAL_CAPACITY];
        table2Values = (V[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /// @brief Inserts a key-value pair into the hash table.
    /// If the key already exists, its value will be updated. If a collision occurs,
    /// the element is displaced and reinserted using the alternative hash function.
    ///
    /// @param key   the key to be inserted.
    /// @param value the value associated with the key.
    public void put(K key, V value) {
        if (size >= LOAD_FACTOR * table1Keys.length) {
            resize();
        }
        for (int i = 0; i < 4; i++) {
            int index1 = hash1(key);
            if (table1Keys[index1] == null) {
                table1Keys[index1] = key;
                table1Values[index1] = value;
                size++;
                return;
            }
            K displacedKey = table1Keys[index1];
            V displacedValue = table1Values[index1];
            table1Keys[index1] = key;
            table1Values[index1] = value;

            key = displacedKey;
            value = displacedValue;

            int index2 = hash2(key);
            if (table2Keys[index2] == null) {
                table2Keys[index2] = key;
                table2Values[index2] = value;
                size++;
                return;
            }
            displacedKey = table2Keys[index2];
            displacedValue = table2Values[index2];
            table2Keys[index2] = key;
            table2Values[index2] = value;

            key = displacedKey;
            value = displacedValue;
        }
        resize();
        put(key, value);
    }

    /**
     * @brief Retrieves the value associated with a given key.
     *
     * @param key the key to search for.
     * @return the value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        int index1 = hash1(key);
        if (key.equals(table1Keys[index1])) {
            return table1Values[index1];
        }
        int index2 = hash2(key);
        if (key.equals(table2Keys[index2])) {
            return table2Values[index2];
        }
        return null;
    }

    /**
     * @brief Removes a key-value pair from the hash table.
     *
     * @param key the key to remove.
     */
    public void remove(K key) {
        int index1 = hash1(key);
        if (key.equals(table1Keys[index1])) {
            table1Keys[index1] = null;
            table1Values[index1] = null;
            size--;
            return;
        }
        int index2 = hash2(key);
        if (key.equals(table2Keys[index2])) {
            table2Keys[index2] = null;
            table2Values[index2] = null;
            size--;
        }
    }

    /**
     * @brief Returns the number of elements in the hash table.
     *
     * @return the number of elements in the hash table.
     */
    public int size() {
        return size;
    }

    /**
     * @brief Checks if the hash table is empty.
     *
     * @return true if the hash table is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @brief Resizes the hash table to twice its current capacity and rehashes all elements.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        K[] oldTable1Keys = table1Keys;
        K[] oldTable2Keys = table2Keys;
        V[] oldTable1Values = table1Values;
        V[] oldTable2Values = table2Values;

        table1Keys = (K[]) new Object[oldTable1Keys.length * 2];
        table2Keys = (K[]) new Object[oldTable2Keys.length * 2];
        table1Values = (V[]) new Object[oldTable1Values.length * 2];
        table2Values = (V[]) new Object[oldTable2Values.length * 2];
        size = 0;

        for (int i = 0; i < oldTable1Keys.length; i++) {
            if (oldTable1Keys[i] != null) {
                put(oldTable1Keys[i], oldTable1Values[i]);
            }
        }
        for (int i = 0; i < oldTable2Keys.length; i++) {
            if (oldTable2Keys[i] != null) {
                put(oldTable2Keys[i], oldTable2Values[i]);
            }
        }
    }

    /**
     * @brief Computes the first hash function for a key.
     *
     * @param key the key to hash.
     * @return the hash value for the key.
     */
    private int hash1(K key) {
        return (key.hashCode() & 0x7fffffff) % table1Keys.length;
    }

    /**
     * @brief Computes the second hash function for a key.
     *
     * @param key the key to hash.
     * @return the hash value for the key.
     */
    private int hash2(K key) {
        return ((key.hashCode() * 31) & 0x7fffffff) % table2Keys.length;
    }

    public static void main(String[] args) {
        CuckooHashTable<Integer, String> hashTable = new CuckooHashTable<>();

        int key1 = 1;
        int key2 = 1 + hashTable.hash2(key1) * hashTable.getLength1();
        int key3 = 1 + hashTable.hash1(key2) * hashTable.getLength2();
        int key4 = 1 + hashTable.hash2(key3) * hashTable.getLength1();
        int key5 = 1 + hashTable.hash1(key4) * hashTable.getLength2();
        int key6 = 1 + hashTable.hash2(key5) * hashTable.getLength1();

        hashTable.put(key1, "value1");
        hashTable.put(key2, "value2");
        hashTable.put(key3, "value3");
        hashTable.put(key4, "value4");
        hashTable.put(key5, "value5");
        hashTable.put(key6, "value6");

        System.out.println("All keys inserted successfully!");
    }

    private int getLength2() {
        return table1Keys.length;
    }

    private int getLength1() {
        return table2Keys.length;
    }
}