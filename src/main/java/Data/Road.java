package Data;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by prnc on 15/08/2016.
 */
public class Road {
    public Set<Point> points;
    public DirectedGraph<Point, DefaultWeightedEdge> g =
            new SimpleDirectedGraph<Point, DefaultWeightedEdge>(DefaultWeightedEdge.class);

    public boolean isLeaf(Point p){
        Set<DefaultWeightedEdge> edges= g.edgesOf(p);
        if(g.containsVertex(p)){
            if(edges.isEmpty()) return true;
        }
        return false;
    }

    public int getDegreeOf(Point p){
        return g.inDegreeOf(p);
    }

    public Set<DefaultWeightedEdge> getEdgesOf(Point p){
        return g.edgesOf(p);
    }

    public Set<Point> getPointsOf(Point p){
        Set<Point> setPoints= new HashSet<>();
        for (DefaultWeightedEdge e: getEdgesOf(p)) {
            setPoints.add(g.getEdgeTarget(e));
        }
        return setPoints;
    }

    public Set<Point> getAllPoints(){
        return g.vertexSet();
    }

    public void setPoints(){
        for (Point p: getAllPoints()) {

        }
    }
}
