package com.slava.binarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

//https://practice.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/0
public class KthTwoSortedArrays {

    @Test
    public void simple3() {
        int arr1[] = {1, 2, 3};
        int arr2[] = {1, 2, 3};
        int k = 3;
        long res = kthElement(arr1, arr2, arr1.length, arr2.length, k);
        Assert.assertEquals(2, res);
    }


    @Test
    public void simple() {
        int arr1[] = {2, 3, 6, 7, 9};
        int arr2[] = {1, 4, 8, 10};
        int k = 5;
        long res = kthElement(arr1, arr2, arr1.length, arr2.length, k);
        Assert.assertEquals(6, res);
    }

    @Test
    public void simple1() {
        int arr1[] = {2, 3, 4, 6, 7, 9};
        int arr2[] = {1, 4, 8, 10};
        int k = 5;
        long res = kthElement(arr1, arr2, arr1.length, arr2.length, k);
        Assert.assertEquals(4, res);
    }

    @Test
    public void nonOverlapped() {
        int arr1[] = {1, 2, 3, 4, 5};
        int arr2[] = {6, 7, 8};
        int k = 7;
        long res = kthElement(arr1, arr2, arr1.length, arr2.length, k);
        Assert.assertEquals(7, res);
    }

    @Test
    public void dublicates() {
        int arr1[] = {1, 1, 1, 1};
        int arr2[] = {0, 0, 0, 0, 0};
        int k = 3;
        long res = kthElement(arr1, arr2, arr1.length, arr2.length, k);
        Assert.assertEquals(0, res);
    }

    // 123 123  3
    public long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        int i1 = k * arr1.length / (arr1.length + arr2.length);
        int iLow = 0;
        int iHigh = arr1.length - 1;
        int i2 = 0;
        while (iLow <= iHigh) {
            i2 = Arrays.binarySearch(arr2, arr1[i1]);
            i2 = i2 > -1 ? i2 : Math.abs(i2) - 1;
            if ((i1 + i2) == k - 1) {
                return Integer.min(arr1[i1], arr2[i2]);
            }
            if ((i1 + i2) == k - 2) {
                return Integer.max(arr1[i1], arr2[i2]);
            }
            if ((i1 + i2) > k - 1) {
                iHigh = i1 - 1;
            } else {
                iLow = i1 + 1;
            }
            i1 = (iLow + iHigh) >> 1;
        }
        // there are no in the 1 array
        if (i1 >= 0) {
            return arr2[i2 + k - i1 - 2];
        } else {
            return arr2[k - 1];
        }
    }
}
