package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public double distance(Point p1){
        double dx = p1.x - this.x;
        double dy = p1.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
