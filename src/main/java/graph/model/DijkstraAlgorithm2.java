package graph.model;

import java.util.*;

/**
 * Created by prnc on 19/08/2016.
 */
public class DijkstraAlgorithm2 {

    private final List<Vertex2> nodes;
    private final List<Edge2> Edges;
    private Set<Vertex2> settledNodes;
    private Set<Vertex2> unSettledNodes;
    private Map<Vertex2, Vertex2> predecessors;
    private Map<Vertex2, Integer> distance;

    public DijkstraAlgorithm2(Graph2 Graph2) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex2>(Graph2.getVertexes());
        this.Edges = new ArrayList<Edge2>(Graph2.getEdges());
    }

    public void execute(Vertex2 source) {
        settledNodes = new HashSet<Vertex2>();
        unSettledNodes = new HashSet<Vertex2>();
        distance = new HashMap<Vertex2, Integer>();
        predecessors = new HashMap<Vertex2, Vertex2>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex2 node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex2 node) {
        List<Vertex2> adjacentNodes = getNeighbors(node);
        for (Vertex2 target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex2 node, Vertex2 target) {
        for (Edge2 Edge2 : Edges) {
            if (Edge2.getSource().equals(node)
                    && Edge2.getDestination().equals(target)) {
                return Edge2.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex2> getNeighbors(Vertex2 node) {
        List<Vertex2> neighbors = new ArrayList<Vertex2>();
        for (Edge2 Edge2 : Edges) {
            if (Edge2.getSource().equals(node)
                    && !isSettled(Edge2.getDestination())) {
                neighbors.add(Edge2.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex2 getMinimum(Set<Vertex2> Vertex2es) {
        Vertex2 minimum = null;
        for (Vertex2 Vertex2 : Vertex2es) {
            if (minimum == null) {
                minimum = Vertex2;
            } else {
                if (getShortestDistance(Vertex2) < getShortestDistance(minimum)) {
                    minimum = Vertex2;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex2 Vertex2) {
        return settledNodes.contains(Vertex2);
    }

    private int getShortestDistance(Vertex2 destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Vertex2> getPath(Vertex2 target) {
        LinkedList<Vertex2> path = new LinkedList<Vertex2>();
        Vertex2 step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    public int getPath(LinkedList<Vertex2> path) {
        int sum=0;
        if(path==null) return 0;
        for(int i=0; i<path.size()-1; i++){
            sum+=getDistance(path.get(i), path.get(i+1));
        }
        return sum;
    }

}
