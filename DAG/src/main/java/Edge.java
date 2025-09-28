public record Edge(Vertex source, Vertex target) {
    @Override
    public String toString() {
        return source + " → " + target;
    }
}
