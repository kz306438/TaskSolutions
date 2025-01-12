package org.uniquesubstrings;

import java.util.*;

/**
 * @brief Finds all unique substrings of length L using Rabin-Karp algorithm.
 */
public class UniqueSubstrings {

    /**
     * @param s The input string.
     * @param L The length of substrings to find.
     * @return A set of unique substrings of length L.
     */
    public static Set<String> findUniqueSubstrings(String s, int L) {
        if (s == null || L <= 0 || L > s.length()) {
            throw new IllegalArgumentException("Invalid input");
        }

        Set<String> uniqueSubstrings = new HashSet<>();
        Set<Long> seenHashes = new HashSet<>();

        int base = 256; // Number of possible characters (ASCII)
        long prime = 101; // A prime number for hashing
        long hash = 0; // Current hash value
        long highestBasePower = 1; // base^(L-1) % prime

        // Compute the hash for the first substring of length L and the highest base power
        for (int i = 0; i < L; i++) {
            hash = (hash * base + s.charAt(i)) % prime;
            if (i < L - 1) {
                highestBasePower = (highestBasePower * base) % prime;
            }
        }

        // Add the first substring's hash and value
        seenHashes.add(hash);
        uniqueSubstrings.add(s.substring(0, L));

        // Iterate through the string and compute rolling hashes
        for (int i = L; i < s.length(); i++) {
            // Remove the contribution of the leftmost character and add the new one
            hash = (hash - s.charAt(i - L) * highestBasePower % prime + prime) % prime;
            hash = (hash * base + s.charAt(i)) % prime;

            // Add the substring if its hash is unique
            String substring = s.substring(i - L + 1, i + 1);
            if (!seenHashes.contains(hash)) {
                seenHashes.add(hash);
                uniqueSubstrings.add(substring);
            }
        }

        return uniqueSubstrings;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the input string:");
        String input = scanner.nextLine();

        System.out.println("Enter the length of substrings (L):");
        int L = scanner.nextInt();

        try {
            Set<String> result = findUniqueSubstrings(input, L);
            System.out.println("Unique substrings of length " + L + ":");
            for (String substring : result) {
                System.out.println(substring);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
