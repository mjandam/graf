package models;

public class MatrixModels {
    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    public static double[][] matrix = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
    };

    public static void reset() {
        matrix = new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
    }

    public static double[][] translation(double dx, double dy) {
        return new double[][]{
                {1, 0, dx},
                {0, 1, dy},
                {0, 0, 1}
        };
    }

    public static double[][] scaling(double sx, double sy) {
        return new double[][]{
                {sx, 0, 0},
                {0, sy, 0},
                {0, 0, 1}
        };
    }

    public static double[][] rotation(double angleDegrees) {
        double rad = Math.toRadians(angleDegrees);
        return new double[][]{
                {Math.cos(rad), -Math.sin(rad), 0},
                {Math.sin(rad), Math.cos(rad), 0},
                {0, 0, 1}
        };
    }

    public static String matrixToString(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (double[] row : matrix) {
            sb.append("|");
            for (double val : row) {
                sb.append(String.format("%8.2f ", val));
            }
            sb.append("|\n\n");
        }
        return sb.toString();
    }


}
