package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

//https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_slice_sum/
//kadane's algorithm
public class MaxSliceSum {

    @Test
    public void test() {
        int[] A = {3,2,-6,4,0};
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
