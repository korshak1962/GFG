package com.slava.arraysTasks;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?page=1&difficulty=Basic,Medium&sortBy=submissions
public class LongestSubarray {
    public int longestSubarray(int[] arr, int k) {
        Map<Long, Integer> sumToIndex = new HashMap<>();
        Integer indexFromMap;
        Long curSum = 0L;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];
            if (sumToIndex.get(curSum) == null) {
                sumToIndex.put(curSum, i);
            }
            indexFromMap = sumToIndex.get(curSum - k);
            if (indexFromMap != null) {
                result = Integer.max(result, i - indexFromMap);
            }
            if (curSum == k) {
                result = i + 1;
            }

        }
        return result;
    }
}
