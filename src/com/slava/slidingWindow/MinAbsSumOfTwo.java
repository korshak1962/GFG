package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;

import java.util.Arrays;
//https://app.codility.com/programmers/lessons/15-caterpillar_method/min_abs_sum_of_two/
public class MinAbsSumOfTwo {

    /*
    Let A be a non-empty array consisting of N integers.

The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.

For example, the following array A:

  A[0] =  1
  A[1] =  4
  A[2] = -3
has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2).
The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2.
The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5.
The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2.
The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8.
The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1.
The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6.
Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.
     */


//https://app.codility.com/demo/results/trainingEZ7QMH-9JC/   100%
    @Test
    public void testSolution() {
        int A[] = new int[5];
        A[0] = -8;
        A[1] = 4;
        A[2] = 5;
        A[3] = -10;
        A[4] = 3;
        int correct = 3;
        int res = solution(A);
        Assert.assertEquals(correct, res);
    }

    public int solution(int[] A) {
        Arrays.sort(A);
        long result = Math.abs(A[0] + A[A.length - 1]);
        int lowIndex = 0;
        int highIndex = A.length - 1;
        while (true) {
            result = Long.min(Math.abs(A[lowIndex] + A[highIndex]), result);
            if (lowIndex == highIndex) {
                break;
            }
            if (Math.abs(A[lowIndex]) > Math.abs(A[highIndex])) {
                lowIndex++;
            } else {
                highIndex--;
            }
        }
        return (int)result;
    }
}
