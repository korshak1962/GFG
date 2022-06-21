package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//https://practice.geeksforgeeks.org/problems/max-circular-subarray-sum-1587115620/1/?problemStatus=unsolved
public class MaxCircularSum {

    @Test
    public void test() {
        int[] input = {8, -8, 9, -9, 10, -11, 12};
        Assert.assertEquals(22, circularSubarraySum(input, 7));
    }

    @Test
    public void test34() {
        int[] input = {16, 18, -4, -2, -9};
        Assert.assertEquals(34, circularSubarraySum(input, 7));
    }

    @Test
    public void test49() {
        int[] input = {-16, 21, 28, -6};
        Assert.assertEquals(49, circularSubarraySum(input, 7));
    }

    @Test
    public void test28() {
        int[] input = {-3, -30, -25, 14, -15, 19, -27, -19, 1, 27, -4, -25, -19, 5, 7, -27, 11, -21, -13, -18};
        Assert.assertEquals(28, circularSubarraySum(input, 7));
    }  //-162

//https://www.youtube.com/watch?v=Q1TYVUEr-wY&t=79s
    static int circularSubarraySum(int a[], int n) {
        long sum = 0;
        long positiveSumSoFar = 0;
        long negativeSumSoFar = Integer.MAX_VALUE;
        long minSum = 0;
        long maxSum = a[0];
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            positiveSumSoFar += a[i];
            maxSum = Long.max(maxSum, positiveSumSoFar);
            positiveSumSoFar = positiveSumSoFar < 0 ? 0 : positiveSumSoFar;
            negativeSumSoFar += a[i];
            negativeSumSoFar = negativeSumSoFar > 0 ? 0 : negativeSumSoFar;
            minSum = Long.min(minSum, negativeSumSoFar);
        }
        return (int) Long.max(maxSum, sum - minSum);
    }


    static int circularSubarraySum1(int a[], int n) {
        if (a.length == 0) return 0;
        if (a.length == 1) return a[0];
        int iSumMin = 0;
        long sum = 0;
        long sumMin = Long.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sumMin > sum) {
                iSumMin = i;
                sumMin = sum;
            }
        }
        long sumMax = Long.MIN_VALUE;
        sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = iSumMin; i < a.length; i++) {
            sum += a[i];
            sum = sum < 0 ? 0 : sum;
            sumMax = Long.max(sum, sumMax);
            min = Integer.min(min, a[i]);
        }
        for (int i = 0; i < iSumMin; i++) {
            sum += a[i];
            sum = sum < 0 ? 0 : sum;
            sumMax = Long.max(sum, sumMax);
            min = Integer.min(min, a[i]);
        }
        sumMax = sumMax > 0 ? sumMax : min;
        return (int) sumMax;
    }
}
