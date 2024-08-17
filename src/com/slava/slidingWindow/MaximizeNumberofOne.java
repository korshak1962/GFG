package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

public class MaximizeNumberofOne {

    int findZeroes(int arr[], int n, int m) {
        int res = 0;
        int leftInd = 0, rightInd = 0;
        int zerosInRange = Math.abs(arr[0]-1);
        while (rightInd < arr.length) {
            if (zerosInRange <= m) {
                res = Integer.max(res, rightInd - leftInd + 1);
                rightInd++;
                if (rightInd < arr.length && arr[rightInd] == 0) {
                    zerosInRange++;
                }
            } else {
                if (arr[leftInd] == 0 && zerosInRange > 0) {
                    zerosInRange--;
                }
                leftInd++;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int arr[] = {1, 0, 1};
        int m = 1;
        Assert.assertEquals(3, findZeroes(arr, arr.length, m));
    }

    @Test
    public void test1() {
        int arr[] = {1, 0, 1, 0};
        int m = 0;
        Assert.assertEquals(1, findZeroes(arr, arr.length, m));
    }

    @Test
    public void test2() {
        int arr[] = {0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1,
                1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0};
        int m = 25;
        Assert.assertEquals(52, findZeroes(arr, arr.length, m));
    }

}
