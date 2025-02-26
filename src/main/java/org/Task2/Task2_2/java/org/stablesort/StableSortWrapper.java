package org.Task2.Task2_2.java.org.stablesort;

import java.util.Arrays;
import java.util.Comparator;

public class StableSortWrapper {

    /**
     * @brief Wrapper method to ensure stable sorting of an array.
     * @param array The array of elements to be sorted.
     * @param comparator Comparator used to define the sorting order of elements.
     * @param <T> Type of elements in the array.
     */
    public static <T> void stableSort(T[] array, Comparator<? super T> comparator) {
        // Wrapping each element in a pair (element, index)
        Pair<T>[] pairs = new Pair[array.length];
        for (int i = 0; i < array.length; i++) {
            pairs[i] = new Pair<>(array[i], i);
        }

        // Sorting pairs by value and, in case of equal values, by index
        Arrays.sort(pairs, Comparator.comparing((Pair<T> p) -> p.value, comparator)
                .thenComparingInt(p -> p.index));

        // Restoring the original array with stable order
        for (int i = 0; i < array.length; i++) {
            array[i] = pairs[i].value;
        }
    }

    // Helper class to store element and its original index
    private static class Pair<T> {
        T value;
        int index;

        Pair(T value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}

