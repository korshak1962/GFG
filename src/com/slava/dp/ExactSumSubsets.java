package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ExactSumSubsets {

    /*
    https://practice.geeksforgeeks.org/problems/perfect-sum-problem/0
    Given an array arr[] of integers and an integer sum, the task is to count all subsets of the given array
    with a sum equal to a given sum.

Note: Answer can be very large, so, output answer modulo 109+7

Example 1:

Input: N = 6, arr[] = {2, 3, 5, 6, 8, 10}
       sum = 10
Output: 3
Explanation: {2, 3, 5}, {2, 8}, {10}
Example 2:
Input: N = 5, arr[] = {1, 2, 3, 4, 5}
       sum = 10
Output: 3
Explanation: {1, 2, 3, 4}, {1, 4, 5},
             {2, 3, 5}

Your Task:
You don't need to read input or print anything. Complete the function perfectSum() which takes N, array arr[]
and sum as input parameters and returns an integer value

Expected Time Complexity: O(N*sum)
Expected Auxiliary Space: O(N*sum)

Constraints:
1 ≤ N*sum ≤ 106
     */

    static final int div = 1000000007;

    static int findAllSubsets(int[] origArray, int targetSum) {
        int dp[][] = new int[origArray.length][targetSum + 1];
        if (origArray[0] < targetSum + 1) {
            dp[0][origArray[0]] = 1;
        }
        for (int i = 1; i < origArray.length; i++) {
            for (int k = 1; k < targetSum + 1; k++) {
                dp[i][k] = dp[i - 1][k]; // copy previous  column
            }
            if (origArray[i] > targetSum) {
                continue;
            }
            for (int k = 1; k < targetSum + 1; k++) {
                if (dp[i - 1][k] > 0) {
                    final int newSum = k + origArray[i];
                    if (newSum < targetSum + 1) {
                        dp[i][newSum] = (dp[i][newSum] + dp[i - 1][k]) % div;
                    }
                }
            }
            dp[i][origArray[i]]++;
        }
        return dp[origArray.length - 1][targetSum];
    }


    static int findAllSubsets2(int[] origArray, int targetSum) {
        long dp[][] = new long[origArray.length][targetSum + 1];
        if (origArray[0] < targetSum + 1) {
            dp[0][origArray[0]] = 1;
        }
        for (int i = 1; i < origArray.length; i++) {
            for (int k = 0; k < targetSum + 1; k++) {
                dp[i][k] = dp[i - 1][k]; // copy previous  column
            }
            if (origArray[i] > targetSum) {
                continue;
            }
            for (int k = 0; k < targetSum + 1; k++) {
                if (dp[i - 1][k] > 0) {
                    final int newSum = k + origArray[i];
                    if (newSum < targetSum + 1) {
                        dp[i][newSum] += dp[i - 1][k];
                    }
                }
            }
            dp[i][origArray[i]]++;
        }
        final long div = Double.valueOf(1E9).longValue() + 7;
        return Long.valueOf(dp[origArray.length - 1][targetSum] % div).intValue();
    }


    @Test
    public void testFindAllSubsets() {
        int[] input = {2, 3, 5, 6, 8, 10};
        int sum = 10;
        int res = findAllSubsets(input, sum);
        Assert.assertEquals(3, res);
        int div = 1000000000 + 7;
        System.out.println(res % div);
    }

    @Test
    public void testFindAllSubsets10() {
        int[] input = {1, 2, 3, 4, 5};
        int sum = 10;
        int res = findAllSubsets(input, sum);
        Assert.assertEquals(3, res);
        int div = 1000000000 + 7;
        System.out.println(res % div);
    }

    @Test
    public void testFindAllSubsets63() {
        int[] input = {12, 3, 16, 19, 11, 12, 18, 25, 22};
        int sum = 63;
        int res = findAllSubsets(input, sum);
        Assert.assertEquals(7, res);
        int div = 1000000000 + 7;
        System.out.println(res % div);
    }

    @Test
    public void testFindAllSubsets185() {
        int[] input = {6, 10, 1, 4, 7, 1, 9, 5, 10, 5, 3, 5, 10, 1, 5, 4, 4, 3, 8, 10, 10, 7, 4, 1, 6, 7, 6, 6, 8, 3, 4, 4, 2, 7, 7, 1, 9, 6, 5, 9, 10, 9, 5, 1, 2, 2, 4, 5, 4, 3, 4, 5, 9, 10, 8, 4, 6, 3, 1, 5, 8, 7, 8, 9, 3, 5, 1, 1, 2, 5, 1, 3, 6, 8, 3, 7, 9, 8, 3, 4, 2, 8, 8, 10, 7, 7, 5, 4, 2, 8, 9, 9, 4, 6, 7, 8};
        int sum = 185;
        int res = findAllSubsets(input, sum);
        Assert.assertEquals(175398726, res);
        System.out.println(res);
    }


}
