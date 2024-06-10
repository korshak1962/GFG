package com.slava;

import org.junit.Assert;
import org.junit.Test;

//https://www.geeksforgeeks.org/problems/a-difference-of-values-and-indexes0302/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class DifValIndex {

    public static int maxDistance(int arr[], int n) {
        int minAplusI = arr[0];
        int maxAplusI = arr[0];
        int minAminusI = arr[0];
        int maxAminusI = arr[0];
        for (int i = 0; i < arr.length; i++) {
            minAplusI = Integer.min(minAplusI, arr[i] + i);
            maxAplusI = Integer.max(maxAplusI, arr[i] + i);
            minAminusI = Integer.min(minAminusI, arr[i] - i);
            maxAminusI = Integer.max(maxAminusI, arr[i] - i);
        }
        return Integer.max(maxAplusI - minAplusI, maxAminusI - minAminusI);
    }

    @Test
    public void test() {
        int arr[] = {1, 3, -1};
        int n = 3;
        int res = maxDistance(arr, n);
        Assert.assertEquals(5, res);
    }

}
