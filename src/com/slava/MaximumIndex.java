package com.slava;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
// https://practice.geeksforgeeks.org/problems/maximum-index/0/
public class MaximumIndex {

    /*
    https://practice.geeksforgeeks.org/problems/maximum-index/0/
    Given an array A[] of N positive integers. The task is to find the maximum of j - i subjected to the constraint of A[i] <= A[j].

Input:
The first line contains an integer T, depicting total number of test cases.  Then T test case follows. First line of each test case contains an integer N denoting the size of the array. Next line contains N space separated integeres denoting the elements of the array.

Output:
Print the maximum difference of the indexes i and j in a separtate line.

Constraints:
1 ≤ T ≤ 1000
1 ≤ N ≤ 103
0 ≤ A[i] ≤ 1018

Example:
Input:
1
9
34 8 10 3 2 80 30 33 1

Output:
6

Explanation:
Testcase 1:  In the given array A[1] < A[7] satisfying the required condition(A[i] <= A[j]) thus
giving the maximum difference of j - i which is 6(7-1).
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int nTotal = sc.nextInt();
            int[] origArray = new int[nTotal];
            //   NumToIndex[] numToIndexes = new NumToIndex[nTotal];
            for (int i = 0; i < nTotal; i++) {
                origArray[i] = sc.nextInt();
                // numToIndexes[i] = new NumToIndex(sc.nextLong(), i); N*log(N)
                //  numToIndex.put(sc.nextInt(), i); // doubles!!!
            }
            //int[] indexes = getSortedIndexes(numToIndexes);
          //  System.out.println(findMaxIndexDiff(origArray));
        }
    }

    static private int findMaxIndexDiff(int origArray[]) {
        int[] minIndex = getMinIndexes(origArray);
        int[] maxIndex = getMaxIndexes(origArray);
        int diff = -1;
        int i = 0;
        int j = 0;
        while (i < origArray.length && j < origArray.length) {
            if (minIndex[i] <= maxIndex[j]) {
                diff = Integer.max(diff, j - i);
                j++;
            } else {
                i++;
            }
        }
        return diff;
    }

    @Test
    public void testFindMaxIndexDiff() {
        int[] input = {34, 8, 10, 3, 2, 80, 30, 33, 1};
        int res = findMaxIndexDiff(input);
        Assert.assertEquals(6, res);
    }

    private static int[] getSortedIndexes(NumAndIndex[] numAndIndices) {
        System.out.println(numAndIndices.length);
        long t = System.currentTimeMillis();
        Arrays.sort(numAndIndices);
        System.out.println(" time for sort[Ms]:" + (System.currentTimeMillis() - t));
        int indexes[] = new int[numAndIndices.length];
        for (int i = 0; i < numAndIndices.length; i++) {
            indexes[i] = numAndIndices[i].index;
        }
        return indexes;
    }

    static class NumAndIndex implements Comparable {
        Long num;
        Integer index;

        public NumAndIndex(Long num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            int c1 = this.num.compareTo(((NumAndIndex) o).num);
            if (c1 == 0) {
                return this.index.compareTo(((NumAndIndex) o).index);
            }
            return c1;
        }
    }


    static private int findMaxProfit(int origIndexes[]) {
        int[] minIndex = getMinIndexes(origIndexes);
        int[] maxIndex = getMaxIndexes(origIndexes);
        int maxProfit = 0;
        for (int i = 0; i < minIndex.length; i++) {
            maxProfit = Integer.max(maxProfit, maxIndex[i] - minIndex[i]);
        }
        return maxProfit;
    }

    private static int[] getMaxIndexes(int[] origArray) {
        int maxs[] = new int[origArray.length];
        maxs[maxs.length - 1] = origArray[maxs.length - 1];
        for (int i = maxs.length - 2; i >= 0; i--) {
            maxs[i] = Integer.max(maxs[i + 1], origArray[i]);
        }
        return maxs;
    }

    private static int[] getMinIndexes(int[] origArray) {
        int mins[] = new int[origArray.length];
        mins[0] = origArray[0];
        for (int i = 1; i < mins.length; i++) {
            mins[i] = Integer.min(mins[i - 1], origArray[i]);
        }
        return mins;
    }

    @Test
    public void test() {
        long[] input = {34, 8, 10, 3, 2, 80, 30, 33, 1};
        int res = getRes(input);
        Assert.assertEquals(6, res);
    }

    @Test
    public void testTLE() {
        int n = 9998;
        long[] input = new long[n];
        for (int i = 0; i < n; i++) {
            input[i] = i;
        }
        int res = getRes(input);
        // Assert.assertEquals(6, res);
    }

    private int getRes(long[] input) {
        NumAndIndex[] numAndIndices = new NumAndIndex[input.length];
        for (int i = 0; i < input.length; i++) {
            numAndIndices[i] = new NumAndIndex(input[i], i);
            //  numToIndex.put(sc.nextInt(), i); // doubles!!!
        }
        int res = findMaxProfit(getSortedIndexes(numAndIndices));
        return res;
    }


}
