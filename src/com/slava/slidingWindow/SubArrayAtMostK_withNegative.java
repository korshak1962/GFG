package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

//https://leetcode.com/discuss/interview-question/758045/
public class SubArrayAtMostK_withNegative {

    @Test
    public void test() {
        int[] sourceArray = {10, 10, 10, -29};
        int target = 3;
        Assert.assertEquals(4, longestSumArray(sourceArray, target));
    }

    @Test
    public void test1() {
        int[] sourceArray = {10, 10, -8, 10, 29};
        int target = 3;
        Assert.assertEquals(2, longestSumArray(sourceArray, target));
    }

    @Test
    public void test2() {
        int[] sourceArray = {5, -10, 7, -20, 57};
        int target = -22;
        Assert.assertEquals(3, longestSumArray(sourceArray, target));
    }

    int longestSumArray(int[] sourceArray, int target) {
        int ans = 0;
        long[] prefixSumMax = new long[sourceArray.length];
        long prefixSum = sourceArray[0];
        prefixSumMax[0] = sourceArray[0];
        for (int i = 1; i < sourceArray.length; i++) {
            prefixSum += sourceArray[i];
            prefixSumMax[i] = Long.max(prefixSumMax[i - 1], prefixSum);
            if (prefixSum <= target) ans = i + 1;
            else { // try to count backWard
                while (i - ans > 0) {
                    if (prefixSumMax[i - ans - 1] - prefixSum < target) break;
                    ans++;
                }
            }
        }
        return ans;
    }


    int longestSumArrayC(int[] sourceArray, int target) {
        int n = sourceArray.length;
        int prefixSum = 0;
        int ans = 0;
        int[] maxOfPrefixSumSoFar = new int[n];
        for (int i = 0; i < n; i++) {
            prefixSum += sourceArray[i];
            if (prefixSum <= target) ans = i + 1;
            else {
                // need prefixSum-T[k]<=target
                // T[k]>=prefixSum-target
                while (i - ans > 0) {
                    boolean diff = maxOfPrefixSumSoFar[i - ans - 1] >= prefixSum - target;
                    if (!diff) break;
                    ans++;
                }
            }
            if (i == 0) maxOfPrefixSumSoFar[i] = prefixSum;
            else maxOfPrefixSumSoFar[i] = Integer.max(maxOfPrefixSumSoFar[i - 1], prefixSum);
        }
        return ans;
    }

}
