package Data;

import java.util.Map;

/**
 * Created by prnc on 15/08/2016.
 */
public class Vertex extends Point {
    public Map<Vertex, Double> listNeighbor;

    public Vertex(double x, double y) {
        super(x, y);
    }

    public void setListNeighbor(String filePath){

    }

    public boolean isLeaf(){
        return listNeighbor.isEmpty();
    }
}
