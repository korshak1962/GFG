package com.slava.maps;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/problems/array-pair-sum-divisibility-problem3257/1
public class PairSumDivisibility {

    public boolean canPair(int[] nums, int k) {
        if (nums.length % 2 != 0) return false;
        Map<Integer, Integer> intToTimes = new HashMap<>();
        for (int num : nums) {
            intToTimes.computeIfPresent(num % k, (key, v) -> ++v);
            intToTimes.computeIfAbsent(num % k, (key) -> 1);
        }
        Integer zeroKey = intToTimes.get(0);
        if (zeroKey != null) {
            if (zeroKey % 2 != 0) return false;
            else intToTimes.remove(0);
        }
        while (!intToTimes.isEmpty()) {
            Map.Entry<Integer, Integer> next = intToTimes.entrySet().iterator().next();
            Integer pairValTimes = intToTimes.get(Math.abs(k - next.getKey()));
            if (next.getValue() == pairValTimes) {
                intToTimes.remove(Math.abs(k - next.getKey()));
                intToTimes.remove(next.getKey());
            } else return false;
        }
        return true;
    }

    private boolean canPair(int[] nums, int k, boolean mask[]) {
        return false;
    }

}
