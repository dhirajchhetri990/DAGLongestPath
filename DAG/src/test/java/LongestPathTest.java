import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class LongestPathTest {

    private Graph buildSampleGraph() {
        Graph g = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        g.connect(v1, v2);
        g.connect(v2, v3);
        g.connect(v2, v4);
        g.connect(v3, v5);
        g.connect(v4, v5);

        return g;
    }

    @Test
    public void testTopoApproach() {
        Graph g = buildSampleGraph();
        Vertex start = new Vertex(1);

        LongestPathUsingTopo solver = new LongestPathUsingTopo();
        LongestPathUsingTopo.PathResult result = solver.findLongestPath(g, start);

        assertEquals(3, result.getLength(), "Path length should be 3 edges");
        assertEquals(List.of(new Vertex(1), new Vertex(2), new Vertex(3), new Vertex(5)),
                     result.getSequence(), "Expected path sequence incorrect");
    }

    @Test
    public void testDFSApproach() {
        Graph g = buildSampleGraph();
        Vertex start = new Vertex(1);

        LongestPathDFS solver = new LongestPathDFS();
        LongestPathDFS.PathResult result = solver.findLongestPath(g, start);

        assertEquals(3, result.getLength(), "Path length should be 3 edges");
        assertEquals(List.of(new Vertex(1), new Vertex(2), new Vertex(3), new Vertex(5)),
                     result.getNodes(), "Expected path sequence incorrect");
    }

    @Test
    public void testSingleVertexGraph() {
        Graph g = new Graph();
        Vertex v1 = new Vertex(1);
        g.addVertex(v1);

        LongestPathUsingTopo topoSolver = new LongestPathUsingTopo();
        LongestPathUsingTopo.PathResult topoResult = topoSolver.findLongestPath(g, v1);

        LongestPathDFS dfsSolver = new LongestPathDFS();
        LongestPathDFS.PathResult dfsResult = dfsSolver.findLongestPath(g, v1);

        assertEquals(0, topoResult.getLength());
        assertEquals(List.of(v1), topoResult.getSequence());

        assertEquals(0, dfsResult.getLength());
        assertEquals(List.of(v1), dfsResult.getNodes());
    }

    @Test
    public void testDisconnectedGraph() {
        Graph g = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        g.addVertex(v1);
        g.addVertex(v2);

        LongestPathUsingTopo topoSolver = new LongestPathUsingTopo();
        LongestPathUsingTopo.PathResult topoResult = topoSolver.findLongestPath(g, v1);

        assertEquals(0, topoResult.getLength());
        assertEquals(List.of(v1), topoResult.getSequence());
    }
}
