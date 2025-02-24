package org.multipartor;

import Task9.NFA;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @brief Implementation of a Non-deterministic Finite Automaton (NFA) to evaluate whether a given text matches a regular expression.
 */
public class MultipartorNFA extends NFA {
    /**
     * @param regexp the regular expression to process
     * @brief Constructs an NFA from a given regular expression.
     */
    public MultipartorNFA(String regexp) {
        super(regexp);
    }

    @Override
    protected void construct() {

        Stack<Integer> ops = new Stack<>();
        for (int i = 0; i < M; i++) {
            int lp = i;

            if (re[i] == '(' || re[i] == '|') {
                ops.add(i);
            } else if (re[i] == ')') {
                List<Integer> ors = new ArrayList<>();
                while (re[ops.peek()] == '|') {
                    ors.add(ops.pop());
                }
                int lpOr = ops.pop(); // Matching '('
                for (int or : ors) {
                    G.addEdge(lpOr, or + 1);
                    G.addEdge(or, i);
                }
                lp = lpOr;
            }

            if (i < M - 1 && re[i + 1] == '*') {
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i + 1);
            }
        }

    }
}
