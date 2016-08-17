package Data;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prnc
 */
public class MapCreater {
    public static Road road;
    public static Set<Point> targets;
    public Set<Car> cars;
    public ArrayList s= new ArrayList<Point>();
    public double W;
    public double H;
    public double N;    //number of sensors node
    
    public MapCreater(){
        
    }
    
    public MapCreater(double W, double H){
        this.W=W;
        this.H=H;
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
