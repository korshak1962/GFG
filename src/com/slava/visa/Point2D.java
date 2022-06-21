package com.slava.visa;

public class Point2D {
    float x;
    float y;

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    double dist2D(Point2D anotherPoint) {
        return Math.sqrt((x - anotherPoint.x) * (x - anotherPoint.x)
                + (y - anotherPoint.y) * (y - anotherPoint.y)
        );
    }

    public void printDistance(double d) {
        System.out.println("2D distance = " + (int) Math.ceil(d));
    }
}
