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
public class Draw extends JApplet{
    @Override
    public void paint(Graphics g){
        drawArea(g);
        Data.Point p= Point.createRandomPoint(100, 0);
        Individual individual= new Individual(p);
        List<Point> points = new ArrayList<Point>();

        Random gen = new Random();

        for (int i = 0; i < 20; i++){
            double x = gen.nextDouble()*100;
            double y = gen.nextDouble()*100;
            points.add(new Point(x, y));
        }
        individual.setGraph(individual.cars);
        drawPoints(g, individual);
        drawBase(g, individual);
        drawPath(g, individual);
    }

    public void drawBase(Graphics g, Individual individual){
        Graphics2D g2d= (Graphics2D)g;
        Point p= individual.baseStation;
        g2d.setColor(Color.RED);
        g2d.draw(new Ellipse2D.Double(p.x*5-5, p.y*5-5, 10, 10));
    }

    public void drawPoints(Graphics g, Individual individual){
        Graphics2D g2d= (Graphics2D)g;
        for (Point p: individual.points) {
            g2d.draw(new Line2D.Double(p.x*5, p.y*5, p.x*5, p.y*5));
        }
    }

    public void drawPath(Graphics g, Individual individual){
        Graphics2D g2d= (Graphics2D)g;
        for(int i=0; i<individual.points.size()-1; i++){
            LinkedList<graph.model.Vertex> path = individual.dijkstraAlgorithm.getPath(individual.graph.getVertexes().get(i));
            System.err.println(i+" "+ individual.dijkstraAlgorithm.getPath(path));
            for (int j = 0; j <path.size()-1 ; j++) {
                graph.model.Vertex vertex= path.get(j);
                graph.model.Vertex vertex2= path.get(j+1);
                Double d= Point.getDistance(new Point(vertex.getCentrePoint().x, vertex.getCentrePoint().y),
                        new Point(vertex2.getCentrePoint().x, vertex2.getCentrePoint().y));
                int temp= (int)Math.round(d);
                for(int var=0; var<temp; var++){
                    g2d.draw(new Line2D.Double());
                }
            }
        }
    }

    public void drawAPath(Graphics2D g, Point var1, Point var2){
        Double d= Point.getDistance(var1, var2);
        int temp= (int)Math.round(d);

    }

    public void drawEdges(Graphics g, LinkedList<Vertex> path) {
        Graphics2D g2d = (Graphics2D) g;
        for (int j = 0; j < path.size() - 1; j++) {
            g2d.draw(new Line2D.Double(path.get(j).x, path.get(j).y, path.get(j).x, path.get(j).y));
        }
    }

    public void drawVoronoi2(Graphics g, List<Point> edgeList){
        Graphics2D g2d= (Graphics2D)g;
        Voronoi voronoi= new Voronoi(edgeList);
        List<Edge> edges= voronoi.edges;
        for (Edge e:edges) {
            g2d.draw(new Line2D.Double(e.start.x*5, e.start.y*5, e.end.x*5, e.end.y*5));
        }
    }

    public void drawVoronoi(Graphics g, List<Edge> edgeList){
        Graphics2D g2d= (Graphics2D)g;
        for (Edge e:edgeList) {
            g2d.draw(new Line2D.Double(e.start.x*5, e.start.y*5, e.end.x*5, e.end.y*5));
        }
    }

    public void drawArea(Graphics g){
        g.drawLine(0,0, 0,500);
        g.drawLine(0,0, 500,0);
        g.drawLine(500,500, 0,500);
        g.drawLine(500,500, 500,0);
    }

}