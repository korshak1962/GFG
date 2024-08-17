package com.slava.binarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class KthSmallestAbsoluteDifference {

    private static int arr[];

    public long kthDiff(int arr[], int n, int k) {
        Arrays.sort(arr);
        this.arr = arr;
        int dMax = arr[arr.length - 1] - arr[0];
        int dMin = 0;
        int d = (dMax - dMin) / 2;
        while (dMax > dMin) {
            int numOfSmallest = getNumOfSmallest(d);
            if (numOfSmallest < k) {
                dMin = d + 1;
            } else if (numOfSmallest >= k) {
                dMax = d;
            }
            d = dMin + (dMax - dMin) / 2;
        }
        return d;
    }

    int getNumOfSmallest(int d) {
        int res = 0;
        int indD = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            while (indD < arr.length && arr[indD] - arr[i] <= d) {
                indD++;
            }
            res += indD - i - 1;
            //    if (res > k) return Integer.MAX_VALUE;
        }
        return res;
    }

    @Test
    public void testGetNumOfSmallest() {
        int[] a = {1, 2, 3, 4};
        KthSmallestAbsoluteDifference.arr = a;
        Assert.assertEquals(3, getNumOfSmallest(1));
    }

    @Test
    public void testGetNumOfSmallest0() {
        int[] a = {0};
        KthSmallestAbsoluteDifference.arr = a;
        Assert.assertEquals(0, getNumOfSmallest(1));
    }

    @Test
    public void testGetNumOfSmallest01() {
        int[] a = {0, 255};
        KthSmallestAbsoluteDifference.arr = a;
        Assert.assertEquals(0, getNumOfSmallest(1));
    }

    @Test
    public void testGetNumOfSmallest02() {
        int[] a = {0, 255};
        KthSmallestAbsoluteDifference.arr = a;
        Assert.assertEquals(1, getNumOfSmallest(300));
    }


    @Test
    public void test() {
        int A[] = {1, 2, 3, 4};
        int k = 3;
        Assert.assertEquals(1, kthDiff(A, A.length, k));
    }

    @Test
    public void test1() {
        int A[] = {10, 10};
        int k = 1;
        Assert.assertEquals(0, kthDiff(A, A.length, k));
    }

    @Test
    public void test2() {
        int A[] = {10, 10, 13, 101};
        int k = 4;
        Assert.assertEquals(88, kthDiff(A, A.length, k));
    }

    @Test
    public void test3() {
        int A[] = {10, 10, 13, 101};
        int k = 2;
        Assert.assertEquals(3, kthDiff(A, A.length, k));
    }

    @Test
    public void test4() {
        int A[] = {410, 349, 203, 733, 46, 538, 782, 176, 681, 828, 282, 378, 227, 802, 801, 774, 368, 261, 32, 910};
        int k = 20;
        Assert.assertEquals(46, kthDiff(A, A.length, k));
    }

    @Test
    public void testGetNumOfSmallest4A() {
        int A[] = {410, 349, 203, 733, 46, 538, 782, 176, 681, 828, 282, 378, 227, 802, 801, 774, 368, 261, 32, 910};
        Arrays.sort(A);
        KthSmallestAbsoluteDifference.arr = A;
        Assert.assertEquals(20, getNumOfSmallest(46));
    }

}
