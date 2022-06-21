package com.slava.arraysTasks;

import org.junit.Test;

//https://practice.geeksforgeeks.org/problems/circular-tour-1587115620/1/?category[]=two-pointer-algorithm
public class PetrolCircle {

    @Test
    public void test() {
        int[] input = {78, 87, 94, 16, 87, 36, 50, 93, 63, 22, 91, 28, 64, 60, 41, 27, 73, 27, 12, 37, 68, 69, 83, 30, 63, 31, 68, 24, 30, 36, 23, 3, 70, 59, 94, 68, 12, 57, 30, 43, 22, 74, 85, 20, 99, 38, 16, 25, 14, 71, 92, 27, 57, 81, 63, 74, 97, 71, 6, 82, 85, 26, 37, 28, 47, 6, 14, 30};
        int petrol[] = new int[input.length / 2];
        int distance[] = new int[input.length / 2];
        for (int i = 0; i < input.length; i += 2) {
            petrol[i / 2] = input[i];
        }
        for (int i = 0; i < input.length - 1; i += 2) {
            distance[i / 2] = input[i + 1];
        }
        System.out.println(tour(petrol, distance));

    }

    @Test
    public void test1() {
        int[] input = {96, 25, 46, 83, 68, 15, 65, 35, 51, 44, 9, 88, 79, 77, 85, 89};
        int petrol[] = new int[input.length / 2];
        int distance[] = new int[input.length / 2];
        for (int i = 0; i < input.length; i += 2) {
            petrol[i / 2] = input[i];
        }
        for (int i = 0; i < input.length - 1; i += 2) {
            distance[i / 2] = input[i + 1];
        }
        System.out.println(tour(petrol, distance));
    }

    int tour(int petrol[], int distance[]) {
        int res[] = new int[petrol.length];
        int minRes = Integer.MAX_VALUE;
        int index = 0;
        int sumRes = 0;
        // find min rest of gas point.
        for (int i = 0; i < petrol.length; i++) {
            res[i] = petrol[i] - distance[i];
            sumRes += res[i];
            if (minRes > sumRes && sumRes < 0) {
                index = i;
                minRes = sumRes;
            }
        }
        if (sumRes < 0) return -1;
        if (minRes >=0) return 0; // if gas everytime > 0 then start from 0;
        return index + 1;  // start from next of min
    }

}
