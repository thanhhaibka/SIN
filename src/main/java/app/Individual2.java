package app;

/**
 * Created by prnc on 20/08/2016.
 */

import Data.Car;
import Data.Point;
import createData.CreateCar;
import graph.model.*;
import voronoi.model.*;

import java.util.*;

public class Individual2 {
    public Point baseStation;
    public List<Car> cars;
    public Graph graph;
    public final double R=4;

    public Individual2(Point baseStation){
        cars= new ArrayList<>();
        for(int i=0; i<10; i++){
            Car car= new Car(i+"");
            car.setCars(CreateCar.createCar(24));
            cars.add(car);
        }
        this.baseStation= baseStation;
    }

    public Individual2(Point baseStation, List<Car> cars) {
        this.baseStation = baseStation;
        this.cars = cars;
    }

    public List<voronoi.model.Edge> getVertexVoronoi(List<Car> cars){
        List<Vertex2> nodes= new ArrayList<>();
        List<Edge2> edges =new ArrayList<>();
        Set<Point> points= new HashSet<>();
        for (Car c:cars) {
            points.addAll(c.getAll());
        }
        List<Point> p= new ArrayList<>();
        for (Point point:points) {
            p.add(point);
        }
        p.add(baseStation);
        Voronoi voronoi= new Voronoi(p);
        return voronoi.edges;
    }

    public void setGraph(List<Car> cars){
        List<Vertex2> nodes= new ArrayList<>();
        List<Edge2> edges =new ArrayList<>();
        Set<Point> points= new HashSet<>();
        for (Car c:cars) {
            points.addAll(c.getAll());
        }
        List<Point> p= new ArrayList<>();
        for (Point point:points) {
            p.add(point);
        }
        p.add(baseStation);
        Voronoi voronoi= new Voronoi(p);
        for(int i=0; i<voronoi.sites.size(); i++){
            Vertex2 location= new Vertex2(i+"",
                    i+"", voronoi.sites.get(i));
            nodes.add(location);
        }

        for (int i=0; i<nodes.size(); i++){
            for(int j=0; j<nodes.size(); j++){
                nodes.get(i);
                nodes.get(j);
                Double d= Point.getDistance(nodes.get(i),nodes.get(j))/R;
                int temp= round(d);
                edges.add(new Edge2(i+" "+j, nodes.get(i), nodes.get(j), temp));
            }
        }

        Graph2 g= new Graph2(nodes, edges);
        DijkstraAlgorithm2 dijkstra= new DijkstraAlgorithm2(g);
        dijkstra.execute(nodes.get(nodes.size()-1));
        for(int i=0; i<nodes.size()-1; i++){
            LinkedList<Vertex2> path = dijkstra.getPath(nodes.get(i));
            System.err.println(i+" "+ dijkstra.getPath(path));
            for (Vertex2 vertex : path) {
                System.out.print(vertex+" ");
            }
            System.out.println();
        }
    }

    private int round(double d){
        Long var1= Math.round(d);
        return  var1.intValue();
    }

    public static void main(String args[]){
        Point p= new Point(100, 100);
        Individual2 individual= new Individual2(p);
        individual.setGraph(individual.cars);
    }
}

