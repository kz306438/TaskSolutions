package org.wildcard;

public class WCNFATest {
    public static void main(String[] args) {
        // Examples of regular expressions with the wildcard character '.'
        WCNFA nfa1 = new WCNFA("A.C"); // Expected result: true for "ABC"
        System.out.println(nfa1.recognizes("ABC")); // true
        System.out.println(nfa1.recognizes("AXC")); // true
        System.out.println(nfa1.recognizes("AB")); // false

        System.out.println();

        WCNFA nfa2 = new WCNFA("A.B*C"); // Expected result: true for "ABC", "ABBC", "ABBBBC", etc.
        System.out.println(nfa2.recognizes("ABC")); // true
        System.out.println(nfa2.recognizes("ABBBBC")); // true
        System.out.println(nfa2.recognizes("AC")); // false
        System.out.println(nfa2.recognizes("ABBBAC")); // false

        System.out.println();

        WCNFA nfa3 = new WCNFA("(A|B)."); // Expected result: true for "AC", "BC", "ABC", "BBC"
        System.out.println(nfa3.recognizes("AC")); // true
        System.out.println(nfa3.recognizes("BC")); // true
        System.out.println(nfa3.recognizes("ABC")); // false
        System.out.println(nfa3.recognizes("BBC")); // false
        System.out.println(nfa3.recognizes("ABBC")); // false
        System.out.println();

        WCNFA nfa4 = new WCNFA(".*"); // Expected result: true for any string
        System.out.println(nfa4.recognizes("AnyString")); // true
        System.out.println(nfa4.recognizes("")); // true (empty string also matches)

        System.out.println();

        WCNFA nfa5 = new WCNFA("A.*B"); // Expected result: true for strings starting with "A" and ending with "B"
        System.out.println(nfa5.recognizes("AAB")); // true
        System.out.println(nfa5.recognizes("AABBB")); // true
        System.out.println(nfa5.recognizes("AB")); // true
        System.out.println(nfa5.recognizes("BBA")); // false
    }
}
