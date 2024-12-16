package org.alphabet;

public class AlphabetSortTestClient {

    /**
     * @brief Main method to test LSD and MSD sorting.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Alphabet Sorting Test Client\n");

        // Define the alphabet
        AlphabetInterface alphabet = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println("Using Alphabet: " + alphabet);

        // Test strings for sorting
        String[] testStrings = {"BAC", "ABC", "CBA", "ACB", "BCA", "CAB"};
        System.out.println("\nOriginal Strings:");
        printArray(testStrings);

        // Test LSD Sort (requires fixed-length strings)
        int fixedLength = 3; // All strings must have length 3 for LSD sort
        if (validateFixedLength(testStrings, fixedLength)) {
            LSDSort.sort(testStrings, alphabet, fixedLength);
            System.out.println("\nSorted Strings using LSD Sort:");
            printArray(testStrings);
        } else {
            System.out.println("\nError: Strings are not of equal length for LSD Sort.");
        }

        // Reset the test strings for MSD Sort
        testStrings = new String[]{"BAC", "ABC", "CBA", "ACB", "BCA", "CAB"};

        // Test MSD Sort
        MSDSort.sort(testStrings, alphabet);
        System.out.println("\nSorted Strings using MSD Sort:");
        printArray(testStrings);

        System.out.println("\nTest completed successfully!");
    }

    /**
     * @brief Prints the elements of a string array.
     * @param array Array to print.
     */
    private static void printArray(String[] array) {
        for (String s : array) {
            System.out.println(s);
        }
    }

    /**
     * @brief Validates if all strings in the array have the same fixed length.
     * @param strings Array of strings.
     * @param length Expected fixed length.
     * @return True if all strings have the same length, false otherwise.
     */
    private static boolean validateFixedLength(String[] strings, int length) {
        for (String s : strings) {
            if (s.length() != length) {
                return false;
            }
        }
        return true;
    }
}