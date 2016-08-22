package graph.model;

import Data.Point;
<<<<<<< HEAD

/**
 * Created by pc on 18/08/2016.
 */
public class Vertex extends Point{
    final private String id;
    final private String name;


    public Vertex(String id, String name, double x, double y) {
        super(x, y);
        this.id = id;
        this.name = name;
    }
=======
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

>>>>>>> ceb0b9dbbcbc92103a5797cb782997a97d5ff8ea
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

<<<<<<< HEAD
}
=======
}
>>>>>>> ceb0b9dbbcbc92103a5797cb782997a97d5ff8ea
