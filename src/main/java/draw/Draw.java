package draw;

import Data.*;
import Data.Point;
import Data.Vertex;
import app.Individual;
import app.Individual2;
import graph.model.*;
import voronoi.model.Edge;
import voronoi.model.Voronoi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by prnc on 20/08/2016.
 */
public class Draw extends JFrame {
    private static Individual individual;

    public Draw() {
        setTitle("Drawing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 100, 100);
//        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setBackground(Color.GREEN);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        drawArea(g);
        Point p = Point.createRandomPoint(100, 0);
        individual = new Individual(p);
        individual.setGraph(individual.cars);
        drawBase(g, individual);
        drawPoints(g, individual);
        g.setColor(Color.RED);
        drawPath(g, individual);
    }

    public void drawBase(Graphics g, Individual individual) {
        Graphics2D g2d = (Graphics2D) g;
        Point p = individual.baseStation;
//        System.err.println("fail");
        g2d.setColor(Color.GREEN);
        g2d.fill(new Ellipse2D.Double(p.x * 5 - 5, p.y * 5 - 5, 10, 10));
    }

    public void drawPoints(Graphics g, Individual individual) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        for (Point p : individual.points) {
            g2d.fill(new Ellipse2D.Double(p.x * 5 - 1, p.y * 5 - 1, 2, 2));
        }
    }

    public void drawPath(Graphics g, Individual individual) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < individual.graph.getVertexes().size() - 1; i++) {
            LinkedList<graph.model.Vertex> path = individual.dijkstraAlgorithm.getPath(individual.graph.getVertexes().get(i));
//            System.err.println(i+" "+ individual.dijkstraAlgorithm.getPath(path));
            System.out.println(path.size());
            for (int j = 0; j < path.size() - 1; j++) {
                graph.model.Vertex vertex = path.get(j);
                graph.model.Vertex vertex2 = path.get(j + 1);
                Point p1, p2;
                p1 = new Point(vertex.getCentrePoint().x, vertex.getCentrePoint().y);
                p2 = new Point(vertex2.getCentrePoint().x, vertex2.getCentrePoint().y);
                g2d.draw(new Line2D.Double(p1.x * 5, p1.y * 5, p2.x * 5, p2.y * 5));
                drawAPath(g2d, p1, p2);
            }
        }
    }

    public void drawAPath(Graphics2D g, Point var1, Point var2) {
        double r = Individual.R;
        Double d = Point.getDistance(var1, var2) / (2 * r);
        int temp = (int) Math.round(d);
        double tan = (var2.y - var1.y) / (var2.x - var1.x);
        double atan = Math.atan(tan);
        System.err.println(tan + " " + atan);
        g.draw(new Ellipse2D.Double((var1.x - r) * 5, (var1.y - r) * 5, 10 * r, 10 * r));
        for (int i = 0; i < temp; i++) {
            if (atan < 0) {
                g.draw(new Ellipse2D.Double((var1.x - (i + 1) * 2 * r * Math.cos(atan) - r) * 5, (var1.y + (i + 1) * 2 * r * Math.sin(atan) - r) * 5, 10 * r, 10 * r));
            } else {
                g.draw(new Ellipse2D.Double((var1.x + (i + 1) * 2 * r * Math.cos(atan) - r) * 5, (var1.y + (i + 1) * 2 * r * Math.sin(atan) - r) * 5, 10 * r, 10 * r));
            }
        }
    }

    public void drawAPath2(Graphics2D g, Point var1, Point var2) {
        double r = Individual.R;
        double dx = var2.x - var1.x;
        double dy = var2.y - var1.y;
        Double d = Point.getDistance(var1, var2) / (2 * r);
        int temp = (int) Math.round(d);
        if ((var2.x - var1.x) == 0) {
            for (int i = 0; i < temp; i++) {
                if (dy > 0) {
                    g.draw(new Ellipse2D.Double((var1.x) * 5, (var1.y + Math.abs((i) * 2 * r) - r) * 5, 10 * r, 10 * r));
                }
            }
        } else {
            double tan = (var2.y - var1.y) / (var2.x - var1.x);
            double atan = Math.atan(tan);
            for (int i = 0; i < temp; i++) {
                if (dx > 0 && dy > 0) {
                    g.draw(new Ellipse2D.Double((var1.x + Math.abs((i) * 2 * r * Math.cos(atan)) - r) * 5, (var1.y + Math.abs((i) * 2 * r * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx > 0 && dy < 0) {
                    g.draw(new Ellipse2D.Double((var1.x + Math.abs((i) * 2 * r * Math.cos(atan)) - r) * 5, (var1.y - Math.abs((i) * 2 * r * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx < 0 && dy < 0) {
                    g.draw(new Ellipse2D.Double((var1.x - Math.abs((i) * 2 * r * Math.cos(atan)) - r) * 5, (var1.y - Math.abs((i) * 2 * r * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                } else if (dx<0 && dy>0){
                    g.draw(new Ellipse2D.Double((var1.x - Math.abs((i) * 2 * r * Math.cos(atan)) - r) * 5, (var1.y + Math.abs((i) * 2 * r * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
                }
            }
        }
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