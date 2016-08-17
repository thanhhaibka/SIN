package Data;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author prnc
 */
public class Point implements Comparable<Point>{
    public double x;
    public double y;
    public int clusterNumber;

    public static Point createRandomPoint(int max, int min){
        Random r = new Random();
        Double x = max + (min - max) * r.nextDouble();
        Double y = min + (max - min) * r.nextDouble();
        return new Point(x,y);
    }
    
    public Point(double x, double y){
        this.x= x;
        this.y= y;
    }
    
    public double standard(){
        double s= 0;
        s= Math.pow((this.x- 50),2) + Math.pow((this.y-50),2);
        return Math.sqrt(s);
    }
    
    public boolean isGreater(Point p){
        return this.standard()>=p.standard();
    }

    public static Double getDistance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public int getClusterNumber() {
        return clusterNumber;
    }
    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    @Override
    public int compareTo(Point o) {
        return this.standard()> o.standard()?1:(this.standard()==o.standard()?0:-1);
    }
}
