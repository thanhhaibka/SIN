package app;

import Data.MapCreater;
import Data.Point;

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
    
    public Chromosome(MapCreater map){
        this.map=map;
    }
    
    public int getSize(){
        return chroms.size();
    }
    
    public Chromosome(boolean flag, MapCreater map){
        this.map= map;
//        if(flag) greedyInit(100);
//        else randomInit();
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
    
//    public void greedyInit2(int maxNumber){  //init theo roads
//        ArrayList<Data.Point> s= map.s;
//        double x=0, y=0, z=0;
//        int numOfSensor=0;
//        while(numOfSensor<=maxNumber){
//            Data.Point p= s.get(numOfSensor);
//            chroms.add(new Sensor(p.x, p.y, randomRadius(8, 10, 12)));
//            s.add(new Data.Point(p.x+1.44*8, p.y));
//            s.add(new Data.Point(p.x-1.44*8, p.y));
//            s.add(new Data.Point(p.x, p.y+1.44*8));
//            s.add(new Data.Point(p.x, p.y-1.44*8));
//            s.remove(p);
//            Collections.sort(s);
//            numOfSensor++;
//        }
//    }
    
//    public void greedyInit1(){  //init theo cars
//        ArrayList cars= map.cars;
//        ArrayList roads= map.roads;
//        double x=0,y=0, r=0;
//        while(!cars.isEmpty()&&chroms.size()<map.N){
//            Sensor sensor= new Sensor();
//            r=sensor.r;
//            int i=0;
//            Car car= (Car)cars.get(i);
////
////            while(!s.flag){
////                if(i==0){
////                    x=car.x;
////                    y=car.y;
////                }else{
////                   sensor= (app.Sensor)chroms.get(i-1);
//////                   Director d1= new Director(1, 0); //xe di ngang sang phai
//////                   Director d2= new Director(0,-1); //xe di doc len tren
//////                   Director d3= new Director(-1, 0);//xe di ngang sang trai
//////                   Director d4= new Director(0, 1);//xe di doc xuong duoi
////                   x=car.x+car.d.x*1.73*r;
////                   y=car.y+car.d.y*1.73*r;
////                }
////                chroms.add(new app.Sensor(x, y, r));
////            }
//        }
//    }
    
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
