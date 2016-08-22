package graph.model;

/**
 * Created by prnc on 20/08/2016.
 */
public class Edge2 {
    private final String id;
    private final Vertex2 source;
    private final Vertex2 destination;
    private final int weight;

    public Edge2(String id, Vertex2 source, Vertex2 destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }
    public Vertex2 getDestination() {
        return destination;
    }

    public Vertex2 getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }

}
