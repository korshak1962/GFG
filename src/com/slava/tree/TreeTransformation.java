package com.slava.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://practice.geeksforgeeks.org/problems/fbcd1787378ed396a8f24b47872cbc0ad2624f1d/1
public class TreeTransformation {
    //-1 0 0 0 0 2 2 5   => min -1 ang change rest parents value
    //-1 0 0 0 0 2 2
    //-1 0 0 0 0 0

    @Test
    public void test1() {
        int N = 4;
        int[] p = {-1, 0, 1, 1};
        int res = solve(N, p);
        System.out.println(res);
        Assert.assertEquals(res, 0);
    }

    @Test
    public void test2() {
        int N = 5;
        int[] p = {-1, 0, 0, 1, 1};
        int res = solve(N, p);
        System.out.println(res);
        Assert.assertEquals(res, 1);
    }

    public static int solve(int N, int[] p) {
        if (N < 4) return 0;
        Map<Integer, Integer> parents = new HashMap<>();
        for (int a : p) {
            parents.computeIfPresent(a, (k, v) -> v + 1);
            parents.computeIfAbsent(a, (v) -> 1);
        }
        if (parents.get(0) == 1) {
            return parents.size() - 3;
        }
        return parents.size() - 2;
    }
}
