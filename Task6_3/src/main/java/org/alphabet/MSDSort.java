package org.alphabet;

import java.util.Arrays;

/**
 * @brief Class for performing MSD radix sort on strings.
 */
public class MSDSort {

    private static final int CUTOFF = 15; // Threshold for switching to insertion sort

    /**
     * @brief Sorts an array of strings using MSD radix sort.
     * @param strings Array of strings to sort.
     * @param alphabet Custom alphabet implementing AlphabetInterface.
     */
    public static void sort(String[] strings, AlphabetInterface alphabet) {
        String[] aux = new String[strings.length];
        sort(strings, aux, 0, strings.length - 1, 0, alphabet);
    }

    private static void sort(String[] strings, String[] aux, int low, int high, int d, AlphabetInterface alphabet) {
        if (high <= low + CUTOFF) { // Use insertion sort for small subarrays
            insertionSort(strings, low, high, d);
            return;
        }

        int R = alphabet.R();
        int[] count = new int[R + 2]; // One extra for empty strings

        // Step 1: Frequency count
        for (int i = low; i <= high; i++) {
            int c = charAt(strings[i], d, alphabet);
            count[c + 2]++;
        }

        // Step 2: Transform counts to indices
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // Step 3: Distribute
        for (int i = low; i <= high; i++) {
            int c = charAt(strings[i], d, alphabet);
            aux[count[c + 1]++] = strings[i];
        }

        // Step 4: Copy back
        System.arraycopy(aux, 0, strings, low, high - low + 1);

        // Step 5: Recursively sort for each character value
        for (int r = 0; r < R; r++) {
            sort(strings, aux, low + count[r], low + count[r + 1] - 1, d + 1, alphabet);
        }
    }

    private static int charAt(String s, int d, AlphabetInterface alphabet) {
        if (d < s.length()) return alphabet.toIndex(s.charAt(d));
        return -1; // Treat empty character as the smallest value
    }

    private static void insertionSort(String[] strings, int low, int high, int d) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && less(strings[j], strings[j - 1], d); j--) {
                swap(strings, j, j - 1);
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}