package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
//https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/
public class MinAbsSum {

    @Test
    public void testSolutionPerf() {
        int nDim = 1000;
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
        A[0] = 2;
        A[1] = 5;
        A[2] = 5;
        A[3] = 5;
        int correct = 0;
        int res = solution(A);
        Assert.assertEquals(correct, res);
    }

    static int[] dp;
    public static int sum(int[] array) {
        int sum = 0;
        for(int i : array) {
            sum += i;
        }

        return sum;
    }

    public static int solution(int[] A) {
        int arrayLength = A.length;
        int maxInArray = 0;
        for (int i = 0; i < arrayLength; i++) {
            A[i] = Math.abs(A[i]);
            maxInArray = Math.max(A[i], maxInArray);
        }
        int S = sum(A);
        dp = new int[S + 1];
        int[] count = new int[maxInArray + 1];
        for (int i = 0; i < arrayLength; i++) {
            count[A[i]] += 1;
        }
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < maxInArray + 1; i++) {  // walk throw count
            if (count[i] > 0) {
                for(int j = 0; j < S; j++) {
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
