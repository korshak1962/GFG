package com.slava.visa;

import org.junit.Assert;
import org.junit.Test;

//https://leetcode.com/discuss/interview-question/790459/Visa-OA-Hackerrank-New-Grad-2019-2020
public class BalancingElement {

    public int getNumberOfBalancingElements(int[] input) {
        int res = 0;
        int sumOdds[] = new int[input.length];
        int sumEvens[] = new int[input.length];
        sumEvens[0] = input[0];
        sumOdds[0] = 0;
        for (int i = 1; i < input.length; i++) {
            if (i % 2 == 0) {
                sumEvens[i] = sumEvens[i - 1] + input[i];
                sumOdds[i] = sumOdds[i - 1];
            } else {
                sumEvens[i] = sumEvens[i - 1];
                sumOdds[i] = sumOdds[i - 1] + input[i];
            }
        }
        if (sumOdds[sumOdds.length-1] == sumEvens[sumEvens.length-1] - sumEvens[0]) {
            res++;
        }
        for (int i = 1; i < input.length; i++) {
            if (i % 2 == 0) {
                if (sumOdds[i] + (sumEvens[sumEvens.length-1] - sumEvens[i]) == sumEvens[i - 1] + (sumOdds[sumEvens.length-1] - sumOdds[i]))
                    res++;
            } else {
                if (sumEvens[i] + (sumOdds[sumOdds.length-1] - sumOdds[i]) == sumOdds[i - 1] + (sumEvens[sumEvens.length-1] - sumEvens[i]))
                    res++;
            }
        }
        return res;
    }

    @Test
    public void testGetNumberOfBalancingElements() {
        int[] firstD = {5, 5, 2, 5, 8};
        int res = getNumberOfBalancingElements(firstD);
        Assert.assertEquals(2, res);
    }

}
