package org.Task9.Task9_1.java.org.wildcard;

import org.Task9.NFA;

import java.util.Stack;

/**
 * @brief Implementation of a Non-deterministic Finite Automaton (NFA) to evaluate whether a given text matches a regular expression.
 */
public class WCNFA extends NFA {
    /**
     * @param regexp the regular expression to process
     * @brief Constructs an NFA from a given regular expression.
     */
    public WCNFA(String regexp) {
        super(regexp);
    }

    protected void construct()
    {
        Stack<Integer> ops = new Stack<>();
        for (int i = 0; i < M; i++) {
            int lp = i;

            if (re[i] == '(' || re[i] == '|') {
                ops.add(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
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