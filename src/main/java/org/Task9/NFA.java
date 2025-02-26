package org.Task9;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @brief Implementation of a Non-deterministic Finite Automaton (NFA) to evaluate whether a given text matches a regular expression.
 */
public class NFA {
    protected final char[] re; // Regular expression as a character array
    protected final int M; // Length of the regular expression
    protected final Digraph G; // Directed graph representation of the NFA

    /**
     * @param regexp the regular expression to process
     * @brief Constructs an NFA from a given regular expression.
     */
    public NFA(String regexp) {
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 1);
        construct();
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

            if (i < M - 1) {
                if (re[i + 1] == '*') {
                    G.addEdge(lp, i + 1);
                    G.addEdge(i + 1, lp);
                } else if (re[i + 1] == '+') {
                    G.addEdge(i + 1, lp); // Add a backward edge for repetition
                }
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == '+' || re[i] == ')') {
                G.addEdge(i, i + 1);
            }
        }
    }


    /**
     * @param txt the input text to be matched
     * @return true if the text matches the regular expression, false otherwise
     * @brief Checks if the given text matches the regular expression represented by this NFA.
     */
    public boolean recognizes(String txt) {
        List<Integer> pc = new ArrayList<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);

        // Initial reachable states
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) pc.add(v);
        }

        // Process the input text
        for (int i = 0; i < txt.length(); i++) {
            List<Integer> match = new ArrayList<>();

            for (int v : pc) {
                if (v < M) {
                    if (re[v] == txt.charAt(i) || re[v] == '.') {
                        match.add(v + 1);
                    }
                }
            }

            // Update reachable states
            pc = new ArrayList<>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) pc.add(v);
            }
        }

        // Check if the final state is reachable
        for (int v : pc) {
            if (v == M) return true;
        }
        return false;
    }
}