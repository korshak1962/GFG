package com.slava.microFocus;

import org.junit.Test;

import java.net.URL;

public class Kadane {

// given array integers
    // I have to find max Sum of contiguous

    long getMaxSum(int[] inArray) {
        if (inArray.length == 0) return 0;
        long maxSum = inArray[0];
        long currentSum = inArray[0];
        for (int i = 1; i < inArray.length; i++) {
            currentSum = currentSum + inArray[i];
            if (currentSum < inArray[i]) {
                currentSum = inArray[i];
            }
            maxSum = Long.max(currentSum, maxSum);
        }
        return maxSum;
    }


    @Test
    public void test() {
        URL u;
        int[] inArray = {1, 2, -10, 0, 15, 17};
        long res = getMaxSum(inArray);
        System.out.println("res = " + res);
    }

    @Test
    public void testNegative() {
        int[] inArray = {-100, -2, -10, -1000, -15, -17};
        long res = getMaxSum(inArray);
        System.out.println("res = " + res);
    }

    @Test
    public void testEmpty() {
        Double d = Double.NEGATIVE_INFINITY;
        Long l = 1L;
        System.out.println(d.longValue() + l);
        int[] inArray = {};
        long res = getMaxSum(inArray);
        System.out.println("res = " + res);
    }

    // binary


}
