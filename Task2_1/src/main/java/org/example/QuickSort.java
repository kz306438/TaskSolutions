package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @brief A class for sorting arrays and lists using the quick sort algorithm.
 */
public class        QuickSort {

    /**
     * @brief Sorts an array or list using the quick sort algorithm.
     * @param collection The array or list to be sorted.
     * @param <T> The type of elements in the collection, which must implement Comparable.
     */
    public <T extends Comparable<T>> void sort(T[] collection) {
        sort(Arrays.asList(collection));
    }

    /**
     * @brief Sorts a list using the quick sort algorithm.
     * @param list The list to be sorted.
     * @param <T> The type of elements in the list, which must implement Comparable.
     */
    public <T extends Comparable<T>> void sort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * @brief Recursively applies the quick sort algorithm to a list.
     * @param list The list to be sorted.
     * @param low The starting index of the sublist.
     * @param high The ending index of the sublist.
     * @param <T> The type of elements in the list, which must implement Comparable.
     */
    private <T extends Comparable<T>> void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    /**
     * @brief Partitions the list around a pivot.
     * @param list The list to be partitioned.
     * @param low The starting index of the sublist.
     * @param high The ending index of the sublist.
     * @param <T> The type of elements in the list, which must implement Comparable.
     * @return The pivot index after partitioning.
     */
    private <T extends Comparable<T>> int partition(List<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}
