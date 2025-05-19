package models;

import java.awt.*;
import java.util.ArrayList;

public class BezierCurveModel {

    public static ArrayList<PointModel> calculateCurve(ArrayList<PointModel> points, int steps) {
        ArrayList<PointModel> curve = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            double t = i / (double) steps;
            curve.add(algorithm(points, t));
        }
        return curve;
    }

    private static PointModel algorithm(ArrayList<PointModel> points, double t) {
        ArrayList<PointModel> temp = new ArrayList<>(points);
        while (temp.size() > 1) {
            ArrayList<PointModel> next = new ArrayList<>();
            for (int i = 0; i < temp.size() - 1; i++) {
                //interpolacja liniowa
                double x = (1 - t) * temp.get(i).x + t * temp.get(i + 1).x;
                double y = (1 - t) * temp.get(i).y + t * temp.get(i + 1).y;
                next.add(new PointModel(x, y));
            }
            temp = next;
        }
        return temp.getFirst();
    }
}
