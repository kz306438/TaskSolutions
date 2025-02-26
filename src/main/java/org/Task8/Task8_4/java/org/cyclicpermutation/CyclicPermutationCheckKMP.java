package org.Task8.Task8_4.java.org.cyclicpermutation;

public class CyclicPermutationCheckKMP {

    /**
     * @brief Checks if one string is a cyclic permutation of another.
     * @param str1 The first string.
     * @param str2 The second string.
     * @return true if one string is a cyclic permutation of the other.
     */
    public static boolean isCyclicPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        String doubled = str1 + str1;
        return kmpSearch(doubled, str2);
    }

    /**
     * @brief Performs substring search using the Knuth-Morris-Pratt (KMP) algorithm.
     * @param text The text in which the search is performed.
     * @param pattern The pattern to search for.
     * @return true if the pattern is found in the text.
     */
    private static boolean kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] prefix = computePrefixFunction(pattern);
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Computes the prefix function (failure function) for the KMP algorithm.
     * @param pattern The pattern for which the prefix function is computed.
     * @return The prefix function array.
     */
    private static int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] prefix = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }

    public static void main(String[] args) {
        String str1 = "пальто";
        String str2 = "топаль";

        if (isCyclicPermutation(str1, str2)) {
            System.out.println("The string \"" + str2 + "\" is a cyclic permutation of the string \"" + str1 + "\".");
        } else {
            System.out.println("The string \"" + str2 + "\" is not a cyclic permutation of the string \"" + str1 + "\".");
        }
    }
}
