package createData;

import Data.Car;
import Data.CarInTime;
import Data.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by prnc on 20/08/2016.
 */
public class CreateCar {
    public static final int num = 10;
    public static final double v = 15;
    public static final Point[] direc = {new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1),
            new Point(-1, -1), new Point(1, 1), new Point(1, -1), new Point(-1, 1)
    };

    public static List<CarInTime> createCar(int T) {
        Random rd = new Random();
        List<CarInTime> carInTimes = new ArrayList<>();
        double x = rd.nextDouble() * 100;
        double y = rd.nextDouble() * 100;
        Point p = new Point(x, y);

        carInTimes.add(new CarInTime(x, y, 0));
        for (int i = 1; i < T; i++) {
            p = create(p);
            carInTimes.add(new CarInTime(p.x, p.y, i));
        }
        return carInTimes;
    }

    public static Point create(Point p) {
        Point point = p;
        Random rd = new Random();
        int r = rd.nextInt(8);
        if (r == 0) {
            point.x -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 1) {
            point.x += v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 2) {
            point.y -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 3) {
            point.y += v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 4) {
            point.x -= v * (rd.nextFloat() * 0.5 + 0.5);
            point.y -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 5) {
            point.x += v * (rd.nextFloat() * 0.5 + 0.5);
            point.y += v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 6) {
            point.x += v * (rd.nextFloat() * 0.5 + 0.5);
            point.y -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else {
            point.x -= v * (rd.nextFloat() * 0.5 + 0.5);
            point.y += v * (rd.nextFloat() * 0.5 + 0.5);
        }
        adjust(point);
        return point;
    }

    public static void adjust(Point point) {
        if (point.x < 0) {
            point.x = 0;
        } else if (point.x > 100) {
            point.x = 100;
        }
        if (point.y < 0) {
            point.y = 0;
        } else if (point.y > 100) {
            point.y = 100;
        }
    }
}
