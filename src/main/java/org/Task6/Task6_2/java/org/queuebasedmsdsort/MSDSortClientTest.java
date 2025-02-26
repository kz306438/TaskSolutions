package org.Task6.Task6_2.java.org.queuebasedmsdsort;
import java.util.Arrays;

public class MSDSortClientTest {
    /**
     * @brief Testing MSD sorting on various sets of strings
     */
    public static void main(String[] args) {
        System.out.println("--- MSD String Sort Testing Client ---\n");

        // Test 1: Basic dataset
        String[] basicSet = {"she", "sells", "seashells", "by", "the", "sea", "shore"};
        runTest(basicSet, "Test 1: Basic Set");

        // Test 2: Empty strings and strings of different lengths
        String[] emptyAndShortSet = {"", "a", "abc", "abcd", "", "ab", "a"};
        runTest(emptyAndShortSet, "Test 2: Empty and Short Strings");

        // Test 3: Strings with identical prefixes
        String[] prefixSet = {"abc", "abcd", "abcde", "abc", "abcf", "abcg", "ab"};
        runTest(prefixSet, "Test 3: Strings with Same Prefixes");

        // Test 4: Strings with extended ASCII characters
        String[] asciiSet = {"\u00E9clair", "\u00E7a va", "na\u00EFve", "a\u00F1o", "caf\u00E9"};
        runTest(asciiSet, "Test 4: Extended ASCII Strings");

        // Test 5: Duplicate strings
        String[] duplicateSet = {"repeat", "repeat", "repeat", "test", "test", "a", "a"};
        runTest(duplicateSet, "Test 5: Duplicate Strings");

        // Test 6: All strings identical
        String[] allSameSet = {"same", "same", "same", "same", "same"};
        runTest(allSameSet, "Test 6: All Identical Strings");

        // Test 7: Strings in reverse order
        String[] reverseSet = {"zulu", "yankee", "xray", "whiskey", "victor", "uniform"};
        runTest(reverseSet, "Test 7: Reverse Ordered Strings");
    }

    /**
     * @brief Runs a test and prints the result
     * @param strings Array of strings to be sorted
     * @param testName Name of the test
     */
    private static void runTest(String[] strings, String testName) {
        System.out.println(testName + ":");
        System.out.println("Original array:");
        printArray(strings);

        // Copy the array to verify correctness
        String[] sortedCopy = strings.clone();
        Arrays.sort(sortedCopy);

        // Perform MSD sorting
        MSDSort.sort(strings);
        System.out.println("Sorted array:");
        printArray(strings);

        // Check for correctness
        if (Arrays.equals(strings, sortedCopy)) {
            System.out.println(testName + " PASSED\n");
        } else {
            System.out.println(testName + " FAILED\n");
        }
    }

    /**
     * @brief Prints an array of strings
     * @param array Array of strings
     */
    private static void printArray(String[] array) {
        for (String s : array) {
            System.out.println(s);
        }
        System.out.println();
    }
}
