package org.oneormore;

import java.util.ArrayList;
import java.util.List;

public class DirectedDFS {
    private boolean[] marked; // Array to mark visited vertices

    /**
     * @brief Performs depth-first search for a single source vertex
     * @param G The directed graph
     * @param s The source vertex
     */
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    /**
     * @brief Performs depth-first search for a set of source vertices
     * @param G The directed graph
     * @param sources The sources (a set of vertices)
     */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) dfs(G, s);
        }
    }

    /**
     * @brief Recursive depth-first search
     * @param G The directed graph
     * @param v The current vertex
     */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    /**
     * @brief Checks if a vertex is reachable
     * @param v The vertex
     * @return true if the vertex is reachable, false otherwise
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * @brief Test client
     */
    public static void main(String[] args) {
        Digraph G = new Digraph(6); // Example graph
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(2, 3);
        G.addEdge(5, 0);

        List<Integer> sources = new ArrayList<>();
        sources.add(5); // Starting vertex

        DirectedDFS reachable = new DirectedDFS(G, sources);
        System.out.print("Reachable vertices: ");
        for (int v = 0; v < G.V(); v++) {
            if (reachable.marked(v)) System.out.print(v + " ");
        }
        System.out.println();
    }
}
