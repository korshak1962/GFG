package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;
//https://www.geeksforgeeks.org/problems/max-sum-in-the-configuration/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class MaxSumRotatedArray {

    long max_sum(int[] a, int n) {
        long rotated = 0;
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            rotated += (long) a[i] * i;
            sum += a[i];
        }
        long res = rotated;
        for (int i = 1; i < a.length; i++) {
            // previous - shifted without element at zero index (wasn't included)
            // + add element at 0 n-1 times (now included).
            rotated = rotated - (sum - a[i - 1]) + ((long) a[i - 1] * (a.length - 1));
            res = Long.max(rotated, res);
        }
        return res;
    }

    @Test
    public void test() {
        int a[] = {8, 3, 1, 2};
        Assert.assertEquals(29, max_sum(a, a.length));
    }

}
