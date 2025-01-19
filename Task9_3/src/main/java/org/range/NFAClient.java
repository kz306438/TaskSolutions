package org.range;

/**
 * @brief Client to test the NFA implementation with valid and meaningful test cases.
 */
public class NFAClient {
    public static void main(String[] args) {
        // Valid test cases for the NFA
        String[] patterns = {
                "a*b",        // Matches zero or more 'a' followed by 'b'
                "(a|b)c",     // Matches "ac" or "bc"
                "[a-c]d",     // Matches "ad", "bd", or "cd"
                "[a-z]*",     // Matches any lowercase string
                "a[b-d]*e",   // Matches "ae", "abe", "acde", etc.
                "(ab|cd)*ef"  // Matches zero or more "ab" or "cd" followed by "ef"
        };

        // Texts to match against
        String[][] testCases = {
                {"b", "ab", "aab"},
                {"ac", "bc", "cc"},
                {"ad", "bd", "dd"},
                {"abc", "xyz", "hello"},
                {"ae", "afe", "acde"},
                {"ef", "abcf", "cdabef"}
        };

        // Run the test cases
        for (int i = 0; i < patterns.length; i++) {
            String pattern = patterns[i];
            NFA nfa = new NFA(pattern);
            System.out.println("Pattern: " + pattern);
            for (String text : testCases[i]) {
                boolean matches = nfa.recognizes(text);
                System.out.println("  Text: " + text + " -> " + (matches ? "MATCH" : "NO MATCH"));
            }
            System.out.println();
        }
    }
}

