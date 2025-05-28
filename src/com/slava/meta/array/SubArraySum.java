package com.slava.meta.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1?page=1&company[]=Facebook&category[]=Arrays&sortBy=
public class SubArraySum {

    static ArrayList<Integer> subarraySum(int[] arr, int target) {
        Map<Long, Integer> sumToIndex = new HashMap<>();
        Long subSum = 0L;
        sumToIndex.put(subSum, 0);
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            subSum += arr[i];
            final int fi = i + 1;
            sumToIndex.computeIfAbsent(subSum, k -> fi);
            Integer startIndex = sumToIndex.get(subSum - target);
            if (startIndex != null) {
                if (arr[startIndex] == 0 && startIndex != 0) {
                    toReturn.add(startIndex);
                } else toReturn.add(startIndex + 1);
                toReturn.add(i + 1);
                return toReturn;
            }
        }
        toReturn.add(-1);
        return toReturn;
    }

    @Test
    public void test() {
        int[] arr = {38, 8, 38, 4, 34, 46, 10, 10, 9, 22, 39, 23};
        List<Integer> res = subarraySum(arr, 197);
        System.out.println(res);
    }

}
