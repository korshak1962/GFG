package com.slava.binarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class KthSmallestInArray {
    public static int kthSmallest(int[] arr, int l, int r, int k)
    {
        Arrays.sort(arr);
        return arr[k-1];
    }

    @Test
    public void test1(){
        int[] arr = {7, 10, 4, 3, 20, 15};
        int res = kthSmallest( arr, 0, arr.length-1, 3);
        Assert.assertEquals(7,res);
    }
}
