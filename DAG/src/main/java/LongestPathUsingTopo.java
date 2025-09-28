import java.util.*;

public class LongestPathUsingTopo {

    public static class PathResult {
        private final int length;
        private final List<Vertex> sequence;

        public PathResult(int length, List<Vertex> sequence) {
            this.length = length;
            this.sequence = sequence;
        }

        public int getLength() { return length; }
        public List<Vertex> getSequence() { return sequence; }

        @Override
        public String toString() {
            return "Length=" + length + ", Path=" + sequence;
        }
    }

    public PathResult findLongestPath(Graph g, Vertex source) {
        if (!g.getVertices().contains(source)) {
            throw new IllegalArgumentException("Source not in graph: " + source);
        }

        List<Vertex> order = g.topologicalSort();

        Map<Vertex, Integer> distance = new HashMap<>();
        Map<Vertex, Vertex> parent = new HashMap<>();
        for (Vertex v : g.getVertices()) {
            distance.put(v, Integer.MIN_VALUE);
        }
        distance.put(source, 0);

        for (Vertex u : order) {
            if (distance.get(u) == Integer.MIN_VALUE) continue;
            for (Vertex v : g.getNeighbors(u)) {
                int newDist = distance.get(u) + 1;
                if (newDist > distance.get(v)) {
                    distance.put(v, newDist);
                    parent.put(v, u);
                }
            }
        }

        int best = 0;
        Vertex farthest = source;
        for (Map.Entry<Vertex, Integer> entry : distance.entrySet()) {
            if (entry.getValue() > best) {
                best = entry.getValue();
                farthest = entry.getKey();
            }
        }

        LinkedList<Vertex> path = new LinkedList<>();
        for (Vertex cur = farthest; cur != null; cur = parent.get(cur)) {
            path.addFirst(cur);
        }

        return new PathResult(best, path);
    }
}
