package models;

import views.DrawPanel;
import views.ListPanel;
import views.MainFrame;

import java.util.ArrayList;

public class TransformMatrixCalculator {

    //aplikuje punktom macierz
    public static ArrayList<PointModel> calculate(ArrayList<PointModel> points, double[][] matrix) {
        ArrayList<PointModel> newPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            double x = points.get(i).x;
            double y = points.get(i).y;

            double newX = (matrix[0][0] * x + matrix[0][1] * y + matrix[0][2]);
            double newY = (matrix[1][0] * x + matrix[1][1] * y + matrix[1][2]);

            PointModel newPoint = new PointModel(newX, newY);
            newPoints.add(newPoint);

        }
        MatrixModels.matrix = matrix;

        return newPoints;
    }
}
