package org.example;

import java.util.Arrays;
import java.util.List;

/**
 * @brief Client to test the NFA class for various regular expression scenarios.
 */
public class NFATestClient {
    public static void main(String[] args) {
        // Test cases for the NFA with expected matching and non-matching inputs
        String[][] testCases = {
                {"(a|b)", "a", "b", "c", "", "ab"},                   // Simple OR
                {"(a|b|c)", "a", "b", "c", "d", "", "abc"},        // Multi-part OR
                {"(a(b|c)d)", "abd", "acd", "ad", "abcd", ""},       // Grouping with OR
                {"(a|b)*", "", "a", "b", "ab", "abab", "c"},     // Kleene star with OR
                {"(a|(b|c))d", "a", "bd", "cd", "abcd", ""},        // Nested OR
                {"((a|b)c|d)e", "ace", "bce", "de", "ae", "be"},  // Complex nested OR
                {"(a|b|c|d|e)", "a", "b", "c", "d", "e", "f"},    // Multiple standalone ORs
                {"((a|b)|(c|d))", "a", "b", "c", "d", "e"},         // Grouped ORs
                {"(a|(b|(c|d)))", "a", "b", "c", "d", "e"},         // Nested group ORs
                {"((a|b)*c|(d|e)f)", "c", "abc", "df", "ef", "aef"} // Combination of Kleene star, OR, and concatenation
        };

        for (String[] testCase : testCases) {
            String regexp = testCase[0];
            System.out.println("Testing NFA for regular expression: " + regexp);
            NFA nfa = new NFA(regexp);

            for (int i = 1; i < testCase.length; i++) {
                String input = testCase[i];
                boolean result = nfa.recognizes(input);
                System.out.printf("Input: '%s' -> %s\n", input, result ? "Matched" : "Not Matched");
            }
            System.out.println();
        }

        // Edge cases
        System.out.println("Edge case tests:");
        testEdgeCases();
    }

    /**
     * @brief Tests edge cases for the NFA.
     */
    private static void testEdgeCases() {
        String[][] edgeCases = {
                {"", ""},                          // Empty regular expression
                {"(a|)", "a", ""},                  // OR with missing operand
                {"(|b)", "b", ""},                  // OR with missing operand on the left
                {"(|)", ""},                        // Empty group
                {"a*", "", "a", "aa", "aaa"},  // Kleene star alone
                {"(a|b|c)*", "", "a", "b", "c", "abc"} // Star applied to multiple ORs
        };

        for (String[] edgeCase : edgeCases) {
            String regexp = edgeCase[0];
            System.out.println("Testing edge case NFA for regular expression: " + regexp);
            NFA nfa = new NFA(regexp);

            for (int i = 1; i < edgeCase.length; i++) {
                String input = edgeCase[i];
                boolean result = nfa.recognizes(input);
                System.out.printf("Input: '%s' -> %s\n", input, result ? "Matched" : "Not Matched");
            }
            System.out.println();
        }
    }
}
