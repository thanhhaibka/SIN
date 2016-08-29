package app;

import Data.Car;
import cluster.Kmean;
import createData.CreateCar;
import graph.model.DijkstraAlgorithm;
import graph.model.Graph2;
import org.ajwerner.voronoi.Point;
import org.ajwerner.voronoi.Voronoi;

import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prnc
 */
public class Chromosome {
    public ArrayList<Point> points;
    public Point baseStation;
    public List<Car> cars;
    public static Voronoi voronoi;
    public Graph2 graph;
    public Kmean kmean;
    public static final double R = 8;
    public DijkstraAlgorithm dijkstraAlgorithm;

    public Chromosome(Point baseStation){
        cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Car car = new Car(i + "");
            car.setCars(CreateCar.createCar(24));
            cars.add(car);
        }
        this.baseStation = baseStation;
    }

    public void setVornonoi(){
        for (Car c : cars) {
            Set<Data.Point> p= c.getAll();
            List<Point> points= new ArrayList<>();
            for(Data.Point p1: p){
                points.add(new Point(p1.x, p1.y));
            }
            points.addAll(points);
        }
        Voronoi voronoi= new Voronoi(points, true);
    }
}
