package com.slava.codility;

import org.junit.Assert;
import org.junit.Test;

//https://app.codility.com/demo/results/training9J3HYS-G5K/
public class MinAvgTwoSlice {

    public int solution(int[] A) {
        int iMin = 0;
        double min = ((double) A[1] + A[0]) / 2;
        for (int i = 1; i < A.length; i++) {  // in case min slice consists two elements
            final double aver = ((double) A[i] + A[i - 1]) / 2;
            if (min > aver) {
                min = aver;
                iMin = i - 1;
            }
        }
        if (A.length > 2) {
            double[] prefixSum = new double[A.length];
            prefixSum[0] = A[0];
            for (int i = 1; i < A.length; i++) {  // calc prefix sum
                prefixSum[i] = prefixSum[i - 1] + A[i];
            }
            if (prefixSum[2] / 3 < min) {   //
                min = prefixSum[2] / 3;
                iMin = 0;
            }
            for (int i = 3; i < A.length; i++) {   // in case min slice consists three elements
                final double aver = (prefixSum[i] - prefixSum[i - 3]) / 3;
                if (min > aver) {
                    min = aver;
                    iMin = i - 2;
                }
            }
        }
        return iMin;
    }


    @Test
    public void test() {
        int A[] = {4, 2, 2, 5, 1, 5, 8};
        int res = solution(A);

    }

    @Test
    public void test1() {
        int A[] = {-3, -5, -8, -4, -10};
        int res = solution(A);
        Assert.assertTrue(res==2);
    }

    @Test
    public void test2() {
        int A[] = {10, 10, -1, 2, 4, -1, 2, -1};
        int res = solution(A);
        Assert.assertEquals(5,res);
    }


}