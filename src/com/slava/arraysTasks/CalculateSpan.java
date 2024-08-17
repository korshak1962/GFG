package com.slava.arraysTasks;

import org.junit.Test;

import java.util.Arrays;

public class CalculateSpan {

    public static int[] calculateSpan(int price[], int n) {
        int[] spans = new int[price.length];
        int span = 1;
        spans[0] = span;
        int prevPrice = price[0];
        int prevInd;
        for (int i = 1; i < price.length; i++) {
            span = 1;
            prevInd = i - 1;
            prevPrice = price[prevInd];
            while (prevPrice <= price[i]) {
                span += spans[prevInd];
                prevInd -= spans[prevInd];
                if (prevInd < 0) {
                    break;
                }
                prevPrice = price[prevInd];
            }
            spans[i] = span;
        }
        return spans;
    }

    @Test
    public void test() {
        int price[] = {100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(calculateSpan(price, price.length)));
    }

    @Test
    public void test1() {
        int price[] = {10, 4, 5, 90, 120, 80};
        System.out.println(Arrays.toString(calculateSpan(price, price.length)));
    }

}
