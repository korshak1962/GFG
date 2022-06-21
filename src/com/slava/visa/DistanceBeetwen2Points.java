package com.slava.visa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//https://leetcode.com/discuss/interview-question/790459/Visa-OA-Hackerrank-New-Grad-2019-2020
public class DistanceBeetwen2Points {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("resources/distanceBeetwen.txt"));
        float x = sc.nextFloat();
        float y = sc.nextFloat();
        float z = sc.nextFloat();
        Point3D first = new Point3D(x, y, z);
        x = sc.nextFloat();
        y = sc.nextFloat();
        z = sc.nextFloat();
        Point3D second = new Point3D(x, y, z);
        Point2D second2 = new Point2D(x, y);
        second2.printDistance(second2.dist2D(first));
        first.printDistance(first.dist3D(second));
    }
}
