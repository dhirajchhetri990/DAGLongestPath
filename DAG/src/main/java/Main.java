public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        g.connect(v1,v2);
        g.connect(v2,v3);
        g.connect(v2,v4);
        g.connect(v3,v5);
        g.connect(v4,v5);

        LongestPathUsingTopo topoSolver = new LongestPathUsingTopo();
        System.out.println("Topo Sort Solution: " + topoSolver.findLongestPath(g, v1));

        LongestPathDFS dfsSolver = new LongestPathDFS();
        System.out.println("DFS Solution: " + dfsSolver.findLongestPath(g, v1));
    }
}
