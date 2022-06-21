package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

//https://practice.geeksforgeeks.org/problems/k-sorted-array1610/1
public class kSortedArray {

    @Test
    public void testYes() {
        int[] input = {3, 2, 1, 5, 6, 4};
        Assert.assertEquals("Yes", isKSortedArray(input, 6, 2));
    }

    static String isKSortedArray(int arr[], int n, int k) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        for (int i = 0; i < arr.length; i++) {
            final int iSorted = Arrays.binarySearch(sorted, arr[i]);
            if (Math.abs(i - iSorted) > k) return "No";
        }
        return "Yes";
    }
}

