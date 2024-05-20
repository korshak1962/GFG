package com.slava;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Pairs {
    static long countPairs(int n, int[] arr, int k) {
        long res = 0;
        long[] restQnty = new long[k];
        for (int i = 0; i < arr.length; i++) {
            restQnty[arr[i] % k] += 1;
        }
        for (long val : restQnty) {
            if (val > 0) {
                res += getCombinations(val);
            }
        }
        return res;
    }

    static Map<Long, Long> factorials = new HashMap<>();
    static Map<Long, Long> combinations = new HashMap<>();

    static long maxFact = 0;

    static {
        factorials.put(0L, 1L);
    }

    static long getCombinations(long num) {
        if (combinations.get(num) == null) {
            combinations.put(num, combinations((int)num, 2));
        }
        return combinations.get(num);
    }


    public static long combinations(int n, int k) {
        if (k > n) {
            return 0; // Handle invalid cases (k cannot be greater than n)
        }
        if (k == 0 || k == n) {
            return 1; // Base cases (1 combination when k is 0 or n)
        }
        long result = 1;
        for (int i = n; i > n - k; i--) {
            result *= i;
        }
        for (int i = 2; i <= k; i++) {
            result /= i;
        }
        return result;
    }

    static long calcCombinations(long from, long qnty) {
        if (qnty > from) return 0;
        if (from > maxFact) {
            calcFactorial(from);
        }
        long res = (factorials.get(from) / (factorials.get(qnty) * factorials.get(from - qnty)));
        return res;
    }

    static Long calcFactorial(long num) {
        while (maxFact < num) {
            maxFact++;
            factorials.put(maxFact, factorials.getOrDefault(maxFact - 1, 1L) * maxFact);
        }
        return factorials.get(num);
    }

    @Test
    public void testCalcCombinations() {
        Assert.assertEquals(1, calcCombinations(1, 1));
        Assert.assertEquals(1, calcCombinations(2, 2));
        Assert.assertEquals(3, calcCombinations(3, 2));


    }

    @Test
    public void simple() {
        int n = 10;
        int[] arr = {5, 5, 10, 10, 2, 1, 7, 8, 9, 5};
        int k = 3;
        Assert.assertEquals(16, countPairs(n, arr, k));
    }

    @Test
    public void simple1() {
        int n = 3;
        int[] arr = {3, 7, 11};
        int k = 4;
        Assert.assertEquals(3, countPairs(n, arr, k));
    }

    static long countPairs1(int n, int[] arr, int k) {
        int res = 0;
        int max = arr[0];
        Map<Integer, Integer> numberToTimes = new HashMap<>();
        Map<Integer, Integer> repeatedToTimes = new HashMap<>();
        Integer times;
        for (int i = 0; i < arr.length; i++) {
            max = Integer.max(max, arr[i]);
            if ((times = numberToTimes.get(arr[i])) != null) {
                numberToTimes.put(arr[i], times + 1);
                repeatedToTimes.put(arr[i], times + 1);
            } else {
                numberToTimes.put(arr[i], 1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int curValue = arr[i] + k;
            while (curValue <= max) {
                times = numberToTimes.get(curValue);
                if ((times) != null) {
                    res += times;
                }
                curValue += k;
            }
        }
        for (int val : repeatedToTimes.values()) {
            res += getCombinations(val);
        }
        return res;
    }
}
