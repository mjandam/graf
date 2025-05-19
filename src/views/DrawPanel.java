package views;

import models.BezierCurveModel;
import models.PointModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private boolean drawLines = false;
    private boolean drawCurve = true;
    private double zoom = 1.0;
    private int steps = 30;

    //Teoretycznie cała logika powinna być w models, ale zapomniałem o tym B)

    public DrawPanel(ListPanel listPanel) {

        setBackground(Color.black);

        //Obsługa myszki
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // dodaje punt do listy
                    // układ współrzędnych na środek ekranu dlatego dziele przez dwa
                    // oraz -y dla bardziej matematycznego układu wspołrzędnych
                    // dzieli przez zoom żeby dostosować do oddalenia. Jest prawie pewien że tego nie powinno tu być ale coś skopałem i jest
                    PointModel p = new PointModel((e.getX() - (double) getWidth() / 2) / zoom, -(e.getY() - (double) getHeight() / 2) / zoom);
                    MainFrame.points.add(p);
                    repaint();
                    listPanel.refresh();

                } else if (SwingUtilities.isRightMouseButton(e)) {

                    //usuń punkt
                    MainFrame.points.removeLast();
                    repaint();
                    listPanel.refresh();

                }

                //Obługa zooma
            }
        });
        addMouseWheelListener(e -> {
            if (e.getPreciseWheelRotation() < 0) {
                zoom *= 1.1;
            } else {
                zoom /= 1.1;
            }
            repaint();
        });
    }

    //Rysowanie punktów
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        // układ współrzędnych na środek ekranu
        int offsetX = getWidth() / 2;
        int offsetY = getHeight() / 2;
        g2.translate(offsetX, offsetY);

        //osie
        g2.setColor(new Color(10, 50, 10));
        g2.drawLine(-getWidth(), 0, getWidth(), 0); // oś X
        g2.drawLine(0, -getHeight(), 0, getHeight()); // oś Y

        //zoom i odwrócienie osi y (aby rosła w góre)
        g2.scale(zoom, -zoom);

        //rysowanie punktów
        g2.setColor(new Color(255, 255, 0));
        for (PointModel p : MainFrame.points) {
            g2.fillOval((int) p.x - 3, (int) p.y - 3, 6, 6);
        }

        //rysowani lini pomiędzy punktami
        if (drawLines && MainFrame.points.size() >= 2) {
            g2.setColor(new Color(0, 255, 255));
            for (int i = 0; i < MainFrame.points.size() - 1; i++) {
                PointModel p1 = MainFrame.points.get(i);
                PointModel p2 = MainFrame.points.get(i + 1);
                g2.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
            }
        }

        //rysowanie krzywej
        if (drawCurve && MainFrame.points.size() >= 2) {
            g2.setColor(new Color(255, 0, 255));
            ArrayList<PointModel> bezierPoints = BezierCurveModel.calculateCurve(MainFrame.points, steps);
            for (int i = 0; i < bezierPoints.size() - 1; i++) {
                PointModel p1 = bezierPoints.get(i);
                PointModel p2 = bezierPoints.get(i + 1);
                g2.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
            }
        }


    }


    public void clearPoints() {
        MainFrame.points.clear();
        repaint();
    }

    public void zoomReset() {
        zoom = 1.0;
        repaint();
    }

    //gettery

    public int getSteps() {
        return steps;
    }

    public boolean getIsDrawCurve() {
        return drawCurve;
    }

    //settery

    public void setdrawLines() {
        drawLines = !drawLines;
        repaint();
    }

    public void setDrawCurve(boolean b) {
        drawCurve = b;
        repaint();
    }

    public void setSteps(int steps) {
        this.steps = steps;
        repaint();
    }
}
