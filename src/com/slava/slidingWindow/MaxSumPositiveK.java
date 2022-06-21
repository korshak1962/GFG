package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;
//https://www.educative.io/courses/grokking-the-coding-interview/JPKr0kqLGNP
public class MaxSumPositiveK {
    /*
    Input: [2, 3, 4, 1, 5], k=2
    Output: 7
    Explanation: Subarray with maximum sum is [3, 4].
     */
    @Test
    public void testNoop() {
        int[] inputArray = {2, 3, 4, 1, 5};
        Assert.assertEquals(7, getMaxOfSubArray(inputArray, 2));
    }


    int getMaxOfSubArray(int[] inputArray, int k) {
        if (inputArray.length < k) return -1;
        int startIndex = 0;
        int endIndex = startIndex + k;
        int curSum = 0;
        for (int i = startIndex; i < endIndex; i++) {
            curSum += inputArray[i];
        }
        int result = curSum;
        while (endIndex < inputArray.length) {
            curSum += inputArray[endIndex];
            curSum -= inputArray[startIndex];
            result = Integer.max(curSum, result);
            endIndex++;
            startIndex++;
        }
        return result;
    }

}
