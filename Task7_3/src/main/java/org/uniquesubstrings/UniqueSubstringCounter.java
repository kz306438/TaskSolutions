package org.uniquesubstrings;

import java.util.Scanner;

public class UniqueSubstringCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text:");
        String text = scanner.nextLine();
        System.out.println("Enter the length of substrings (L):");
        int L = scanner.nextInt();

        if (text == null || text.isEmpty() || L <= 0 || L > text.length()) {
            System.out.println("Invalid input. Ensure L > 0 and L <= text length.");
            return;
        }

        TST<Integer> tst = new TST<>();
        int uniqueCount = 0;

        System.out.println("Unique substrings of length " + L + ":");
        for (int i = 0; i <= text.length() - L; i++) {
            String substring = text.substring(i, i + L);
            if (tst.get(substring) == null) {
                tst.put(substring, 1); 
                uniqueCount++;
                System.out.println(substring);
            }
        }

        System.out.println("Total number of unique substrings of length " + L + ": " + uniqueCount);
    }
}