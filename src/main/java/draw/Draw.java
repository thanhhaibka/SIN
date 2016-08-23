package draw;

import Data.*;
import Data.Point;
import Data.Vertex;
import app.Individual;
import app.Individual2;
import edu.princeton.cs.introcs.In;
import graph.model.*;
import voronoi.model.Edge;
import voronoi.model.Voronoi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

/**
 * Created by prnc on 20/08/2016.
 */
public class Draw extends JFrame {
    private static Individual individual;
    private static Point p;
    private int sum=0;
    private static List<Point> points= new ArrayList<>();

    public Draw() {
        setTitle("Drawing");
        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 100, 100);
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        this.setSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setBackground(Color.GREEN);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        drawArea(g);
        p = Point.createRandomPoint(100, 0);
        individual = new Individual(p);
        individual.setGraph(individual.cars);
        drawPath(g, individual);
        drawBase(g, individual);
        drawPoints(g, individual);
        g.setColor(Color.yellow);
        drawInCluster(g, individual);
        g.setColor(Color.RED);

        System.out.println(points.size());
    }

    public void drawBase(Graphics g, Individual individual) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.fill(new Ellipse2D.Double(p.x * 5 - Individual.R*5, p.y * 5 - Individual.R*5, Individual.R*10, Individual.R*10));
        g2d.setColor(Color.BLACK);
        g2d.draw(new Ellipse2D.Double(p.x * 5 - Individual.R*5, p.y * 5 - Individual.R*5, Individual.R*10, Individual.R*10));
    }

    public void drawPoints(Graphics g, Individual individual) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        for (Point p : individual.points) {
            g2d.fill(new Ellipse2D.Double(p.x * 5 - 2, p.y * 5 - 2, 4, 4));
        }
    }

    public void drawPoints(Graphics g, List<Point> points) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(153, 255, 255));
        for (Point p : points) {
            g2d.fill(new Ellipse2D.Double(p.x * 5 - Individual.R*5, p.y * 5 - Individual.R*5, Individual.R*10, Individual.R*10));
        }
        g2d.setColor(Color.BLACK);
        for (Point p : points) {
            g2d.draw(new Ellipse2D.Double(p.x * 5 - Individual.R*5, p.y * 5 - Individual.R*5, Individual.R*10, Individual.R*10));
        }
    }

    public void drawPath(Graphics g, Individual individual) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < individual.graph.getVertexes().size() - 1; i++) {
            LinkedList<graph.model.Vertex> path = individual.dijkstraAlgorithm.getPath(individual.graph.getVertexes().get(i));
//            System.err.println(i+" "+ individual.dijkstraAlgorithm.getPath(path));
//            System.out.println(path.size());
            for (int j = 0; j < path.size() - 1; j++) {
                graph.model.Vertex vertex = path.get(j);
                graph.model.Vertex vertex2 = path.get(j + 1);
                Point p1, p2;
                p1 = new Point(vertex.getCentrePoint().x, vertex.getCentrePoint().y);
                p2 = new Point(vertex2.getCentrePoint().x, vertex2.getCentrePoint().y);
//                g2d.draw(new Line2D.Double(p1.x * 5, p1.y * 5, p2.x * 5, p2.y * 5));
                points.addAll(drawAPath2(p1, p2));
            }
        }
        Random rd= new Random();
        List<Integer> list= new ArrayList<>();
        List<Point> pointList= new ArrayList<>(points);
        for(int i=0; i<pointList.size(); i++){
            for(int j=0; j<=i; j++){
                if(i!=j){
                    if(Point.getDistance(pointList.get(i), pointList.get(j))<Individual.R){
                        if(rd.nextDouble()<0.5){
                            list.add(i);
                        }else{
                            list.add(j);
                        }
                    }
                }
            }
        }
        for(int i=0; i<list.size(); i++){
            points.remove((Integer)list.get(i));
        }
        list.clear();
        for(int i=pointList.size()-1; i>=0; i--){
            for(int j=pointList.size()-1; j>=i; j--){
                if(i!=j){
                    if(Point.getDistance(pointList.get(i), pointList.get(j))<Individual.R){
                        if(rd.nextDouble()<0.5){
                            list.add(i);
                        }else{
                            list.add(j);
                        }
                    }
                }
            }
        }
        for(int i=0; i<list.size(); i++){
            points.remove((Integer)list.get(i));
        }
        drawPoints(g, points);
    }

    public void drawInCluster(Graphics g, Individual individual) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < individual.kmean.getNUM_CLUSTERS(); i++) {
            List<Point> points = individual.kmean.getClusters().get(i).getPoints();
            Point p = individual.kmean.getClusters().get(i).getCentrePoint();
            for (int j = 0; j < points.size(); j++) {
                g2d.setColor(Color.RED);
                drawAPath(g2d, points.get(j), p);
                g2d.setColor(Color.YELLOW);
//                fillAPath(g2d, points.get(j), p);
//                g2d.setColor(Color.BLACK);
//                g2d.draw(new Line2D.Double(p.x * 5, p.y * 5, points.get(j).x * 5, points.get(j).y * 5));
            }
        }
    }

    public List<Point> fillAPath(Graphics2D g, Point var1, Point var2) {
        List<Point> points= new ArrayList<>();
        double r = Individual.R;
        double dx = var2.x - var1.x;
        double dy = var2.y - var1.y;
        Double d = Point.getDistance(var1, var2) / (r);

        int temp = (int) Math.floor(d);
        if (d != temp) temp += 1;
        if ((var2.x - var1.x) == 0) {
            for (int i = 0; i < temp; i++) {
                if (dy > 0) {
                    g.fill(new Ellipse2D.Double((var1.x) * 5, (var1.y + Math.abs((i) * r)-r) * 5, 10 * r, 10 * r));
                    points.add(new Point(var1.x, var1.y + Math.abs((i) * r)));
                }else{
                    g.fill(new Ellipse2D.Double((var1.x) * 5, (var1.y - Math.abs((i) * r)-r) * 5, 10 * r, 10 * r));
                    points.add(new Point(var1.x, var1.y - Math.abs((i) * r)));
                }
                sum+=1;
            }
        } else {
            double tan = (var2.y - var1.y) / (var2.x - var1.x);
            double atan = Math.atan(tan);
            for (int i = 0; i < temp; i++) {
                if (dx > 0 && dy > 0) {
                    g.fill(new Ellipse2D.Double((var1.x + Math.abs(((i) * r ) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) *r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                    points.add(new Point(var1.x + Math.abs(((i) * r* Math.cos(atan))), var1.y + Math.abs(((i) *r ) * Math.sin(atan))));
                } else if (dx > 0 && dy < 0) {
                    points.add(new Point(var1.x + Math.abs(((i) * r* Math.cos(atan))), var1.y - Math.abs(((i) *r ) * Math.sin(atan))));
                    g.fill(new Ellipse2D.Double((var1.x + Math.abs(((i) * r) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) *  r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx < 0 && dy < 0) {
                    points.add(new Point(var1.x - Math.abs(((i) * r* Math.cos(atan))), var1.y - Math.abs(((i) *r ) * Math.sin(atan))));
                    g.fill(new Ellipse2D.Double((var1.x - Math.abs(((i) *r ) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) *  r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx < 0 && dy > 0) {
                    points.add(new Point(var1.x - Math.abs(((i) * r* Math.cos(atan))), var1.y + Math.abs(((i) *r ) * Math.sin(atan))));
                    g.fill(new Ellipse2D.Double((var1.x - Math.abs(((i) *  r ) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) * r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                }
                sum+=1;
            }
        }
        return points;
    }

    public List<Point> drawAPath(Graphics2D g, Point var1, Point var2) {
        List<Point> points= new ArrayList<>();
        double r = Individual.R;
        double dx = var2.x - var1.x;
        double dy = var2.y - var1.y;
        Double d = Point.getDistance(var1, var2) / (r);

        int temp = (int) Math.floor(d);
        if (d != temp) temp += 1;
        if ((var2.x - var1.x) == 0) {
            for (int i = 0; i < temp; i++) {
                if (dy > 0) {
                    g.draw(new Ellipse2D.Double((var1.x) * 5, (var1.y + Math.abs((i) * r)-r) * 5, 10 * r, 10 * r));
                    points.add(new Point(var1.x, var1.y + Math.abs((i) * r)));
                }else{
                    g.draw(new Ellipse2D.Double((var1.x) * 5, (var1.y - Math.abs((i) * r)-r) * 5, 10 * r, 10 * r));
                    points.add(new Point(var1.x, var1.y - Math.abs((i) * r)));
                }
                sum+=1;
            }
        } else {
            double tan = (var2.y - var1.y) / (var2.x - var1.x);
            double atan = Math.atan(tan);
            for (int i = 0; i < temp; i++) {
                if (dx > 0 && dy > 0) {
                    g.draw(new Ellipse2D.Double((var1.x + Math.abs(((i) * r ) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) *r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                    points.add(new Point(var1.x + Math.abs(((i) * r* Math.cos(atan))), var1.y + Math.abs(((i) *r ) * Math.sin(atan))));
                } else if (dx > 0 && dy < 0) {
                    points.add(new Point(var1.x + Math.abs(((i) * r* Math.cos(atan))), var1.y - Math.abs(((i) *r ) * Math.sin(atan))));
                    g.draw(new Ellipse2D.Double((var1.x + Math.abs(((i) * r) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) *  r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx < 0 && dy < 0) {
                    points.add(new Point(var1.x - Math.abs(((i) * r* Math.cos(atan))), var1.y - Math.abs(((i) *r ) * Math.sin(atan))));
                    g.draw(new Ellipse2D.Double((var1.x - Math.abs(((i) *r ) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) *  r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx < 0 && dy > 0) {
                    points.add(new Point(var1.x - Math.abs(((i) * r* Math.cos(atan))), var1.y + Math.abs(((i) *r ) * Math.sin(atan))));
                    g.draw(new Ellipse2D.Double((var1.x - Math.abs(((i) *  r ) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) * r ) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                }
                sum+=1;
            }
        }
        return points;
    }

    public List<Point> drawAPath2(Point var1, Point var2) {
        List<Point> points= new ArrayList<>();
        double r = Individual.R;
        double dx = var2.x - var1.x;
        double dy = var2.y - var1.y;
        Double d = Point.getDistance(var1, var2) / (r);

        int temp = (int) Math.floor(d);
        if (d != temp) temp += 1;
        if ((var2.x - var1.x) == 0) {
            for (int i = 0; i < temp; i++) {
                if (dy > 0) {
                    points.add(new Point(var1.x, var1.y + Math.abs((i) * r)));
//                    g.draw(new Ellipse2D.Double((var1.x) * 5, (var1.y + Math.abs((i) * r+r)-r) * 5, 10 * r, 10 * r));
                }else{
                    points.add(new Point(var1.x, var1.y - Math.abs((i) * r)));
//                    g.draw(new Ellipse2D.Double((var1.x) * 5, (var1.y - Math.abs((i) *  r+r)-r) * 5, 10 * r, 10 * r));
                }
                sum+=1;
            }
        } else {
            double tan = (var2.y - var1.y) / (var2.x - var1.x);
            double atan = Math.atan(tan);
            for (int i = 0; i < temp; i++) {
                if (dx > 0 && dy > 0) {
                    points.add(new Point(var1.x + Math.abs((i * r+r)* Math.cos(atan)), var1.y + Math.abs((i+1) *r  * Math.sin(atan))+r));
//                    g.draw(new Ellipse2D.Double((var1.x + Math.abs(((i) *  r + r) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) *  r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx > 0 && dy < 0) {
                    points.add(new Point(var1.x + Math.abs((i * r+r)* Math.cos(atan))+r, var1.y - Math.abs((i+1) *r  * Math.sin(atan))+r));
//                    g.draw(new Ellipse2D.Double((var1.x + Math.abs(((i) *  r + r) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) *  r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx < 0 && dy < 0) {
                    points.add(new Point(var1.x - Math.abs((i * r+r)* Math.cos(atan))+r, var1.y - Math.abs((i+1) *r  * Math.sin(atan))+r));
//                    g.draw(new Ellipse2D.Double((var1.x - Math.abs(((i) * r + r) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) * r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx < 0 && dy > 0) {
                    points.add(new Point(var1.x - Math.abs((i * r+r)* Math.cos(atan))+r, var1.y + Math.abs((i+1) *r  * Math.sin(atan))+r));
//                    g.draw(new Ellipse2D.Double((var1.x - Math.abs(((i) *  r + r) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) *  r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                }
                sum+=1;
            }
        }
        return points;
    }

    public void drawEdges(Graphics g, LinkedList<Vertex> path) {
        Graphics2D g2d = (Graphics2D) g;
        for (int j = 0; j < path.size() - 1; j++) {
            g2d.draw(new Line2D.Double(path.get(j).x, path.get(j).y, path.get(j).x, path.get(j).y));
        }
    }

    public void drawVoronoi2(Graphics g, List<Point> edgeList) {
        Graphics2D g2d = (Graphics2D) g;
        Voronoi voronoi = new Voronoi(edgeList);
        List<Edge> edges = voronoi.edges;
        for (Edge e : edges) {
            g2d.draw(new Line2D.Double(e.start.x * 5, e.start.y * 5, e.end.x * 5, e.end.y * 5));
        }
    }

    public void drawVoronoi(Graphics g, List<Edge> edgeList) {
        Graphics2D g2d = (Graphics2D) g;
        for (Edge e : edgeList) {
            g2d.draw(new Line2D.Double(e.start.x * 5, e.start.y * 5, e.end.x * 5, e.end.y * 5));
        }
    }

    public void drawArea(Graphics g) {
        g.drawLine(0, 0, 0, 500);
        g.drawLine(0, 0, 500, 0);
        g.drawLine(500, 500, 0, 500);
        g.drawLine(500, 500, 500, 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Draw();
            }
        });
    }

}