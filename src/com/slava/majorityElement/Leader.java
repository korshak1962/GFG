package com.slava.majorityElement;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Leader {

    @Test
    public void test() {
        int[] A = new int[1];
        A[0] = 0;
        int res = solution(A);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test1() {
        int[] A = new int[3];
        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        int res = solution(A);
        Assert.assertEquals(-1, res);
    }

    @Test
    public void test2() {
        int[] A = new int[8];
        A[0] = 3;
        A[1] = 4;
        A[2] = 3;
        A[3] = 2;
        A[4] = 3;
        A[5] = -1;
        A[6] = 3;
        A[7] = 3;
        int res = solution(A);
        Assert.assertTrue(res > -1);
    }

    public int solution(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Integer value = map.get(A[i]);
            if (value == null) {
                value = 1;
            } else {
                value++;
            }
            if (value > A.length / 2) {
                return i;
            }
            map.put(A[i], value);
        }
        return -1;
    }

    public int solution1(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.computeIfPresent(A[i], (k, v) -> ++v);
            map.computeIfAbsent(A[i], (k) -> 1);
            if (map.get(A[i]) > A.length / 2) {
                return i;
            }
        }
        return -1;
    }

}
