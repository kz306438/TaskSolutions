package org.Task8.Task8_3.java.org.boyermoorbinary;

/**
 * Implementation of a Boyer-Moore-like algorithm for binary substring search,
 * using groups of bits as symbols.
 */
public class BoyerMooreBinary {

    private final int b; // Number of bits per symbol
    private final int[] right;
    private final int M; // Length of the pattern

    /**
     * @brief Constructs the Boyer-Moore binary search class.
     * @param pattern The binary pattern to search for.
     * @param b The number of bits per symbol.
     */
    public BoyerMooreBinary(String pattern, int b) {
        this.b = b;
        this.M = pattern.length();

        int R = (int) Math.pow(2, b); // Total number of symbols of b bits
        right = new int[R];
        for (int i = 0; i < R; i++) {
            right[i] = -1;
        }

        for (int j = 0; j <= M - b; j++) {
            int symbol = extractBits(pattern, j);
            right[symbol] = j;
        }
    }

    /**
     * @brief Searches for the binary pattern in the given text.
     * @param text The binary string to search within.
     * @return The starting index of the pattern in the text, or -1 if not found.
     */
    public int search(String text) {
        int N = text.length();
        int R = (int) Math.pow(2, b);

        for (int i = 0; i <= N - M; ) {
            int j;
            for (j = M - b; j >= 0; j -= b) {
                int symbol = extractBits(text, i + j);
                if (right[symbol] == -1 || right[symbol] < j) {
                    break;
                }
            }

            if (j < 0) {
                return i; // Match found
            }

            int shiftSymbol = extractBits(text, i + M - b);
            i += Math.max(1, j - right[shiftSymbol]);
        }

        return -1; // No match found
    }

    /**
     * @brief Extracts a b-bit symbol from the binary string starting at the given position.
     * @param binaryString The binary string.
     * @param startPos The starting position.
     * @return The integer representation of the b-bit symbol.
     */
    private int extractBits(String binaryString, int startPos) {
        int symbol = 0;
        for (int i = 0; i < b; i++) {
            if (startPos + i < binaryString.length() && binaryString.charAt(startPos + i) == '1') {
                symbol |= (1 << (b - 1 - i));
            }
        }
        return symbol;
    }

    /**
     * @brief Main method for testing the algorithm.
     */
    public static void main(String[] args) {
        String text = "1101101011110110";
        String pattern = "10111";
        int b = 2; // Using groups of 2 bits as symbols

        BoyerMooreBinary bmb = new BoyerMooreBinary(pattern, b);
        int index = bmb.search(text);

        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found.");
        }
    }
}
