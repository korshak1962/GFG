package com.slava.combination;

import org.junit.Assert;
import org.junit.Test;
//https://practice.geeksforgeeks.org/problems/number-of-positive-integral-solutions2115/1
// x+y=z  - find all combinations
public class PositiveNumbers {
    long posIntSol(String s) {
        String[] splited=s.split("=");
        int target = Integer.valueOf(splited[1]);
        int qnty = splited[0].split("\\+").length;
        if (target < qnty) return 0;
        return getCombination(qnty, target);
    }
    long getCombination(int qnty, int target) {
        if (qnty == 1) return 1;
        long res = 0;
        for (int i = 1; i <= target - qnty + 1; i++) {
            res += getCombination(qnty - 1, target - i);
        }
        return res;
    }

    //qnty-1<=target-i    i<=target-qnty+1
    @Test
    public void test1() {
       long res=getCombination(2, 5);
        Assert.assertEquals(res, 4);
    }

    @Test
    public void test2() {
        long res=posIntSol("a+b=5");
        Assert.assertEquals(4, res);
    }
}
