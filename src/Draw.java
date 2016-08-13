import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by pc on 06/08/2016.
 */
public class Draw extends JFrame{
    public Draw() {
        setTitle("Drawing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 100, 100);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(Color.GREEN);
        setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Draw();
            }
        });
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.drawLine(0, 0, 100, 0);
        g2d.drawLine(0, 0, 0, 100);
        g2d.drawLine(100, 100, 100,0 );
        g2d.drawLine(100, 100, 0,100 );
        Map map = new Map(100, 100);
        map.sortS();
//        Chromosome chromosome= new Chromosome(true, map);
        for(Road road: map.roads){
            drawRoad(g2d, road);
        }
//        for(Sensor s: chromosome.chroms){
//            drawSensor(g2d, s);
//        }
    }

    public void drawSensor(Graphics2D g2d, Sensor s){
        g2d.draw(new Ellipse2D.Double(s.x, s.y, s.r*2, s.r*2));
    }

    public void drawRoad(Graphics2D g2d, Road road){
        double w= road.xMax-road.xMin;
        double h= road.yMax-road.yMin;
        g2d.draw(new Rectangle2D.Double(road.xMin, road.yMin, w, h));
    }
}
