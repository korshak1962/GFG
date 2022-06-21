package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

//https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_slice_sum/
//kadane's algorithm
public class MaxSliceSum {

    @Test
    public void test() {
        int[] A = new int[5];
        A[0] = 3;
        A[1] = 2;
        A[2] = -6;
        A[3] = 4;
        A[4] = 0;
        int res = solution(A);
        Assert.assertEquals(5, res);
    }

    //kadane's algorithm :)
    public int solution(int[] A) {
        if (A.length < 1) return 0;
        int maxSliceSum = A[0];
        int currentSum = A[0];
        for (int i = 1; i < A.length; i++) {
            currentSum = Integer.max(currentSum + A[i], A[i]);
            maxSliceSum = Integer.max(currentSum, maxSliceSum);
        }
        return maxSliceSum;
    }

}
