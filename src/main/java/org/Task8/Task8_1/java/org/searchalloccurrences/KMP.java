package org.Task8.Task8_1.java.org.searchalloccurrences;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Implements the Knuth-Morris-Pratt (KMP) algorithm for substring searching.
 */
public class KMP {

    private final String pattern;
    private final int[] lps;

    /**
     * @brief Constructs a KnuthMorrisPratt object and precomputes the LPS array for the given pattern.
     * @param pattern The pattern to search for.
     */
    public KMP(String pattern) {
        this.pattern = pattern;
        this.lps = computeLPSArray(pattern);
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

        for (int i = 0, j = 0; i < n; ) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                occurrences.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return occurrences;
    }

    /**
     * @brief Computes the LPS (Longest Prefix Suffix) array for the pattern.
     * @param pattern The pattern to preprocess.
     * @return The LPS array.
     */
    private int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        for (int i = 1, len = 0; i < m; ) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = ++len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "abab";

        KMP kmp = new KMP(pattern);
        for (int index : kmp.search(text)) {
            System.out.println("Pattern found at index: " + index);
        }
    }
}
