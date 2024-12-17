package org.integerlsdsort;

import java.util.Arrays;

public class SublinearSort {
    private static final int BIT_MASK = 0xFFFF; // Mask to extract the lower 16 bits

    /**
     * @brief Sorts an array using LSD Radix Sort for the upper 16 bits and Insertion Sort.
     * @param arr Array of integers to be sorted
     */
    public static void sublinearSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        // Phase 1: LSD Radix Sort on the upper 16 bits
        lsdRadixSortUpper16Bits(arr);

        // Phase 2: Final Insertion Sort
        insertionSort(arr);
    }

    /**
     * @brief Performs LSD Radix Sort on the upper 16 bits of the keys.
     * @param arr Array of integers to be sorted
     */
    private static void lsdRadixSortUpper16Bits(int[] arr) {
        int[] aux = new int[arr.length];
        int[] count = new int[65537]; // 2^16 + 1 for counting sort

        // Extract upper 16 bits and count frequencies
        for (int num : arr) {
            int key = (num >>> 16) & BIT_MASK; // Upper 16 bits
            count[key + 1]++;
        }

        // Compute cumulative counts
        for (int r = 0; r < 65536; r++) {
            count[r + 1] += count[r];
        }

        // Distribute the elements into the auxiliary array
        for (int num : arr) {
            int key = (num >>> 16) & BIT_MASK;
            aux[count[key]++] = num;
        }

        // Copy back to the original array
        System.arraycopy(aux, 0, arr, 0, arr.length);
    }

    /**
     * @brief Performs Insertion Sort on the array.
     * @param arr Array of integers to be sorted
     */
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12345678, 87654321, 12312312, 32132132, 11111111, 22222222};
        System.out.println("Original array: " + Arrays.toString(arr));

        sublinearSort(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
