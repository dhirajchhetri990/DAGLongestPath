import java.util.*;

public class LongestPathDFS {

    public static class PathResult {
        private final int length;
        private final List<Vertex> nodes;

        public PathResult(int length, List<Vertex> nodes) {
            this.length = length;
            this.nodes = nodes;
        }

        public int getLength() { return length; }
        public List<Vertex> getNodes() { return nodes; }

        @Override
        public String toString() {
            return "Length=" + length + ", Path=" + nodes;
        }
    }

    private int dfs(Vertex v, Graph g,
                   Map<Vertex, Integer> memo,
                   Map<Vertex, Vertex> next) {
        if (memo.containsKey(v)) return memo.get(v);

        int best = 0;
        Vertex bestNext = null;
        for (Vertex neighbor : g.getNeighbors(v)) {
            int cand = 1 + dfs(neighbor, g, memo, next);
            if (cand > best) {
                best = cand;
                bestNext = neighbor;
            }
        }

        if (bestNext != null) next.put(v, bestNext);
        memo.put(v, best);
        return best;
    }

    public PathResult findLongestPath(Graph g, Vertex source) {
        if (!g.getVertices().contains(source)) {
            throw new IllegalArgumentException("Source not in graph: " + source);
        }

        Map<Vertex, Integer> memo = new HashMap<>();
        Map<Vertex, Vertex> next = new HashMap<>();

        int length = dfs(source, g, memo, next);

        List<Vertex> path = new ArrayList<>();
        for (Vertex cur = source; cur != null; cur = next.get(cur)) {
            path.add(cur);
        }

        return new PathResult(length, path);
    }
}
