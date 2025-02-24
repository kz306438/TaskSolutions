package org.lazydeletion;

public class LinearProbingHashSTTest {
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

        System.out.println("=== Test: Adding elements ===");
        st.put("A", 1);
        st.put("B", 2);
        st.put("C", 3);
        st.put("D", 4);
        printTable(st);

        System.out.println("\n=== Test: Getting values ===");
        System.out.println("Key A: " + st.get("A"));
        System.out.println("Key B: " + st.get("B"));
        System.out.println("Key C: " + st.get("C"));
        System.out.println("Key D: " + st.get("D"));
        System.out.println("Key E (non-existent): " + st.get("E"));

        System.out.println("\n=== Test: Lazy deletion (deleting keys B and D) ===");
        st.delete("B");
        st.delete("D");
        printTable(st);

        System.out.println("\n=== Test: Overwriting deleted key (adding key B with a new value) ===");
        st.put("B", 5);
        printTable(st);

        System.out.println("\n=== Test: Table resizing (adding more elements) ===");
        st.put("E", 6);
        st.put("F", 7);
        st.put("G", 8);
        printTable(st);

        System.out.println("\n=== Test: Table shrinking (deleting several keys) ===");
        st.delete("A");
        st.delete("C");
        st.delete("E");
        st.delete("F");
        printTable(st);

        System.out.println("\n=== Test: Adding elements after table shrinking ===");
        st.put("H", 9);
        st.put("I", 10);
        printTable(st);
    }

    private static void printTable(LinearProbingHashST<String, Integer> st) {
        System.out.println("Current state of hash table:");
        for (String key : st.keys()) {
            Integer value = st.get(key);
            System.out.println(key + " => " + value);
        }
        System.out.println("Number of elements: " + st.size());
    }
}
