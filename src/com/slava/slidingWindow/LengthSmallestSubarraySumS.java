package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

public class LengthSmallestSubarraySumS {

   /*
Given an array of positive numbers and a positive number ‘S,’
find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’.
 Return 0 if no such subarray exists.

Input: [2, 1, 5, 2, 3, 2], S=7
Output: 2
Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].
common
 */


    @Test
    public void testNoop() {
        int[] inputAray = {2, 1, 5, 2, 3, 2};
        Assert.assertEquals(2, findLength(inputAray, 7));
    }

    int findLength(int[] inputAray, int targetSum) {
        int result = Integer.MAX_VALUE;
        int curResult = 0;
        int curSum = 0;
        int leftIndex = 0;
        for (int i = 0; i < inputAray.length; i++) {
            curResult++;
            curSum += inputAray[i];
            while (curSum >= targetSum) {
                result = Integer.min(curResult, result);
               // System.out.println("curSum=" + curSum + " i=" + i + " leftIndex=" + leftIndex + " result=" + result);
                curSum -= inputAray[leftIndex];
                leftIndex++;
                curResult--;
            }
        }
        if (result == Integer.MAX_VALUE) return 0;
        return result;
    }


}
