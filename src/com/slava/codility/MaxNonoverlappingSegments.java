package com.slava.codility;

import org.junit.Assert;
import org.junit.Test;

//https://app.codility.com/demo/results/trainingJSK9FX-ZFY/
public class MaxNonoverlappingSegments {
    public int solution(int[] A, int[] B) {
        int res = 1;
        if (A.length==0) return 0;
        int currentEnd = B[0];

        for (int i=1;i<A.length;i++){
           if (A[i]> currentEnd) {
               res++;
               currentEnd=B[i];
           }
        }
        return res;
    }


    @Test
    public void simple() {
        int[] A = new int[5];
        int[] B = new int[5];

        A[0] = 1;
        B[0] = 5;
        A[1] = 3;
        B[1] = 6;
        A[2] = 7;
        B[2] = 8;
        A[3] = 9;
        B[3] = 9;
        A[4] = 9;
        B[4] = 10;
        int res = solution(A, B);
        Assert.assertEquals(3,res);
    }
}
