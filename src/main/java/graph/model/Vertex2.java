package graph.model;

import Data.Point;
import cluster.Cluster;
import graph.model.*;

/**
 * Created by prnc on 20/08/2016.
 */
public class Vertex2 extends Point {
    final private String id;
    final private String name;

    public Vertex2(String id, String name, Point point) {
        super(point.x, point.y);
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
        graph.model.Vertex2 other = (graph.model.Vertex2) obj;
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
