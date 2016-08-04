/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prnc
 */
public class Road {
    public double xMin;
    public double yMin;
    public double xMax;
    public double yMax;
    
    public Point lt;
    public Point rt;
    public Point lb;
    public Point rb;
    
    public Road(double xMin, double yMin, double xMax, double yMax){
        this.xMax=xMax;
        this.xMin=xMin;
        this.yMax=yMax;
        this.yMin=yMin;
        lt= new Point(xMin, yMin);
        rt= new Point(xMin, yMax);
        lt= new Point(xMax, yMin);
        lt= new Point(xMax, yMax);
    }
}
