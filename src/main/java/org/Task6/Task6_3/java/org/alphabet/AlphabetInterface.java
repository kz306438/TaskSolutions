package org.Task6.Task6_3.java.org.alphabet;

/**
 * @brief Interface for alphabet operations.
 */
public interface AlphabetInterface {
    /**
     * @brief Converts an index to the corresponding character.
     * @param index Index to convert.
     * @return Character at the specified index.
     */
    char toChar(int index);

    /**
     * @brief Converts a character to its index in the alphabet.
     * @param c Character to convert.
     * @return Index of the character.
     */
    int toIndex(char c);

    /**
     * @brief Checks if the character exists in the alphabet.
     * @param c Character to check.
     * @return True if character exists, false otherwise.
     */
    boolean contains(char c);

    /**
     * @brief Returns the size of the alphabet (radix).
     * @return Size of the alphabet.
     */
    int R();

    /**
     * @brief Calculates the number of bits required to represent an index.
     * @return Number of bits.
     */
    int lgR();

    /**
     * @brief Converts a string to an array of indices.
     * @param s Input string.
     * @return Array of indices corresponding to the characters in the string.
     */
    int[] toIndices(String s);

    /**
     * @brief Converts an array of indices to a string.
     * @param indices Array of indices.
     * @return String represented by the indices.
     */
    String toChars(int[] indices);
}

