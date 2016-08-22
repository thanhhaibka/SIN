package graph.model;

import java.util.List;

/**
<<<<<<< HEAD
 * Created by pc on 18/08/2016.
=======
 * Created by prnc on 19/08/2016.
>>>>>>> ceb0b9dbbcbc92103a5797cb782997a97d5ff8ea
 */
public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
