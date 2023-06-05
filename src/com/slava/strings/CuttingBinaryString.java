package com.slava.strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://practice.geeksforgeeks.org/problems/cutting-binary-string1342/1
public class CuttingBinaryString {
    static int[][] dp;
    static Set<String> powerOf5 = new HashSet<>();

    static {
        fillPowerOf5();
    }

    static int cuts(final String s) {
        dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -2);
        }
        final int result = cuts(s, 0, s.length() - 1);
        if (result < 0) return -1;
        return result + 1;
    }

    static int cuts(final String s, int from, int to) {
        if (dp[from][to] != -2) {
            return dp[from][to];
        }
        if (powerOf5.contains(s.substring(from, to + 1))) {
            dp[from][to] = 0;
            return dp[from][to];
        }
        int forReturn = Integer.MAX_VALUE;
        for (int i = from; i < to; i++) {
            dp[from][i] = cuts(s, from, i);
            dp[i + 1][to] = cuts(s, i + 1, to);
            if (dp[from][i] < 0 || dp[i + 1][to] < 0) {
                continue;
            }
            forReturn = Integer.min(forReturn, dp[from][i] + dp[i + 1][to] + 1);
        }
        if (forReturn == Integer.MAX_VALUE) {
            dp[from][to] = -1;
            return dp[from][to];
        }
        dp[from][to] = forReturn;
        return forReturn;
    }


    static void fillPowerOf5() {
        long power5 = 1;
        String sPower5 = Long.toString(power5, 2);
        powerOf5.add(sPower5);
        while (sPower5.length() < 51) {
            power5 *= 5;
            sPower5 = Long.toString(power5, 2);
            powerOf5.add(sPower5);
        }
    }




    @Test
    public void testCuts() {
        int res = cuts("1101");
        System.out.println(res);
        Assert.assertEquals(2, res);
    }
}
