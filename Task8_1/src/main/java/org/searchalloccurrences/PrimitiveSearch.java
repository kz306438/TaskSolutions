package org.searchalloccurrences;

import java.util.List;
import java.util.ArrayList;

public class PrimitiveSearch {

    public static Iterable<Integer> findAll(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i <= N - M; i++) {

            int j;
            for(j = 0; j < M; j++) {
                if(txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if(j == M) list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "abab";

        for(Integer elem : findAll(pattern, text)) {
            System.out.println(elem);
        }
    }
}



