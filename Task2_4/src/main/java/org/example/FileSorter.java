package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileSorter {

    // Comparator for sorting by file size in ascending order
    public static Comparator<File> sizeAscending() {
        return Comparator.comparingLong(File::length);
    }

    // Comparator for sorting by file size in descending order
    public static Comparator<File> sizeDescending() {
        return (f1, f2) -> Long.compare(f2.length(), f1.length());
    }

    // Comparator for sorting by file name in ascending order
    public static Comparator<File> nameAscending() {
        return Comparator.comparing(File::getName);
    }

    // Comparator for sorting by file name in descending order
    public static Comparator<File> nameDescending() {
        return (f1, f2) -> f2.getName().compareTo(f1.getName());
    }

    // Comparator for sorting by last modified date in ascending order
    public static Comparator<File> dateAscending() {
        return Comparator.comparingLong(File::lastModified);
    }

    // Comparator for sorting by last modified date in descending order
    public static Comparator<File> dateDescending() {
        return (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified());
    }

    /**
     * Selects a comparator based on user input
     * @param option the type of sorting (e.g., "-s", "-t", "-n")
     * @param order the order of sorting ("asc" or "desc")
     * @return the corresponding comparator
     */
    public static Comparator<File> getComparator(String option, String order) {
        switch (option) {
            case "-s":
                return "desc".equals(order) ? sizeDescending() : sizeAscending();
            case "-t":
                return "desc".equals(order) ? dateDescending() : dateAscending();
            case "-n":
                return "desc".equals(order) ? nameDescending() : nameAscending();
            default:
                throw new IllegalArgumentException("Invalid sort option. Use -s, -t, or -n.");
        }
    }


    /**
     * Stable sort for files array using the provided comparator.
     * @param files array of files to be sorted
     * @param comparator comparator to define sort order
     */
    public static void stableSort(File[] files, Comparator<File> comparator) {
        // Using merge sort via Arrays.sort for stable sorting
        Arrays.sort(files, comparator);
    }

}
