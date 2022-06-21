package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class KnapSack {

    /*
You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. Note that we have only one quantity of each item.
In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

Input:
The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. Each test case consists of four lines.
The first line consists of N the number of items.
The second line consists of W, the maximum capacity of the knapsack.
In the next line are N space separated positive integers denoting the values of the N items,
and in the fourth line are N space separated positive integers denoting the weights of the corresponding items.

Output:
For each testcase, in a new line, print the maximum possible value you can get with the given conditions that you can obtain for each test case in a new line.

Constraints:
1 ≤ T ≤ 100
1 ≤ N ≤ 1000
1 ≤ W ≤ 1000
1 ≤ wt[i] ≤ 1000
1 ≤ v[i] ≤ 1000

Example:
Input:
2
3
4
1 2 3
4 5 1
3
3
1 2 3
4 5 6
Output:
3
0
Explanation:
Test Case 1: With W = 4, you can either choose the 0th item or the 2nd item. Thus, the maximum value you can generate is the max of val[0] and val[2], which is equal to 3.
Test Case 2: With W = 3, there is no item you can choose from the given list as all the items have weight greater than W. Thus, the maximum value you can generate is 0.
 */

    @Test
    public void test() {
        int[] values = {10, 2, 5};
        int[] weights = {1, 3, 6};
        int maxWeight = 7;
        KnapSack knapSack = new KnapSack();
        final int actual = knapSack.getMaxValue(values, weights, maxWeight);
        System.out.println(actual);
        final int expected = 15;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGeek() {
        int[] values = {50};
        int[] weights = {38};
        int maxWeight = 57;
        KnapSack knapSack = new KnapSack();
        final int actual = knapSack.getMaxValue(values, weights, maxWeight);
        System.out.println(actual);
        final int expected = 50;
        Assert.assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        KnapSack knapSack = new KnapSack();
        int numItems;
        int maxWeight;
        int[] values;
        int[] weights;
        for (int it = 0; it < nTest; it++) {
            numItems = sc.nextInt();
            maxWeight = sc.nextInt();
            values = new int[numItems];
            weights = new int[numItems];
            for (int items = 0; items < numItems; items++) {
                values[items] = sc.nextInt();
            }
            for (int items = 0; items < numItems; items++) {
                weights[items] = sc.nextInt();
            }
            int actual = knapSack.getMaxValue(values, weights, maxWeight);
            System.out.println(actual);
        }
    }

    int getMaxValue(int[] values, int[] weights, int maxWeight) {
        int result = 0;
        int dp[][] = new int[values.length][maxWeight + 1];
// boundary for
        for (int i = 1; i < maxWeight + 1; i++) {
            if (weights[0] <= i) {
                dp[0][i] = values[0];
                result = Integer.max(result, values[0]);
            }
        }
        int val = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] > val && weights[k] <= 1) {
                val = values[k];
                           }
            dp[k][1] = val;
            result = Integer.max(result, val);
        }
        for (int k = 1; k < values.length; k++) {
            for (int i = 1; i < maxWeight + 1; i++) {
                if (i - weights[k] >= 0) {
                    dp[k][i] = Integer.max(dp[k - 1][i], dp[k - 1][i - weights[k]] + values[k]);
                } else {
                    dp[k][i] = dp[k - 1][i];
                }
                result = Integer.max(result, dp[k][i]);
            }
        }
        return result;
    }

}
