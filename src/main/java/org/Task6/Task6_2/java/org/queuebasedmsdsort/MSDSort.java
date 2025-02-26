package org.Task6.Task6_2.java.org.queuebasedmsdsort;

import java.util.ArrayDeque;
import java.util.Queue;

public class MSDSort {
    private static final int R = 256; // Radix (number of ASCII characters)
    private static final int CUTOFF = 15; // For switching to insertion sort on small subarrays

    /**
     * @brief Sort an array of strings using MSD radix sort with queues
     * @param a Array of strings to sort
     */
    public static void sort(String[] a) {
        Queue<String> result = msdSort(a, 0);
        int i = 0;
        while (!result.isEmpty()) {
            a[i++] = result.poll();
        }
    }

    /**
     * @brief Perform MSD sort using queues
     * @param a Array of strings to sort
     * @param d Current character position to examine
     * @return Queue of sorted strings
     */
    private static Queue<String> msdSort(String[] a, int d) {
        if (a.length <= CUTOFF) {
            return insertionSort(a, d);
        }

        // Step 1: Create R+1 buckets (queues for each possible character)
        @SuppressWarnings("unchecked")
        Queue<String>[] buckets = new Queue[R + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayDeque<>();
        }

        // Step 2: Place strings into queues based on the d-th character
        for (String s : a) {
            int c = charAt(s, d);
            buckets[c + 1].offer(s); // Offset by 1 to handle -1 case
        }

        // Step 3: Recursively sort each non-empty bucket
        Queue<String> result = new ArrayDeque<>();
        for (int i = 0; i < buckets.length; i++) {
            if (!buckets[i].isEmpty()) {
                // Convert queue to array for recursive sorting
                String[] subArray = buckets[i].toArray(new String[0]);
                if (i > 0) {
                    Queue<String> sortedBucket = msdSort(subArray, d + 1);
                    result.addAll(sortedBucket);
                } else {
                    result.addAll(buckets[i]); // Bucket with empty strings
                }
            }
        }
        return result;
    }

    /**
     * @brief Helper method to return the character at position d, or -1 if d exceeds string length
     * @param s String to check
     * @param d Position of the character
     * @return Integer value of character or -1
     */
    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }

    /**
     * @brief Use insertion sort for small subarrays
     * @param a Array of strings
     * @param d Current character position
     * @return Queue of sorted strings
     */
    private static Queue<String> insertionSort(String[] a, int d) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1], d); j--) {
                swap(a, j, j - 1);
            }
        }

        Queue<String> result = new ArrayDeque<>();
        for (String s : a) {
            result.offer(s);
        }
        return result;
    }

    /**
     * @brief Compare two strings starting from a given position
     */
    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    /**
     * @brief Swap two elements in an array
     */
    private static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Main method for testing
    public static void main(String[] args) {
        String[] strings = {"she", "sells", "seashells", "by", "the", "sea", "shore"};
        System.out.println("Original array:");
        for (String s : strings) {
            System.out.println(s);
        }

        sort(strings);

        System.out.println("\nSorted array:");
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
