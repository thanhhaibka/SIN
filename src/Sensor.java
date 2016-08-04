/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prnc
 */
public class Sensor {
   public double x;
   public double y;
   public double r;
   public boolean flag;
   
   public Sensor(){
       flag=false;
   }
   
   public Sensor(double x, double y, double r){
       this.x=x;
       this.y=y;
       this.r=r;
       flag=false;
   }
   
   public double dis(Sensor s){
       return Math.sqrt(Math.pow(x-s.x,2)+Math.pow(y-s.y,2));
   }
   
   public boolean isConnect(Sensor s){
       if(this.dis(s)<=2*r){
            return true;
       }
       else 
            return false;
   }
}
