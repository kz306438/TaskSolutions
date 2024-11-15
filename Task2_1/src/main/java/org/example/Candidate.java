package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @brief A class representing a candidate with a name and surname.
 */
public record Candidate(String name, String surname) implements Comparable<Candidate> {
    private static final char[] SORT_ORDER = {'R', 'W', 'Q', 'O', 'J', 'M', 'V', 'A', 'H', 'B',
            'S', 'G', 'Z', 'X', 'N', 'T', 'C', 'I', 'E', 'K',
            'U', 'P', 'D', 'Y', 'F', 'L'};

    private static final Map<Character, Integer> charOrderMap = new HashMap<>();

    static {
        for (int i = 0; i < SORT_ORDER.length; i++) {
            charOrderMap.put(SORT_ORDER[i], i);
        }
    }

    /**
     * @param name    The first name of the candidate.
     * @param surname The surname of the candidate.
     * @brief Constructs a Candidate with a name and surname.
     */
    public Candidate {
    }

    /**
     * @return The candidate's first name.
     * @brief Gets the name of the candidate.
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * @return The candidate's surname.
     * @brief Gets the surname of the candidate.
     */
    @Override
    public String surname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    /**
     * @param other The other candidate to compare to.
     * @return A negative integer, zero, or a positive integer as this candidate
     * is less than, equal to, or greater than the specified candidate.
     * @brief Compares this candidate to another candidate for order.
     */
    @Override
    public int compareTo(Candidate other) {
        int nameComparison = compareStrings(this.name.toUpperCase(), other.name.toUpperCase());
        if (nameComparison != 0) {
            return nameComparison;
        }
        return compareStrings(this.surname.toUpperCase(), other.surname.toUpperCase());
    }

    /**
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return A negative integer, zero, or a positive integer as the first
     * string is less than, equal to, or greater than the second string.
     * @brief Compares two strings based on a custom character order.
     */
    private int compareStrings(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; i++) {
            int order1 = charOrderMap.getOrDefault(s1.charAt(i), Integer.MAX_VALUE);
            int order2 = charOrderMap.getOrDefault(s2.charAt(i), Integer.MAX_VALUE);
            if (order1 != order2) {
                return Integer.compare(order1, order2);
            }
        }
        return Integer.compare(s1.length(), s2.length());
    }
}
