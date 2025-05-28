package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

//https://practice.geeksforgeeks.org/problems/egg-dropping-puzzle-1587115620/1/?category[]=Dynamic%20Programming&page=1&query=category[]Dynamic%20Programmingpage1
public class EggDrop {
/*
Suppose you have N eggs and you want to determine from which floor in a K-floor building you can drop an egg such
 that it doesn't break.
 You have to determine the minimum number of attempts you need in order find the critical floor in the worst case
 while using the best strategy.There are few rules given below.

An egg that survives a fall can be used again.
A broken egg must be discarded.
The effect of a fall is the same for all eggs.
If the egg doesn't break at a certain floor, it will not break at any floor below.
If the eggs breaks at a certain floor, it will break at any floor above.
For more description on this problem see wiki page

Example 1:

Input:
N = 2, K = 10
Output: 4
 */
    // 13 30  N eggs

    static int eggDrop(int numEggs, int numFloors) {
        Integer dp[][] = new Integer[numEggs + 1][numFloors + 1];
        for (int iEgg = 0; iEgg < numEggs + 1; iEgg++) {  //boundary for first floor
            dp[iEgg][0] = 0;
            dp[iEgg][1] = 1;
        }
        for (int iFloor = 0; iFloor < numFloors + 1; iFloor++) {  //boundary for first egg
            dp[0][iFloor] = 0;
            dp[1][iFloor] = iFloor;
        }
        for (int iEgg = 2; iEgg < numEggs + 1; iEgg++) {
            for (int iFloor = 2; iFloor < numFloors + 1; iFloor++) {
                int minCost = Integer.MAX_VALUE;
                for (int iIteratedFloor = 2; iIteratedFloor < iFloor + 1; iIteratedFloor++) {
                    int smashCost = dp[iEgg - 1][iIteratedFloor - 1] + 1;
                    int saveCost = dp[iEgg][iFloor - iIteratedFloor] + 1;
                    int maxCost = Integer.max(smashCost, saveCost);
                    minCost = Integer.min(minCost, maxCost);
                }
                dp[iEgg][iFloor] = minCost;
            }
        }
        //  print2DArray(dp);
        return dp[numEggs][numFloors];
    }

    @Test
    public void testprint2DArray() {
        Integer array[][] = new Integer[2][2];
        for (int i1 = 0; i1 < array.length; i1++) {
            for (int i2 = 0; i2 < array[0].length; i2++) {
                array[i1][i2] = i1 + i2;
            }
        }
        print2DArray(array);
    }

    static <T> void print2DArray(T array[][]) {
        for (int i1 = 0; i1 < array.length; i1++) {
            System.out.println();
            for (int i2 = 0; i2 < array[0].length; i2++) {
                System.out.print(array[i1][i2] + " ");
            }
        }
    }

    @Test
    public void simpleTest() {
        Assert.assertEquals(4, eggDrop(2, 10));
        Assert.assertEquals(3, eggDrop(3, 5));
    }


    @Test
    public void testUnlim(){
        int res = eggDropUnlimited(36);
        System.out.println(res);
    }

    static int eggDropUnlimited( int k) {
        int[] floors = new int[k + 1];
        floors[0] = 0;
        floors[1] = 1;
        for (int upperFloor = 2; upperFloor < k + 1; upperFloor++) {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < upperFloor / 2 + 1; i++) {
                final int smashed = floors[upperFloor - i] + 1;
                final int survive = floors[i]+1;
                min = Integer.min(min, Integer.max(smashed, survive));
            }
            floors[upperFloor] = min;
        }
        return floors[k];
    }

}
