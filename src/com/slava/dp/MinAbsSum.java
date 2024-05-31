package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/
public class MinAbsSum {

    //https://app.codility.com/demo/results/trainingC4ST83-X6C/  54% performance
    //https://app.codility.com/demo/results/trainingW2WMH2-AYY/  63% performance
    public static int solution2D(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.abs(A[i]);
            sum += A[i];
        }
        if (A.length == 1) {
            return A[0];
        }
        if (A.length == 0) {
            return 0;
        }
        int res = 0;
        int sumUp = sum / 2;
        int maxSum = 0;
        boolean dp[][] = new boolean[A.length][sumUp + 1];
        for (int j = 1; j < sumUp + 1; j++) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == j || (i > 0 && dp[i - 1][j])) {
                    dp[i][j] = true;
                    maxSum = j;
                } else {
                    int subSum = j - A[i];
                    if (subSum > 0 && i > 0 && dp[i - 1][subSum]) {
                        dp[i][j] = true;
                        maxSum = j;
                    }
                }
            }
        }
        int otherSum = sum - maxSum;
        res = Math.abs(maxSum - otherSum);
        return res;
    }

    @Test
    public void testSolutionPerf() {
        int nDim = 20;
        int maxValue = 100;
        int A[] = new int[nDim];
        for (int i = 0; i < nDim; i++) {
            A[i] = (int) (Math.random() * maxValue);
        }
        long time = System.currentTimeMillis();
        int res = solution(A);
        System.out.println("res=" + res + " time=" + (System.currentTimeMillis() - time));
    }

    @Test
    public void testSolution() {
        int A[] = new int[4];
        A[0] = 1;
        A[1] = 5;
        A[2] = -20;
        A[3] = 2;
        int correct = 12;
        int res = solution(A);
        Assert.assertEquals(correct, res);
    }

//https://app.codility.com/demo/results/trainingN5CJKC-GU3/ 100%
    public static int solution(int[] A) {
        int[] dp;
        int arrayLength = A.length;
        int maxInArray = 0;
        for (int i = 0; i < arrayLength; i++) {
            A[i] = Math.abs(A[i]);
            maxInArray = Math.max(A[i], maxInArray);
        }
        int S = Arrays.stream(A).sum();
        dp = new int[S + 1];
        int[] count = new int[maxInArray + 1];
        for (int i = 0; i < arrayLength; i++) {
            count[A[i]] += 1;
        }
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < maxInArray + 1; i++) {  // walk throw count
            if (count[i] > 0) {
                for (int j = 0; j < S; j++) {
                    if (dp[j] >= 0) {
                        dp[j] = count[i];
                    } else if (j >= i && dp[j - i] > 0) {
                        dp[j] = dp[j - i] - 1;
                    }
                }
            }
        }
        int result = S;
        for (int i = 0; i < Math.floor(S / 2) + 1; i++) {
            if (dp[i] >= 0) {
                result = Math.min(result, S - 2 * i);
            }
        }
        return result;
    }


    public int solution01(int[] a) {
        if (a.length == 0) return 0;
        if (a.length == 1) return a[0];
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i]);
        }
        int[] indices = new int[a.length];
        indices[0] = 0;
        int half = sum / 2;
        int localSum = Math.abs(a[0]);
        int minLocalSum = Integer.MAX_VALUE;
        int placeIndex = 1;
        for (int i = 1; i < a.length; i++) {
            if (localSum < half) {
                if (Math.abs(2 * minLocalSum - sum) > Math.abs(2 * localSum - sum))
                    minLocalSum = localSum;
                localSum += Math.abs(a[i]);
                indices[placeIndex++] = i;
            } else {
                if (localSum == half)
                    return Math.abs(2 * half - sum);
                if (Math.abs(2 * minLocalSum - sum) > Math.abs(2 * localSum - sum))
                    minLocalSum = localSum;
                if (placeIndex > 1) {
                    localSum -= Math.abs(a[indices[placeIndex--]]);
                    i = indices[placeIndex];
                }
            }
        }
        return (Math.abs(2 * minLocalSum - sum));
    }


    public int solution2(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.abs(A[i]);
            sum += A[i];
        }
        int result = sum % 2;
        final int halfSum = sum / 2;
        return result;
    }


    public int solutionBad(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.abs(A[i]);
            sum += A[i];
        }
        int result = sum % 2;
        final int halfSum = sum / 2;
        Set<Integer> setOfSum = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            Set<Integer> setToAdd = new HashSet<>();
            for (int s : setOfSum) {
                int toAdd = s + A[i];
                if (Math.abs(toAdd - halfSum) == result) {
                    return result;
                } else if (toAdd < halfSum) {
                    setToAdd.add(toAdd);
                }
            }
            if (Math.abs(A[i] - halfSum) == result) {
                return result;
            }
            setOfSum.add(A[i]);
            setOfSum.addAll(setToAdd);
        }
        //System.out.println("halfSum=" + halfSum + " setOfSum.size()=" + setOfSum.size());
        return Arrays.stream(A).map((i) -> Math.abs(halfSum - i)).min().getAsInt();
    }

}
