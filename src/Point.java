/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prnc
 */
public class Point {
    public double x;
    public double y;
    
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
}
