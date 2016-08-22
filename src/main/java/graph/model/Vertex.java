package graph.model;
import cluster.Cluster;

/**
 * Created by prnc on 19/08/2016.
 */
public class Vertex extends Cluster{
    final private String id;
    final private String name;

    public Vertex(String id, String name, Cluster cluster) {
        super(Integer.parseInt(id));
        this.setCentrePoint(cluster.getCentrePoint());
        this.setPoints(cluster.getPoints());
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
