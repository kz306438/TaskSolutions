package Task9;

import java.util.ArrayList;
import java.util.List;

public class Digraph {
    private final int V; // Number of vertices
    private int E; // Number of edges
    private final List<Integer>[] adj; // Adjacency lists

    /**
     * @brief Creates a directed graph with V vertices and no edges
     * @param V Number of vertices in the graph
     */
    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    /**
     * @brief Returns the number of vertices in the graph
     * @return Number of vertices
     */
    public int V() {
        return V;
    }

    /**
     * @brief Returns the number of edges in the graph
     * @return Number of edges
     */
    public int E() {
        return E;
    }

    /**
     * @brief Adds an edge v -> w to the graph
     * @param v The source vertex
     * @param w The destination vertex
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        E++;
    }

    /**
     * @brief Returns the vertices adjacent to vertex v
     * @param v The vertex
     * @return An iterator over the vertices adjacent to v
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * @brief Reverses the graph
     * @return A new graph with all edges reversed
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * @brief Returns a string representation of the graph
     * @return A string describing the graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices, ").append(E).append(" edges\n");
        for (int v = 0; v < V; v++) {
            sb.append(v).append(": ");
            for (int w : adj[v]) {
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * @brief Validates if a vertex is within valid range
     * @param v The vertex
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
        }
    }
}
