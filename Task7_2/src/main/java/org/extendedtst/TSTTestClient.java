package org.extendedtst;

public class TSTTestClient {
    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();

        // Adding keys with associated values
        tst.put("shell", 1);
        tst.put("shore", 2);
        tst.put("ball", 3);
        tst.put("balcony", 4);
        tst.put("basket", 5);
        tst.put("bat", 6);

        // Print all keys and their values
        System.out.println("All keys in TST: " + tst.keys());
        for (String key : tst.keys()) {
            System.out.println("Key: " + key + ", Value: " + tst.get(key));
        }

        // Testing keysWithPrefix
        System.out.println("\nKeys with prefix 'ba': " + tst.keysWithPrefix("ba"));
        System.out.println("Keys with prefix 'sh': " + tst.keysWithPrefix("sh"));
        System.out.println("Keys with prefix 'a' (no match): " + tst.keysWithPrefix("a"));

        // Testing longestPrefixOf
        System.out.println("\nLongest prefix of 'basketball': " + tst.longestPrefixOf("basketball"));
        System.out.println("Longest prefix of 'shoreline': " + tst.longestPrefixOf("shoreline"));
        System.out.println("Longest prefix of 'unknown': " + tst.longestPrefixOf("unknown"));

        // Testing keysThatMatch
        System.out.println("\nKeys that match pattern '.a.l': " + tst.keysThatMatch(".a.l"));
        System.out.println("Keys that match pattern 'ba..o.y': " + tst.keysThatMatch("ba..o.y"));
        System.out.println("Keys that match pattern 's..e.': " + tst.keysThatMatch("s..e."));
        System.out.println("Keys that match pattern '.....': " + tst.keysThatMatch("....."));

        // Testing edge cases
        System.out.println("\nEdge case: keysWithPrefix with empty prefix: " + tst.keysWithPrefix(""));
        System.out.println("Edge case: keysThatMatch with empty pattern: " + tst.keysThatMatch(""));
        // Modifying TST: adding and updating values
        tst.put("bat", 10);
        tst.put("batman", 11);
        System.out.println("\nUpdated value for 'bat': " + tst.get("bat"));
        System.out.println("Added key 'batman': " + tst.get("batman"));
        System.out.println("All keys after modification: " + tst.keys());

        // Testing nonexistent keys
        System.out.println("\nNonexistent key 'unknown': " + tst.get("unknown"));
        System.out.println("Longest prefix of nonexistent 'xyz': " + tst.longestPrefixOf("xyz"));
    }
}
