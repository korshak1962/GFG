package com.slava.majorityElement;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
// https://app.codility.com/programmers/lessons/8-leader/equi_leader/
// keyword Leader element
//https://app.codility.com/demo/results/trainingA37VXB-6KJ/
public class EquiLeader {
/*
A non-empty array A consisting of N integers is given.
The leader of this array is the value that occurs in more than half of the elements of A.
An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
For example, given array A such that:
    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
we can find two equi leaders:

0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
 */
    @Test
    public void test() {
        int[] A = new int[1];
        A[0] = 0;
        int res = solution(A);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test1() {
        int[] A = new int[6];
        A[0] = 4;
        A[1] = 3;
        A[2] = 4;
        A[3] = 4;
        A[4] = 4;
        A[5] = 2;
        int res = solution(A);
        Assert.assertEquals(2, res);
    }

    public int solution(int[] A) {
        int toReturn = 0;
        Integer leader = null;
        int threshold = A.length / 2;
        Map<Integer, Integer> numToQuintityPresent = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Integer value = fillMapWith(A[i], numToQuintityPresent);
            if (leader == null && value > threshold) {
                leader = A[i];
            }
        }
        if (leader == null) {
            return toReturn;
        }
        int nLeader = numToQuintityPresent.get(leader);
        int nLeader1 = 0;
        for (int i = 0; i < A.length; i++) {
            if (leader == A[i]) {
                nLeader--;
                nLeader1++;
            }
            if (nLeader > (A.length - i - 1) / 2 && nLeader1 > (i + 1) / 2) {
                toReturn++;
            }
        }
        return toReturn;
    }

    private Integer fillMapWith(int key, Map<Integer, Integer> map) {
        Integer value = map.get(key);
        if (value == null) {
            value = 1;
        } else {
            value++;
        }
        map.put(key, value);
        return value;
    }

}
