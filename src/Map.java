
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prnc
 */
public class Map {
    public ArrayList<Road> roads= new ArrayList<Road>();
    public ArrayList cars= new ArrayList<Car>();
    public ArrayList s= new ArrayList<Point>();
    public double W;
    public double H;
    public double N;    //number of sensors node
    
    public Map(){
        
    }
    
    public Map(double W, double H){
        this.W=W;
        this.H=H;
//        setCars("");
        setRoads("/media/pc/DATA/SIN/resources/roads.txt");
        initS();
    }
    
    public void setRoads(String fileName){
        try{
            FileReader fr = new FileReader(fileName);
            double xMin, yMin, xMax, yMax;
            Scanner sc = new Scanner(fr);
            while (sc.hasNextDouble()) {
                xMin = sc.nextDouble();
                yMin = sc.nextDouble();
                xMax = sc.nextDouble();
                yMax = sc.nextDouble();
                roads.add(new Road(xMin, yMin, xMax, yMax));
            }
        }catch(Exception e){
            
        }
    }
    
//    public void setCars(String fileName){
//        try{
//            FileReader fr = new FileReader(fileName);
//            double x, y,r, v, tStart, tEnd;
//            Director d= new Director();
//            Scanner sc = new Scanner(fr);
//            while (sc.hasNextDouble()) {
//                x = sc.nextDouble();
//                y = sc.nextDouble();
//                r = sc.nextDouble();
//                d.x = sc.nextInt();
//                d.y = sc.nextInt();
//                v = sc.nextDouble();
//                tStart=sc.nextDouble();
//                tEnd=sc.nextDouble();
//                roads.add(new Car(x, y, r, v, d, tStart, tEnd));
//            }
//        }catch(Exception e){
//
//        }
//    }
    
    public void initS(){
        for(Road r: roads){
            s.add(r.lt);
            s.add(r.rt);
            s.add(r.lb);
            s.add(r.rb);
        }
    }
    
    public void sortS(){    // sap xep theo do uu tien
        for(int i=0; i<s.size()-1; i++){
            for(int j=0; j<s.size(); j++){
                Point x= (Point)s.get(i);
                Point y= (Point)s.get(j);
                if(x.standard()>y.standard()){
                    s.set(i, y);
                    s.set(j, x);
                }
            }
        }
    }
}
