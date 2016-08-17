import Data.Point;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by prnc on 13/08/2016.
 */
public class Schedule {
    public ArrayList<Car> carSchedule;

    public ArrayList<Car> getCarSchedule() {
        return carSchedule;
    }

    public void setCarSchedule(String fileName) {
        try{
            FileReader fr = new FileReader(fileName);
            Scanner sc = new Scanner(fr);
            double time, carID, x, y, r;
            while (sc.hasNextDouble()) {
                carID = sc.nextDouble();
                x = sc.nextDouble();
                y = sc.nextDouble();
                r = sc.nextDouble();
                time= sc.nextDouble();
                carSchedule.add(new Car(carID, x, y, r, time));
            }
        }catch (Exception e){

        }
    }

    public Point getPos(double time){
        Point point= null;
        for (Car c:
             carSchedule) {
            if(c.time==time){
                point= new Point(c.x, c.y);
                break;
            }
        }
        return point;
    }

    public void add(Car car){
        this.carSchedule.add(car);
    }
}