/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prnc
 */
public class Car extends Sensor{
    public double v;
    public Director d;
    public double tStart;
    public double tEnd;
    
    public Car(double x, double y, double r, double v, Director d, double tStart, double tEnd){
        this.x=x;
        this.y=y;
        this.d=d;
        this.r=r;
        this.tEnd=tEnd;
        this.tStart=tStart;
        this.v=v;
    }
}
