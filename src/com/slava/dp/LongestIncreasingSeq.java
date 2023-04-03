package com.slava.dp;

import java.util.Arrays;
//https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/0
public class LongestIncreasingSeq {

    static int findLongest(int[] input) {
        Integer[] dp = new Integer[input.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j] && dp[j] >= dp[i]) {
                    dp[i]++;
                }
            }
        }
        int max=-1;
        for (Integer incr:dp){
            max=Integer.max(max,incr);
        }
        return max;
    }
}
