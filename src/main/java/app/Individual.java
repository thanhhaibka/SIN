package app;

import Data.Car;
import Data.Point;
import cluster.Cluster;
import cluster.Kmean;
import createData.CreateCar;
import draw.Draw;
import edu.princeton.cs.introcs.StdDraw;
import graph.model.DijkstraAlgorithm;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Vertex;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by prnc on 19/08/2016.
 */
public class Individual {
    public List<Point> points;
    public Point baseStation;
    public List<Car> cars;
    public Graph graph;
    public static final double R=4;
    public DijkstraAlgorithm dijkstraAlgorithm;

    public Individual(Point baseStation){
        cars= new ArrayList<>();
        for(int i=0; i<20; i++){
            Car car= new Car(i+"");
            car.setCars(CreateCar.createCar(24));
            cars.add(car);
        }
        this.baseStation= baseStation;
    }

    public Individual(Point baseStation, List<Car> cars) {
        this.baseStation = baseStation;
        this.cars = cars;
    }

    public void setGraph(List<Car> cars){
        List<Vertex> nodes= new ArrayList<>();
        List<Edge> edges =new ArrayList<>();
        Set<Point> points= new HashSet<>();
        for (Car c:cars) {
            points.addAll(c.getAll());
        }
        List<Point> p= new ArrayList<>();
        for (Point point:points) {
            p.add(point);
        }
        this.points=p;
        Cluster var= new Cluster(-1);
        var.setCentrePoint(baseStation);
        List<Point> var1= new ArrayList<>();
        var1.add(baseStation);
        var.setPoints(var1);
//        List<Kmean> kmeanList= new ArrayList<>();
//        for(int i=2; i<points.size()/2; i++){
//            Kmean kmean= new Kmean(p, i);
//            kmeanList.add(kmean);
//        }
//        Collections.sort(kmeanList);
//        Kmean kmean= kmeanList.get(1);
//        for(int i=0; i<kmeanList.size(); i++){
//            System.out.print(kmeanList.get(i).getNUM_CLUSTERS()+ " ");
//        }
//
        Kmean kmean= new Kmean(p, (int)Math.round(p.size()/10));
        List<Cluster> clusters= kmean.getClusters();
        clusters.add(var);
        for(int i=0; i<clusters.size(); i++){
            Vertex location= new Vertex(clusters.get(i).getClusterNumber()+"", clusters.get(i).getClusterNumber()+"", clusters.get(i));
            nodes.add(location);
        }

        for (int i=0; i<nodes.size(); i++){
            for(int j=0; j<nodes.size(); j++){
                nodes.get(i);
                nodes.get(j);
                Double d= nodes.get(i).getDistance(nodes.get(j))/R;
                int temp= round(d);
                edges.add(new Edge(i+" "+j, nodes.get(i), nodes.get(j), temp));
            }
        }

        Graph g= new Graph(nodes, edges);
        this.graph= g;
        DijkstraAlgorithm dijkstra= new DijkstraAlgorithm(g);
        dijkstra.execute(nodes.get(nodes.size()-1));
        dijkstraAlgorithm= dijkstra;
//        for(int i=0; i<nodes.size()-1; i++){
//            LinkedList<Vertex> path = dijkstra.getPath(nodes.get(i));
//            System.err.println(i+" "+ dijkstra.getPath(path));
//            for (Vertex vertex : path) {
//                System.out.print(vertex+" ");
//            }
//            System.out.println();
//        }
    }

    private int round(double d){
        Long var1= Math.round(d);
        return  var1.intValue();
    }

    public static void main(String args[]){
        Point p= new Point(100, 100);
        Individual individual= new Individual(p);
        individual.setGraph(individual.cars);
    }
}
