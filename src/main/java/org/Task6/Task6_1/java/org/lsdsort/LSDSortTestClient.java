package org.Task6.Task6_1.java.org.lsdsort;

import java.util.Arrays;

public class LSDSortTestClient {

    public static void main(String[] args) {
        testEmptyArray();
        testSingleString();
        testStringsWithDifferentLengths();
        testStringsWithIdenticalValues();
        testStringsWithSpecialCharacters();
        testLargeInputArray();
    }

    /**
     * @brief Test case for an empty array
     */
    private static void testEmptyArray() {
        String[] arr = {};
        System.out.println("Test: Empty array");
        System.out.println("Before sorting: " + Arrays.toString(arr));
        LSDSort.lsdSort(arr);
        System.out.println("After sorting: " + Arrays.toString(arr) + "\n");
    }

    /**
     * @brief Test case for an array with a single string
     */
    private static void testSingleString() {
        String[] arr = {"apple"};
        System.out.println("Test: Single string");
        System.out.println("Before sorting: " + Arrays.toString(arr));
        LSDSort.lsdSort(arr);
        System.out.println("After sorting: " + Arrays.toString(arr) + "\n");
    }

    /**
     * @brief Test case for strings with different lengths
     */
    private static void testStringsWithDifferentLengths() {
        String[] arr = {"banana", "kiwi", "apple", "pear", "grapefruit", "plum"};
        System.out.println("Test: Strings with different lengths");
        System.out.println("Before sorting: " + Arrays.toString(arr));
        LSDSort.lsdSort(arr);
        System.out.println("After sorting: " + Arrays.toString(arr) + "\n");
    }

    /**
     * @brief Test case for strings with identical values
     */
    private static void testStringsWithIdenticalValues() {
        String[] arr = {"test", "test", "test"};
        System.out.println("Test: Strings with identical values");
        System.out.println("Before sorting: " + Arrays.toString(arr));
        LSDSort.lsdSort(arr);
        System.out.println("After sorting: " + Arrays.toString(arr) + "\n");
    }

    /**
     * @brief Test case for strings with special characters
     */
    private static void testStringsWithSpecialCharacters() {
        String[] arr = {"!hello", "world", "@java", "#LSD", "12345"};
        System.out.println("Test: Strings with special characters");
        System.out.println("Before sorting: " + Arrays.toString(arr));
        LSDSort.lsdSort(arr);
        System.out.println("After sorting: " + Arrays.toString(arr) + "\n");
    }

    /**
     * @brief Test case for a large input array
     */
    private static void testLargeInputArray() {
        String[] arr = {
                "cat", "dog", "elephant", "antelope", "bear", "zebra", "monkey", "lion", "tiger", "giraffe",
                "hippo", "kangaroo", "fox", "wolf", "bat", "owl", "panda", "snake", "shark", "whale"
        };
        System.out.println("Test: Large input array");
        System.out.println("Before sorting: " + Arrays.toString(arr));
        LSDSort.lsdSort(arr);
        System.out.println("After sorting: " + Arrays.toString(arr) + "\n");
    }
}

