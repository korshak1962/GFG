package com.slava;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://practice.geeksforgeeks.org/problems/d894706c496da5c5a4f45b0360c7f4164c516f83/1
public class PowerfullInteger {

    @Test
    public void test() {
        int n = 3;
        int[][] intervals = {{1, 3}, {4, 6}, {3, 4}};
        int k = 2;
        int res = powerfullInteger(n, intervals, k);
        Assert.assertEquals(4, res);
    }

    @Test
    public void test2() {
        int n = 5;
        int[][] intervals = {
                {16, 21},
                {5, 8},
                {12, 17},
                {17, 29},
                {9, 24}
        };
        int k = 3;
        int res = powerfullInteger(n, intervals, k);
        Assert.assertEquals(21, res);
    }

    @Test
    public void test3() {
        int n = 8;
        int[][] intervals = {
                {14, 26},
                {12, 21},
                {9, 10},
                {10, 21},
                {5, 21},
                {9, 11},
                {9, 15},
                {14, 28},
        };
        int k = 6;
        int res = powerfullInteger(n, intervals, k);
        Assert.assertEquals(15, res);
    }

    public static int powerfullInteger(int n, int interval[][], int k) {
        int[] startSorted = new int[n];
        int[] finishSorted = new int[n];
        for (int i = 0; i < n; i++) {
            startSorted[i] = interval[i][0];
            finishSorted[i] = interval[i][1];
        }
        Arrays.sort(startSorted);
        Arrays.sort(finishSorted);
        int diff = 0;
        int result = -1;
        int iStart = 0;
        int iFinish = 0;
        while (iStart < n && iFinish < n ) {
            if (startSorted[iStart] <= finishSorted[iFinish]) {
                diff++;
                iStart++;
            } else {
                if (diff >= k) {
                    result = finishSorted[iFinish];
                }
                iFinish++;
                diff--;
            }
        }
        while (diff >= k && iFinish < n) {
            result = finishSorted[iFinish];
            iFinish++;
            diff--;
        }
        return result;
    }


    public static int powerfullInteger2(int n, int interval[][], int k) {
        int minStart = Integer.MAX_VALUE;
        int maxEnd = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            minStart = Integer.min(minStart, interval[i][0]);
            maxEnd = Integer.max(maxEnd, interval[i][1]);
        }
        int numToTime[] = new int[maxEnd - minStart + 1];
        for (int i = 0; i < n; i++) {
            for (int currentPosition = interval[i][0]; currentPosition <= interval[i][1]; currentPosition++) {
                numToTime[currentPosition - minStart]++;
            }
        }
        int maxKey = -1;
        for (int i : numToTime) {
            if (numToTime[i] >= k) maxKey = Integer.max(i + minStart, maxKey + minStart);
        }
        return maxKey;
    }


    public static int powerfullInteger1(int n, int interval[][], int k) {
        Map<Integer, Integer> numToTime = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int start = interval[i][0]; start <= interval[i][1]; start++) {
                numToTime.computeIfPresent(start, (key, v) -> v + 1);
                numToTime.computeIfAbsent(start, (key) -> 1);
            }
        }
        Integer maxKey = -1;
        for (Map.Entry<Integer, Integer> entry : numToTime.entrySet()) {
            if (entry.getValue() >= k) maxKey = Integer.max(entry.getKey(), maxKey);
        }
        return maxKey;
    }
}
