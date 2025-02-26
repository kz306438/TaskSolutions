package org.Task6.Task6_1.java.org.lsdsort;

import java.util.Arrays;

public class LSDSort {

    /**
     * @brief Perform LSD radix sort on an array of strings with variable lengths
     * @param arr The array of strings to sort
     */
    public static void lsdSort(String[] arr) {
        int n = arr.length;
        int maxLen = getMaxStringLength(arr);

        for (int d = maxLen - 1; d >= 0; d--) {
            countingSortByDigit(arr, n, d);
        }
    }

    /**
     * @brief Get the length of the longest string in the array
     * @param arr The array of strings
     * @return Maximum string length
     */
    private static int getMaxStringLength(String[] arr) {
        int maxLen = 0;
        for (String s : arr) {
            maxLen = Math.max(maxLen, s.length());
        }
        return maxLen;
    }

    /**
     * @brief Perform counting sort on strings based on the specified digit
     * @param arr The array of strings to sort
     * @param n Number of strings
     * @param d Current character position to sort by
     */
    private static void countingSortByDigit(String[] arr, int n, int d) {
        final int R = 256; // Extended ASCII radix
        String[] aux = new String[n];
        int[] count = new int[R + 1];

        // Step 1: Count frequency of each character at position d
        for (String s : arr) {
            char c = d < s.length() ? s.charAt(d) : 0; // Treat missing chars as null (0)
            count[c + 1]++;
        }

        // Step 2: Compute cumulative count
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        // Step 3: Distribute strings to auxiliary array
        for (String s : arr) {
            char c = d < s.length() ? s.charAt(d) : 0;
            aux[count[c]++] = s;
        }

        // Step 4: Copy back to the original array
        System.arraycopy(aux, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        String[] arr = {"banana", "apple", "grape", "kiwi", "peach", "pear", "plum"};

        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr));

        lsdSort(arr);

        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(arr));
    }
}
