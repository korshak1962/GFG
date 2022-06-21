package com.slava.binarySearch;

import org.junit.Test;

import java.util.*;

//https://leetcode.com/problems/count-of-smaller-numbers-after-self/
public class SmallerInTheRight {
    /*
    You are given an integer array nums and you have to return a new counts array. The counts array has
    the property where counts[i] is the number of smaller elements to the right of nums[i].
Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
     */

    //Arrays.binarySearch

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> numsSorted = new ArrayList(nums.length);
        Integer[] forReturn = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int foundIndex = findStartIndex(numsSorted, nums[i]);
            if (foundIndex < 0) {
                foundIndex = Math.abs(foundIndex) - 1;
            }
            forReturn[i] = foundIndex;
            numsSorted.add(foundIndex, nums[i]);
        }
        return Arrays.asList(forReturn);
    }

    int findStartIndex(List<Integer> nums, int target) {
        int lower = 0;
        int upper = nums.size() - 1;
        int index = -1;
        while (lower <= upper) {
            int mid = (lower + upper) >> 1;
            if (nums.get(mid) == target) {
                index = mid;
            }
            if (nums.get(mid) >= target) {
                upper = mid - 1;
            } else {
                lower = mid + 1;
            }
        }
        if (index < 0) {
            return -1 * (lower + 1);
        }
        return index;
    }

    public List<Integer> countSmaller0(int[] nums) {
        NavigableMap<Integer, Integer> numToIndex = new TreeMap<>();
        Integer[] forReturnA = new Integer[nums.length];
        if (nums.length > 0) forReturnA[nums.length - 1] = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            numToIndex.computeIfPresent(nums[i], (k, v) -> {
                return ++v;
            });
            numToIndex.putIfAbsent(nums[i], 1);
            if (i > 0) {
                int res = 0;
                SortedMap<Integer, Integer> integerIntegerSortedMap = numToIndex.headMap(nums[i - 1]);
                for (Integer val : integerIntegerSortedMap.values()) {
                    res += val;
                }
                forReturnA[i - 1] = res;
            }
        }
        return Arrays.asList(forReturnA);
    }

    @Test
    public void test() {
        int[] nums = {5, 2, 6, 1};
        System.out.println(countSmaller(nums));
    }

}
