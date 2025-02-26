package org.Task8.Task8_1.java.org.searchalloccurrences;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Implements the Boyer-Moore algorithm for substring searching.
 */
public class BoyerMoore {

    private final String pattern;
    private final int[] right;

    /**
     * @brief Constructs a BoyerMoore object and preprocesses the bad character table for the given pattern.
     * @param pattern The pattern to search for.
     */
    public BoyerMoore(String pattern) {
        this.pattern = pattern;
        int alphabetSize = 256; // Assuming ASCII
        right = new int[alphabetSize];

        for (int i = 0; i < alphabetSize; i++) {
            right[i] = -1;
        }

        for (int j = 0; j < pattern.length(); j++) {
            right[pattern.charAt(j)] = j;
        }
    }

    /**
     * @brief Searches for all occurrences of the pattern in the given text.
     * @param text The text to search within.
     * @return An Iterable containing the starting indices of all occurrences of the pattern.
     */
    public Iterable<Integer> search(String text) {
        List<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                occurrences.add(i);
                skip = 1;
            }
        }

        return occurrences;
    }

    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "abab";

        BoyerMoore bm = new BoyerMoore(pattern);
        for (int index : bm.search(text)) {
            System.out.println("Pattern found at index: " + index);
        }
    }
}
