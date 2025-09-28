import java.util.*;

public class Graph {
    // adjacency list representation
    private final Map<Vertex, Set<Vertex>> adjacency;

    public Graph() {
        this.adjacency = new HashMap<>();
    }

    // Add a vertex if missing
    public void addVertex(Vertex v) {
        adjacency.putIfAbsent(v, new LinkedHashSet<>()); // LinkedHashSet preserves order
    }

    // Connect vertices with an edge
    public void connect(Vertex from, Vertex to) {
        addVertex(from);
        addVertex(to);
        adjacency.get(from).add(to); // avoids duplicate edges automatically
    }

    // All vertices in the graph
    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(adjacency.keySet());
    }

    // Neighbors of a vertex
    public Collection<Vertex> getNeighbors(Vertex v) {
        return adjacency.getOrDefault(v, Collections.emptySet());
    }

    // Topological sort (throws if cycle found)
    public List<Vertex> topologicalSort() {
        Map<Vertex, Integer> indegree = new HashMap<>();

        // initialize indegree
        for (Vertex v : adjacency.keySet()) {
            indegree.putIfAbsent(v, 0);
            for (Vertex u : adjacency.get(v)) {
                indegree.put(u, indegree.getOrDefault(u, 0) + 1);
            }
        }

        Deque<Vertex> queue = new ArrayDeque<>();
        for (Map.Entry<Vertex, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Vertex> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            order.add(v);
            for (Vertex neighbor : getNeighbors(v)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (order.size() != adjacency.size()) {
            throw new IllegalStateException("Cycle detected: graph is not a DAG");
        }
        return order;
    }
}
