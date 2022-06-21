package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class OptimalStrategy {

/*
https://practice.geeksforgeeks.org/problems/optimal-strategy-for-a-game/0

You are given an array A of size N. The array contains integers and is of even length. The elements of the array represent N coin of values V1, V2, ....Vn. You play against an opponent in an alternating way.

In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin.

You need to determine the maximum possible amouint of money you can win if you go first.

Input:
The first line of input contains T denoting the number of testcases. T testcases follow. Each testcase contains two lines of input. The first line contains N denoting the size of the array. The second line contains N elements of the array.

Output:
For each testcase, in a new line, print the maximum amout.

Constraints:
1 <= T <= 100
2 <= N <= 100
1 <= Ai <= 106

Examples:
Input:
2
4
5 3 7 10
4
8 15 3 7
Output:
15
22

Explanation:
Testcase1: The user collects maximum value as 15(10 + 5)
Testcase2: The user collects maximum value as 22(7 + 15)
 */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int length = sc.nextInt();
            int coins[] = new int[length];
            for (int i = 0; i < length; i++) {
                coins[i] = sc.nextInt();
            }
            System.out.println(findMaxWin(coins));
        }
    }

    static int dp[][][];

    static int findMaxWin(int coins[]) {
        dp = new int[coins.length][coins.length][2];
        return findSum(coins, 0, coins.length - 1, true)[0];
    }

    static int[] findSum(int coins[], int startIndex, int endIndex, boolean player) {
        if (dp[startIndex][endIndex][0] > 0) {
            return dp[startIndex][endIndex];
        }
        if ((endIndex - startIndex) == 1) {
            int[] scores = new int[2];
            if (player) {
                scores[0] = Integer.max(coins[startIndex], coins[endIndex]);
                scores[1] = Integer.min(coins[startIndex], coins[endIndex]);
            } else {
                scores[1] = Integer.max(coins[startIndex], coins[endIndex]);
                scores[0] = Integer.min(coins[startIndex], coins[endIndex]);
            }
            dp[startIndex][endIndex] = scores.clone();
            return scores;
        }
        final int[] sumWO_First = findSum(coins, startIndex + 1, endIndex, !player);
        final int[] sumWO_Last = findSum(coins, startIndex, endIndex - 1, !player);
        if (player) {
            if (coins[startIndex] + sumWO_First[0] > coins[endIndex] + sumWO_Last[0]) {
                sumWO_First[0] += coins[startIndex];
                dp[startIndex][endIndex] = sumWO_First.clone();
                return sumWO_First;
            } else {
                sumWO_Last[0] += coins[endIndex];
                dp[startIndex][endIndex] = sumWO_Last.clone();
                return sumWO_Last;
            }
        } else {
            if (coins[startIndex] + sumWO_First[1] > coins[endIndex] + sumWO_Last[1]) {
                sumWO_First[1] += coins[startIndex];
                dp[startIndex][endIndex] = sumWO_First.clone();
                return sumWO_First;
            } else {
                sumWO_Last[1] += coins[endIndex];
                dp[startIndex][endIndex] = sumWO_Last.clone();
                return sumWO_Last;
            }
        }
    }

    static int[] findSum1(int coins[], int startIndex, int endIndex, boolean player) {
        if ((endIndex - startIndex) == 1) {
            int[] scores = new int[2];
            if (player) {
                scores[0] = Integer.max(coins[startIndex], coins[endIndex]);
                scores[1] = Integer.min(coins[startIndex], coins[endIndex]);
            } else {
                scores[1] = Integer.max(coins[startIndex], coins[endIndex]);
                scores[0] = Integer.min(coins[startIndex], coins[endIndex]);
            }
            return scores;
        }
        final int[] sumWO_First = findSum(coins, startIndex + 1, endIndex, !player);
        final int[] sumWO_Last = findSum(coins, startIndex, endIndex - 1, !player);
        if (player) {
            if (coins[startIndex] + sumWO_First[0] > coins[endIndex] + sumWO_Last[0]) {
                sumWO_First[0] += coins[startIndex];
                return sumWO_First;
            } else {
                sumWO_Last[0] += coins[endIndex];
                return sumWO_Last;
            }
        } else {
            if (coins[startIndex] + sumWO_First[1] > coins[endIndex] + sumWO_Last[1]) {
                sumWO_First[1] += coins[startIndex];
                return sumWO_First;
            } else {
                sumWO_Last[1] += coins[endIndex];
                return sumWO_Last;
            }
        }
    }


    static int[] findSum0(int coins[], boolean player) {
        int[] scores;
        if (coins.length == 2 && player) {
            scores = new int[2];
            scores[0] = Integer.max(coins[0], coins[1]);
            scores[1] = Integer.min(coins[0], coins[1]);
            return scores;
        }
        if (coins.length == 2 && !player) {
            scores = new int[2];
            scores[1] = Integer.max(coins[0], coins[1]);
            scores[0] = Integer.min(coins[0], coins[1]);
            return scores;
        }
        final int[] sumWO_First = findSum0(Arrays.copyOfRange(coins, 1, coins.length), !player);
        final int[] sumWO_Last = findSum0(Arrays.copyOfRange(coins, 0, coins.length - 1), !player);
        if (player) {
            if (coins[0] + sumWO_First[0] > coins[coins.length - 1] + sumWO_Last[0]) {
                sumWO_First[0] += coins[0];
                return sumWO_First;
            } else {
                sumWO_Last[0] += coins[coins.length - 1];
                return sumWO_Last;
            }
        } else {
            if (coins[0] + sumWO_First[1] > coins[coins.length - 1] + sumWO_Last[1]) {
                sumWO_First[1] += coins[0];
                return sumWO_First;
            } else {
                sumWO_Last[1] += coins[coins.length - 1];
                return sumWO_Last;
            }
        }
    }


    @Test
    public void testFindMaxWin15() {
        int[] coins = {5, 3, 7, 10};
        dp = new int[coins.length][coins.length][2];
        final int[] results = findSum(coins, 0, coins.length - 1, true);
        System.out.println("sum " + Arrays.stream(coins).sum());
        System.out.println("sum1 " + Arrays.stream(results).sum());
        Assert.assertEquals(15, results[0]);
    }

    @Test
    public void testFindMaxWin22() {
        int[] coins = {8, 15, 3, 7};
        dp = new int[coins.length][coins.length][2];
        final int[] results = findSum(coins, 0, coins.length - 1, true);
        System.out.println("sum " + Arrays.stream(coins).sum());
        System.out.println("sum1 " + Arrays.stream(results).sum());
        Assert.assertEquals(22, results[0]);
    }

    @Test
    public void testFindMaxWin22S() {
        int[] coins = {8, 15, 3, 7, 8, 29};
        dp = new int[coins.length][coins.length][2];
        final int[] results = findSum(coins, 0, coins.length - 1, true);
     //   printDP(coins);
        System.out.println("sum " + Arrays.stream(coins).sum());
        System.out.println("sum1 " + Arrays.stream(results).sum());
        Assert.assertEquals(22, results[0]);
    }

    @Test
    public void testFindMaxWin22S0() {
        int[] coins = {8, 15, 3, 7, 8, 29};
        dp = new int[coins.length][coins.length][2];
        final int[] results = findSum0(coins, true);
        System.out.println("sum " + Arrays.stream(coins).sum());
        System.out.println("sum1 " + Arrays.stream(results).sum());
        Assert.assertEquals(22, results[0]);
    }

    private void printDP(int[] coins) {
        for (int iP = 0; iP < 2; iP++) {
            for (int endIndex = 0; endIndex < coins.length; endIndex++) {
                for (int startIndex = 0; startIndex < coins.length; startIndex++) {
                    if (startIndex < endIndex) {
                        System.out.println(startIndex + " " + endIndex + " " + iP + " " + dp[startIndex][endIndex][iP]);
                    }
                }
            }
        }
    }

    @Test
    public void testFindMaxWin93217() {
        int[] coins = {4993, 4883, 8894, 7241, 1476, 8226, 1207, 5674, 6967, 6766, 8371, 1467, 3169, 2228, 297, 288, 4300, 4194, 4689
                , 1155, 3934, 5209, 4342, 2916, 2808, 2067, 5467, 8012, 1855, 1894, 2684, 9266, 5705, 3578, 4775, 578, 1546, 216, 395, 7883, 720, 9476};
        dp = new int[coins.length][coins.length][2];
        System.out.println("length= " + coins.length);
        final int[] results = findSum1(coins, 0, coins.length - 1, true);
        System.out.println("control sum " + Arrays.stream(coins).sum());
        System.out.println("result sum " + Arrays.stream(results).sum());
        Assert.assertEquals(93217, results[0]);
    }

}
