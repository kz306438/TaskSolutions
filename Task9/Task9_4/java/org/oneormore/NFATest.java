package org.oneormore;

import Task9.NFA;

/**3
 * @brief Test class for the NFA implementation, with emphasis on the "+" operator.
 */
public class NFATest {

    /**
     * @brief Main method for testing the NFA class.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        testPlusOperator();
        testMixedOperators();
        testEdgeCases();
    }

    /**
     * @brief Tests the "+" operator in the NFA.
     */
    private static void testPlusOperator() {
        System.out.println("Testing '+' operator...");

        // Test case: "a+"
        NFA nfa1 = new NFA("a+");
        System.out.println(nfa1.recognizes("a"));  // true: one 'a'
        System.out.println(nfa1.recognizes("aa")); // true: two 'a's
        System.out.println(nfa1.recognizes("aaa")); // true: three 'a's
        System.out.println(nfa1.recognizes(""));    // false: no 'a'

        System.out.println();

        // Test case: "(ab)+"
        NFA nfa2 = new NFA("(ab)+");
        System.out.println(nfa2.recognizes("ab"));    // true: one "ab"
        System.out.println(nfa2.recognizes("abab"));  // true: two "ab"s
        System.out.println(nfa2.recognizes("ababab")); // true: three "ab"s
        System.out.println(nfa2.recognizes(""));      // false: no match
        System.out.println(nfa2.recognizes("a"));     // false: incomplete match
        System.out.println(nfa2.recognizes("abb"));   // false: trailing extra 'b'
    }

    /**
     * @brief Tests mixed operators including "+" in the NFA.
     */
    private static void testMixedOperators() {
        System.out.println("\nTesting mixed operators with '+'...");

        // Test case: "a+b*"
        NFA nfa3 = new NFA("a+b*");
        System.out.println(nfa3.recognizes("a"));     // true: one 'a', no 'b'
        System.out.println(nfa3.recognizes("ab"));    // true: one 'a', one 'b'
        System.out.println(nfa3.recognizes("abb"));   // true: one 'a', two 'b's
        System.out.println(nfa3.recognizes("aaa"));   // true: only 'a's
        System.out.println(nfa3.recognizes(""));      // false: no 'a'

        System.out.println();

        // Test case: "(a|b)+c"
        NFA nfa4 = new NFA("(a|b)+c");
        System.out.println(nfa4.recognizes("ac"));    // true: one 'a', one 'c'
        System.out.println(nfa4.recognizes("bc"));    // true: one 'b', one 'c'
        System.out.println(nfa4.recognizes("aabbc")); // true: multiple 'a' or 'b', one 'c'
        System.out.println(nfa4.recognizes("c"));     // false: no 'a' or 'b'
        System.out.println(nfa4.recognizes("abc"));   // true: combination of 'a' and 'b', one 'c'
    }

    /**
     * @brief Tests edge cases for the NFA.
     */
    private static void testEdgeCases() {
        System.out.println("\nTesting edge cases...");

        // Test case: "(a+)+"
        NFA nfa6 = new NFA("(a+)+");
        System.out.println(nfa6.recognizes("a"));     // true: one 'a'
        System.out.println(nfa6.recognizes("aa"));    // true: multiple 'a's
        System.out.println(nfa6.recognizes("aaa"));   // true: more repetitions
        System.out.println(nfa6.recognizes(""));      // false: no 'a'
    }
}

