package com.slava.cyclicSort;

import org.junit.Assert;
import org.junit.Test;

public class SimpleCycklicSort {

    @Test
    public void testNoop() {
        int[] nums={1, 4, 4, 3, 2};
        sort( nums);
        int[] sorted={1, 2, 3, 4, 4};
        Assert.assertArrayEquals(sorted,nums);
    }

    public void sortCyckle(int[] nums) {
        for (int i=0;i<nums.length-1;i++){
            while (nums[i]!=i+1){ //i=2
                swap(nums, i);
            }
        }
    }

     void swap(int[] nums, int i) {
        int num= nums[i];
        nums[i]= nums[num-1];
        nums[num-1]=num;
    }

    @Test
    public void testSwap() {
        int[] nums={2,1};
        sort( nums);
        int[] swaped={1, 2};
        swap(nums,0);
        Assert.assertArrayEquals(swaped,nums);
    }

    public static void sort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j])
                swap(nums, i, j);
            else
                i++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
