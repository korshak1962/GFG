package com.slava.reputation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day1 {

    int[] mergeArrays(int[] A, int[] B) {   // O(N)
        int[] C = new int[A.length + B.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < A.length && j < B.length) {
            if (A[i] <= B[j]) {
                C[k] = A[i];
                i++;
            } else {
                C[k] = B[j];
                j++;
            }
            k++;
        }
        if (i < A.length - 1) {
            for (int ii = i; ii < A.length; ii++) {
                C[k] = A[ii];
                k++;
            }
        }

        if (j < B.length - 1) {
            for (int ii = j; ii < B.length; ii++) {
                C[k] = B[ii];
                k++;
            }
        }
        return C;
    }

/*
    From Rohan Yadav to Everyone 12:06 PM
    https://codeshare.io/zy6ZgE
    From Petr Igrevskiy to Everyone 01:11 PM
    https://codeshare.io/nzXLkE
*/

    // count = 1                                 for (gap Gaps)  {Gap/3}
//
// [1,0,0,1,0,1];

// [1,0,1,0,1,0,1]

// return boolean
// < 100 size

//[1,0] 2
//[0] ->  1
// [0,0,0], 2

//[1,0,0]
//[0,0,1]
//[0,0]


// [0,0,0,0,0,0,0]

    //[1,0]
    public boolean canChange(int[] nums, int n) {
        List<Integer> gaps = new ArrayList<>();
        int numZeros = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0 && nums[i - 1] == 0) {
                numZeros++;
            } else {
                if (numZeros >= 3) {
                    gaps.add(numZeros);
                    numZeros = 1;
                }
            }
        }
        if (numZeros >= 3) {
            gaps.add(numZeros);

        }
        return false;
    }

    @Test
    public void test() {

    }
}
