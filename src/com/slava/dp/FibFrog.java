package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://app-eu.codility.com/demo/results/trainingUVAXZ6-N4E/  100%
public class FibFrog {

    public int solution(int[] A) {
        int res = Integer.MAX_VALUE;
        int[] dp = new int[A.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        List<Integer> fibs = calcFib(A.length);
        int startPosition = 0;
        dp[startPosition] = 0;
        int nextStartPosition = 0;
        int calcPos;
        while (startPosition < A.length + 1) {
            for (int f : fibs) {
                calcPos = f + startPosition;
                if (calcPos == A.length + 1) {
                    res = Integer.min(res, dp[startPosition] + 1);
                } else if (calcPos <= A.length && A[calcPos - 1] > 0) {
                    dp[calcPos] = Integer.min(dp[calcPos], dp[startPosition] + 1);
                }
            }
            for (int pos = startPosition + 1; pos < A.length + 1; pos++) {
                if (dp[pos] < Integer.MAX_VALUE) {
                    nextStartPosition = pos;
                    break;
                }
            }
            if (nextStartPosition == startPosition) {
                if (res == Integer.MAX_VALUE) return -1;
                else return res;
            }
            startPosition = nextStartPosition;
        }
        if (res == Integer.MAX_VALUE) return -1;
        else return res;
    }

    List<Integer> calcFib(int max) {
        List<Integer> fibs = new ArrayList<>();
        int fPrev = 1;
        int fCur = 2;
        fibs.add(fPrev);
        fibs.add(fCur);
        while (fCur < max + 1) {
            int fTmp = fPrev + fCur;
            if (fTmp <= max + 1) {
                fibs.add(fTmp);
            }
            fPrev = fCur;
            fCur = fTmp;
        }
        return fibs;
    }

    @Test
    public void test3() {
        int[] A = new int[5];
        A[0] = 0;
        A[1] = 0;
        A[2] = 0;
        A[3] = 0;
        A[4] = 1;

        int res = solution(A);
        Assert.assertEquals(2,res);
    }

    @Test
    public void test2() {
        int[] A = new int[2];
        A[0] = 1;
        A[1] = 1;

        int res = solution(A);
        Assert.assertEquals(1,res);
    }

    @Test
    public void test1() {
        int[] A = new int[11];
        A[0] = 0;
        A[1] = 0;
        A[2] = 0;
        A[3] = 1;
        A[4] = 1;
        A[5] = 0;
        A[6] = 1;
        A[7] = 0;
        A[8] = 0;
        A[9] = 0;
        A[10] = 0;
        int res = solution(A);
        Assert.assertEquals(3,res);
    }
}
