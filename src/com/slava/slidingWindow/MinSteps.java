package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

//https://www.geeksforgeeks.org/problems/coin-piles5152/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class MinSteps {

    static int[] A;
    static int K;

    static int minSteps(int[] A, int N, int K) {
        MinSteps.A = A;
        MinSteps.K = K;
        Arrays.sort(A);
        int res = getRes(0);
        int remLeft = 0;
        int iStart = 0;
        while (remLeft < res && iStart < A.length - 1) {
            remLeft += A[iStart++];
            while (iStart < A.length && A[iStart] == A[iStart - 1]) {
                remLeft += A[iStart++];
            }
            res = Integer.min(getRes(iStart) + remLeft, res);
        }
        return res;
    }

    private static int getRes(int iStart) {
        int res = 0;
        for (int i = iStart; i < A.length; i++) {
            if (A[i] > A[iStart] + K) {
                res += A[i] - A[iStart] - K;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int K = 0;
        int A[] = {2, 2, 2, 2};
        Assert.assertEquals(0, minSteps(A, A.length, K));
    }

    @Test
    public void test1() {
        int K = 3;
        int A[] = {1, 5, 1, 2, 5, 1};
        Assert.assertEquals(2, minSteps(A, A.length, K));
    }

    @Test
    public void test2() {
        int K = 0;
        int A[] = {1, 3};
        Assert.assertEquals(1, minSteps(A, A.length, K));
    }

    @Test
    public void test3() {
        int K = 3;
        int A[] = {5, 7, 1, 5, 9, 3, 6, 6, 8};
        Assert.assertEquals(5, minSteps(A, A.length, K));
    }
}
