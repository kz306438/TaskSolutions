package org.alphabet;

/**
 * @brief Class representing a custom alphabet for string manipulations.
 */
public class Alphabet implements AlphabetInterface {
    private char[] alphabet; // Alphabet characters
    private int[] inverse;   // Mapping from characters to indices
    private final int R;     // Radix (alphabet size)

    /**
     * @brief Constructs an alphabet from the given string.
     * @param s String representing the alphabet.
     */
    public Alphabet(String s) {
        boolean[] seen = new boolean[Character.MAX_VALUE];
        for (char c : s.toCharArray()) {
            if (seen[c]) throw new IllegalArgumentException("Duplicate character: " + c);
            seen[c] = true;
        }
        this.alphabet = s.toCharArray();
        this.R = s.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++) inverse[i] = -1;
        for (int i = 0; i < R; i++) inverse[alphabet[i]] = i;
    }

    @Override
    public char toChar(int index) {
        if (index < 0 || index >= R) throw new IllegalArgumentException("Index out of bounds");
        return alphabet[index];
    }

    @Override
    public int toIndex(char c) {
        if (inverse[c] == -1) throw new IllegalArgumentException("Character not in alphabet");
        return inverse[c];
    }

    @Override
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    @Override
    public int R() {
        return R;
    }

    @Override
    public int lgR() {
        int bits = 0, tempR = R - 1;
        while (tempR > 0) {
            tempR >>= 1;
            bits++;
        }
        return bits;
    }

    @Override
    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        for (int i = 0; i < s.length(); i++) indices[i] = toIndex(s.charAt(i));
        return indices;
    }

    @Override
    public String toChars(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int index : indices) sb.append(toChar(index));
        return sb.toString();
    }
}
