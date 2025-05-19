package models;

public class PointModel {
    public double x;
    public double y;

    public PointModel(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public String toString(boolean flag) {
        if (flag)
            return "[x: " + (int) x + " , y: " + (int) y + "]";
        else {
            return "[x: " + x + " , y: " + y + "]";
        }
    }
}
