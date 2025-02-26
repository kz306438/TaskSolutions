package org.Task6.Task6_3.java.org.alphabet;

/**
 * @brief Class for performing LSD radix sort on strings.
 */
public class LSDSort {

    /**
     * @brief Sorts an array of fixed-length strings using LSD radix sort.
     * @param strings Array of strings to sort.
     * @param alphabet Custom alphabet implementing AlphabetInterface.
     * @param W Fixed length of the strings.
     */
    public static void sort(String[] strings, AlphabetInterface alphabet, int W) {
        int N = strings.length;
        int R = alphabet.R(); // Radix (size of the alphabet)
        String[] aux = new String[N]; // Auxiliary array for sorting

        for (int d = W - 1; d >= 0; d--) { // Process each character position right to left
            int[] count = new int[R + 1];

            // Step 1: Frequency count
            for (String s : strings) {
                int index = alphabet.toIndex(s.charAt(d));
                count[index + 1]++;
            }

            // Step 2: Transform counts to indices
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // Step 3: Distribute
            for (String s : strings) {
                int index = alphabet.toIndex(s.charAt(d));
                aux[count[index]++] = s;
            }

            // Step 4: Copy back
            System.arraycopy(aux, 0, strings, 0, N);
        }
    }
}