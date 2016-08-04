
import java.util.ArrayList;
import java.util.Random;

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
    public ArrayList chroms= new ArrayList<Sensor>();
    Map map;
    
    public Chromosome(Map map){
        this.map=map;
    }
    
    public int getSize(){
        return chroms.size();
    }
    
    public Chromosome(boolean flag){
        if(flag) greedyInit1();
        else randomInit();
    }
    
    public void greedyInit2(int maxNumber, double r1){  //init theo roads
        ArrayList s= map.s;
        double x=0, y=0, z=0;
        int numOfSensor=1;
        while(!s.isEmpty()&&numOfSensor<=maxNumber){
            Point p=(Point)s.get(maxNumber);
            chroms.add(new Sensor(p.x, p.y, r1));
        }
    }
    
    public void greedyInit1(){  //init theo cars
        ArrayList cars= map.cars;
        ArrayList roads= map.roads;
        double x=0,y=0, r=0;
        while(!cars.isEmpty()&&chroms.size()<map.N){
            Sensor sensor= new Sensor();
            r=sensor.r;
            int i=0;
            Car car= (Car)cars.get(i);
            Sensor s= new Sensor(car.x+car.d.x*car.v*(car.tEnd-car.tStart), car.y+car.d.y*car.v*(car.tEnd-car.tStart), r);
            while(!s.flag){
                if(i==0){
                    x=car.x;
                    y=car.y;
                }else{
                   sensor= (Sensor)chroms.get(i-1);
//                   Director d1= new Director(1, 0); //xe di ngang sang phai
//                   Director d2= new Director(0,-1); //xe di doc len tren
//                   Director d3= new Director(-1, 0);//xe di ngang sang trai    
//                   Director d4= new Director(0, 1);//xe di doc xuong duoi
                   x=car.x+car.d.x*1.73*r;
                   y=car.y+car.d.y*1.73*r;
                }
                chroms.add(new Sensor(x, y, r));
            }
        }
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
