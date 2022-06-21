package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;
//https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/

public class MaxProfit {

    @Test
    public void test() {
        int[] A = new int[6];
        A[0] = 23171;
        A[1] = 21011;
        A[2] = 21123;
        A[3] = 21366;
        A[4] = 21013;
        A[5] = 21367;
        int res = solution(A);
        Assert.assertEquals(356, res);
    }

    public int solution(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int min = A[0];
        int max = A[0];
        int maxProfit = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < min) {
                min = A[i];
                max = min;
            }
            if (A[i] > max) {
                max = A[i];
                maxProfit = Integer.max(max - min, maxProfit);
            }
        }
        return maxProfit;
    }

}
