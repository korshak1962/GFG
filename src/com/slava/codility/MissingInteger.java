package com.slava.codility;

import org.junit.Test;
//https://app.codility.com/demo/results/training2JTQEY-U5Z/
public class MissingInteger {

    public static final int MAX_VALUE = 1000000 + 1;

    public int solution(int[] A) {
        boolean B[] = new boolean[MAX_VALUE];
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                B[A[i]] = true;
            }
        }
        for (int i = 1; i < MAX_VALUE; i++) {
            if (!B[i]) {
                return i;
            }
        }
        return -1;
    }


    @Test
    public void test() {
        int[] A = {-1, 0, 1, 2, 4, 5, 8};
        int res = solution(A);
        System.out.println(res);
    }
}