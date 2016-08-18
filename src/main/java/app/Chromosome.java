package app;

import Data.Car;
import Data.MapCreater;
import Data.Point;
import cluster.Cluster;
import cluster.Kmean;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

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
    public final int N=100;
    public ArrayList<Sensor> chroms;
    MapCreater map;
    public final int K=24;
    public final long T=0;
    UndirectedGraph<Point, DefaultWeightedEdge> graph;
    
    public Chromosome(MapCreater map){
        this.map=map;
    }
    
    public int getSize(){
        return chroms.size();
    }
    
    public Chromosome(boolean flag, MapCreater map){
        this.map= map;
        graph= new SimpleWeightedGraph<Point, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }

    public void greedyInit(){
        int numOfSensor=0;
        double x=0, y=0, z=0;
        while(numOfSensor<=N){
            Set<Point> points= map.road.getAllPoints();
            for(Point p: points){

            }
        }
    }


    public void setGraph(){
        Kmean kmean= new Kmean(map.targets);
        Set<Car> cars= map.cars;
        List<Cluster> clusters= kmean.getClusters();
        List<Point> points= kmean.getCentrePoints();
        for(int i=0;i<K*T; i+=T){
            UndirectedGraph<Point, DefaultWeightedEdge> g= new SimpleWeightedGraph<Point, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            List<Point> mPoints= new ArrayList<>();
            for (Car c: cars) {
                mPoints.add(c.getCar(i));
            }
            mPoints= merge(points, mPoints);
            for(int var1=0; var1<mPoints.size(); var1++){
                for(int var2=0; var2<var1; var2++){

                }
            }
        }
    }

    public List<Point> merge(List<Point> var1, List<Point> var2){
        List<Point> temp= new ArrayList<>();
        temp.addAll(var1);
        temp.addAll(var2);
        return temp;
    }
    
    public void randomInit(){
        
    }

    public Chromosome neighbor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getFit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void adjust(){
        
    }
    
    private double randomRadius(double r1, double r2, double r3){
        Random rd= new Random();
        double x= rd.nextDouble()*1;
        if(x<=1/3) return r1;
        else if(1/3<x&&x<=2/3) return r2;
        else return r3;
    }
}
