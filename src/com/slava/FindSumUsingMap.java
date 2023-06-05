package com.slava;

import java.util.HashMap;
import java.util.Map;

//https://practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1
public class FindSumUsingMap {

    // arr[] : the input array containing 0s and 1s
    // N : size of the input array

    // return the maximum length of the subarray
    // with equal 0s and 1s
    int maxLen(int[] arr, int N) {
        int result = 0;
        int sum = 0;
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, 0);
        for (int i = 0; i < N; i++) {
            if (arr[i] == 0) sum--;
            else sum++;
            sumToIndex.putIfAbsent(sum, i + 1);
            result = Integer.max(result, i - sumToIndex.get(sum));
        }
        if (result != 0)
            return result + 1;
        return 0;
    }
}
