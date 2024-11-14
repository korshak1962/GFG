package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Leader {

    @Test
    public void test() {
        int[] A = {0};
        int res = solution(A);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test1() {
        int[] A = {1,2,3};
        int res = solution(A);
        Assert.assertEquals(-1, res);
    }

    @Test
    public void test2() {
        int[] ints = {3,4,3,2,3,-1,3,3};
        int res = solution(ints);
        Assert.assertEquals(3, res);
    }

    public int solutionOld(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Integer value = map.get(A[i]);
            if (value == null) {
                value = 1;
            } else {
                value++;
            }
            if (value > A.length / 2) {
                return A[i];
            }
            map.put(A[i], value);
        }
        return -1;
    }

    public int solution(int[] ints) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ints.length; i++) {
            map.computeIfPresent(ints[i], (k, v) -> ++v);
            map.computeIfAbsent(ints[i], (k) -> 1);
            if (map.get(ints[i]) > ints.length / 2) {
                return ints[i];
            }
        }
        return -1;
    }

}
