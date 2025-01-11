package org.searchalloccurrences;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Implements the Rabin-Karp algorithm for substring searching.
 */
public class RabinKarp {

    private final String pattern;
    private final int m; // Length of the pattern
    private final int q; // A large prime number
    private final int d; // Number of characters in the input alphabet
    private final int patternHash; // Hash of the pattern
    private final int h; // Value of d^(m-1) % q

    /**
     * @brief Constructs a RabinKarp object and precomputes the hash for the pattern.
     * @param pattern The pattern to search for.
     * @param q A large prime number for hashing.
     */
    public RabinKarp(String pattern, int q) {
        this.pattern = pattern;
        this.m = pattern.length();
        this.q = q;
        this.d = 256;
        this.h = precomputeH();
        this.patternHash = computeHash(pattern, m);
    }

    /**
     * @brief Searches for all occurrences of the pattern in the given text.
     * @param text The text to search within.
     * @return An Iterable containing the starting indices of all occurrences of the pattern.
     */
    public Iterable<Integer> search(String text) {
        List<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int textHash = computeHash(text, m);

        for (int i = 0; i <= n - m; i++) {
            if (textHash == patternHash) {
                if (text.substring(i, i + m).equals(pattern)) {
                    occurrences.add(i); // Match found
                }
            }

            if (i < n - m) {
                textHash = recomputeHash(text, textHash, i);
            }
        }

        return occurrences;
    }

    /**
     * @brief Computes the initial hash value for the given string.
     * @param key The string to hash.
     * @param length The number of characters to hash.
     * @return The hash value.
     */
    private int computeHash(String key, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (d * hash + key.charAt(i)) % q;
        }
        return hash;
    }

    /**
     * @brief Recomputes the hash value after sliding the window by one character.
     * @param text The text being searched.
     * @param oldHash The previous hash value.
     * @param index The starting index of the sliding window.
     * @return The new hash value.
     */
    private int recomputeHash(String text, int oldHash, int index) {
        oldHash = (d * (oldHash - text.charAt(index) * h) + text.charAt(index + m)) % q;
        if (oldHash < 0) {
            oldHash += q;
        }
        return oldHash;
    }

    /**
     * @brief Precomputes the value of d^(m-1) % q.
     * @return The value of d^(m-1) % q.
     */
    private int precomputeH() {
        int h = 1;
        for (int i = 1; i < m; i++) {
            h = (h * d) % q;
        }
        return h;
    }

    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "abab";
        int q = 101;

        RabinKarp rk = new RabinKarp(pattern, q);
        for (int index : rk.search(text)) {
            System.out.println("Pattern found at index: " + index);
        }
    }
}
