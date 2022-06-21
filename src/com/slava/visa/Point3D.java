package com.slava.visa;

public class Point3D extends Point2D {
    float z;

    public Point3D(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }

    double dist3D(Point3D anotherPoint) {
        return Math.sqrt((x - anotherPoint.x) * (x - anotherPoint.x)
                + (y - anotherPoint.y) * (y - anotherPoint.y)
                + (z - anotherPoint.z) * (z - anotherPoint.z)
        );
    }

    public void printDistance(double d) {
        System.out.println("3D distance = " + (int) Math.ceil(d));
    }

}
