package graph.model;

import java.util.List;

/**
 * Created by prnc on 20/08/2016.
 */
public class Graph2 {
    private final List<Vertex2> vertexes;
    private final List<Edge2> edges;

    public Graph2(List<Vertex2> vertexes, List<Edge2> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex2> getVertexes() {
        return vertexes;
    }

    public List<Edge2> getEdges() {
        return edges;
    }
}
