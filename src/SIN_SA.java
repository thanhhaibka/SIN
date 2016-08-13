
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
public class SIN_SA {
    
    public static void main(String args[]){
        Map map= new Map(100, 100);
        map.sortS();
//        Chromosome ch1= new Chromosome(map);
//        Chromosome ch2= ch1;
//        double alpha=0.95;
//        for(int T=1000; T>=0.00001; T*=alpha){
//            Random rd = new Random();
//            ch2= ch1.neighbor();
//            double t1, t2, t3;
//            t1= ch1.getFit();
//            t2=ch2.getFit();
//            t3= rd.nextDouble()* 1;
//            if (t2 > t1 || t3 <= Math.exp(-Math.abs(t2 - t1) / T)) {
//                ch1 = ch2;
//                t1 = t2;
//            }
//            System.out.print(t1+" ");
//        }
    }
}
