package app;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.*;
import javafx.scene.shape.Rectangle;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

/**
 * @author prnc
 */
public class Sensor {
    public double x;
    public double y;
    public double r;
    public boolean flag;

    public Sensor() {
        flag = false;
    }

    public Sensor(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        flag = false;
    }

    public boolean isAvailable(MapCreater map) {
        Road road = map.road;
        boolean flag = true;
        for (Point point : road.getAllPoints()) {
            if (road.isLeaf(point)) {
                //Do nothing
            } else {
                for(Point p: road.getPointsOf(point)){
                    if(isIn(road, point, p)) return false;
                }
            }
        }
        return true;
    }

    public boolean isIn(Road road, Point var1, Point var2) {
        if (road.isLeaf(var1)) {
            if (road.isLeaf(var2)) {
                if (isInRoad(var1, var2)) {
                    return true;
                }
            } else {
                if (isInRoad(var1, var2)) {
                    return true;
                }
                if (isInRect(new Rectangle(var2.x - 4, var2.y - 4, 8, 8))) return true;
            }
        } else {
            if (road.isLeaf(var2)) {
                if (isInRoad(var1, var2)) {
                    return true;
                }
                if (isInRect(new Rectangle(var1.x - 4, var1.y - 4, 8, 8))) return true;
            } else {
                if (isInRoad(var1, var2)) {
                    return true;
                }
                if (isInRect(new Rectangle(var2.x - 4, var2.y - 4, 8, 8))) return true;
                if (isInRect(new Rectangle(var2.x - 4, var2.y - 4, 8, 8))) return true;
            }
        }
        return false;
    }

    public boolean isInRect(Rectangle rectangle) {
        if (rectangle.contains(x, y)) {
            return true;
        }
        return false;
    }

    public boolean isInRoad(Point v1, Point v2) {
        if (v1.x == v2.x) {
            double xMin = Math.min(v1.x, v2.x);
            double yMin = Math.min(v1.y - 4, v2.y - 4);
            double xMax = Math.max(v1.x, v2.x);
            double yMax = Math.max(v1.y + 4, v2.y + 4);
            return (x > xMin && x < xMax && y > yMin && y < yMax);
        } else {
            double xMin = Math.min(v1.x - 4, v2.x - 4);
            double yMin = Math.min(v1.y, v2.y);
            double xMax = Math.max(v1.x + 4, v2.x + 4);
            double yMax = Math.max(v1.y, v2.y);
            return (x > xMin && x < xMax && y > yMin && y < yMax);
        }
    }

    public double dis(Sensor s) {
        return Math.sqrt(Math.pow(x - s.x, 2) + Math.pow(y - s.y, 2));
    }

    public boolean isConnect(Sensor s) {
        if (this.dis(s) <= 2 * r) {
            return true;
        } else
            return false;
    }
}
