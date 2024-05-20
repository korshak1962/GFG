package com.slava.binarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FindStartIndex {

    private static <T> int getMaxInd(List<? extends Comparable<? super T>> numsWithDupSorted, T key) {
        int indToInsert = Collections.binarySearch(numsWithDupSorted, key);
        while (indToInsert < numsWithDupSorted.size() - 1 && numsWithDupSorted.get(indToInsert + 1).equals(key)) {
            List<? extends Comparable<? super T>> subList = numsWithDupSorted.subList(indToInsert + 1, numsWithDupSorted.size());
            indToInsert += Collections.binarySearch(subList, key) + 1;
        }
        return indToInsert;
    }

    private static <T> int getMinInd(List<? extends Comparable<? super T>> numsWithDupSorted, T key) {
        int curInd = Collections.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && numsWithDupSorted.get(curInd - 1).equals(key)) {
            List<? extends Comparable<? super T>> subList = numsWithDupSorted.subList(0, curInd);
            curInd = Collections.binarySearch(subList, key);
        }
        return curInd;
    }

    @Test
    public void testDubSearchMax() {
        List<Integer> intList = List.of(1, 1, 2, 2, 3, 3, 4, 4, 4);
        int res = getMaxInd(intList, 4);
        System.out.println(res);
    }
    @Test
    public void testDubSearchMin() {
        List<Integer> intList = List.of(1, 1, 2, 2, 3, 3, 4, 4, 4);
        int res = getMinInd(intList, 4);
        System.out.println(res);
    }


    int findQnty(int[] array, int target) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int lIndex = findStartIndex(array, target);
        if (lIndex < 0) {
            return 0;
            //  lIndex = Math.abs(lIndex) - 1;
        }
        int hIndex = findStartIndex(array, target + 1);
        if (hIndex < 0) {
            hIndex = Math.abs(hIndex) - 1;
        }
        return hIndex - lIndex;
    }

    @Test
    public void testInsert() {
        int target = 5;
        int[] nums = {1, 2, 3, 7, 9};
        //System.out.println(Arrays.binarySearch(array, 5));
        int res = findStartIndex(nums, target);
        System.out.println(res);
        Assert.assertEquals(-4, res);
        //   Arrays.binarySearch(nums,1,3,4);
    }

    @Test
    public void testDescending() {
        Integer[] arr = new Integer[]{5, 6, 2, 9, 7};
        Arrays.sort(arr, Collections.reverseOrder()); //sorting the array in descending order
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.binarySearch(arr, 2, Comparator.reverseOrder())); // Searches of for 2 in the array.
    }

    @Test
    public void testChar() {
        char[] arr = new char[]{'a', 'c', 'f', 'h'};
        int index = Arrays.binarySearch(arr, 'b');
        System.out.println(index);
    }

    @Test
    public void testInsertLast() {
        int target = 15;
        int[] nums = {1, 2, 3, 7, 9};
        //System.out.println(Arrays.binarySearch(array, 5));
        int res = findStartIndex(nums, target);
        System.out.println(res);
        Assert.assertEquals(-6, res);
    }

    @Test
    public void testInsertFirstst() {
        int target = 0;
        int[] nums = {1, 2, 3, 7, 9};
        //System.out.println(Arrays.binarySearch(array, 5));
        int res = findStartIndex(nums, target);
        System.out.println(res);
        Assert.assertEquals(-1, res);
    }

    int findStartIndex(int[] nums, int target) {
        if (target<nums[0]) return -1;
        if (target>nums[nums.length-1]) return -(nums.length+1);
        int lower = 0;
        int upper = nums.length - 1;
        int index = (lower + upper) >> 1;
        while (upper > lower) {
            if (nums[index] > target) {
                upper = index - 1;
            }
            if (nums[index] < target) {
                lower = index + 1;
            }
            if (nums[index] == target) {
                if (index == 0) return index;
                if (nums[index - 1] < target) return index;
                upper = index - 1;
            }
            index = (lower + upper) >> 1;
        }
        if (nums[index] != target) return - (index + 2);
        return index;
    }

    int findStartIndexTrue(int[] nums, int target) {
        int lower = 0;
        int upper = nums.length - 1;
        int index = -1;
        while (lower <= upper) {
            int mid = (lower + upper) >> 1;
            if (nums[mid] == target) {
                index = mid;
            }
            if (nums[mid] >= target) {
                upper = mid - 1;
            } else {
                lower = mid + 1;
            }
        }
        if (index < 0) {
            return -1 * (lower + 1);
        }
        return index;
    }


    int findStartIndex1(int[] array, int target) {    //    6
        int lowIndex = 0;
        if (array[lowIndex] == target) {
            return lowIndex;
        } else if (array[lowIndex] > target) {
            return -1;
        }
        int highIndex = array.length - 1; // 7
        if (array[highIndex] < target) {
            return -1 * (highIndex + 2);
        }
        if (array[highIndex] == target) {
            return highIndex;
        }
        int targetIndex = (highIndex + lowIndex) >>> 1;  //3
        do {
            if (array[targetIndex] > target) {
                highIndex = targetIndex;
            } else if (array[targetIndex] < target) {
                lowIndex = targetIndex;  // 3
            } else if (array[targetIndex - 1] < target) {
                return targetIndex;
            } else {
                highIndex = targetIndex;
            }
            targetIndex = (highIndex + lowIndex) >>> 1;  //5
        } while (targetIndex > lowIndex);
        if (array[targetIndex] != target) {
            return -1 * (targetIndex + 2);
        }
        return targetIndex;
    }


    // @Test
    public void testShiftN() {
        Short a = 2;
        System.out.println(Integer.toBinaryString(a));
        a = -2;
        System.out.println(Integer.toBinaryString(a));
    }


    // @Test
    public void testShift() {
        Short a = 60;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a >> 1));
        System.out.println(a >> 1);
        System.out.println(Integer.toBinaryString(a << 1));
        System.out.println(a << 1);
        a = -60;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a >> 1));
        System.out.println(a >> 1);
        System.out.println(Integer.toBinaryString(a << 1));
        System.out.println(a << 1);
    }

    //1 2 3 5 5 5 7 9
    @Test
    public void testQnty() {
        int target = 5;
        int[] array = {1, 2, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 9};
        //System.out.println(Arrays.binarySearch(array, 5));
        int res = findQnty(array, target);
        System.out.println(res);
        Assert.assertEquals(9, res);
        int index = findStartIndex(array, 5);
        System.out.println(index);
        Assert.assertEquals(3, index);
    }

    @Test
    public void testQnty1() {
        int target = 5;
        int[] array = {1, 5};
        int res = findQnty(array, target);
        System.out.println(res);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testQnty2() {
        int target = 5;
        int[] array = {5, 5};
        int res = findQnty(array, target);
        System.out.println(res);
        Assert.assertEquals(2, res);
    }

    @Test
    public void testEmpty() {
        int target = 5;
        int[] array = {1};
        int res = findQnty(array, target);
        System.out.println(res);
        Assert.assertEquals(0, res);
    }

    @Test
    public void testQnty3() {
        int target = 5;
        int[] array = {5, 6};
        int res = findQnty(array, target);
        System.out.println(res);
        Assert.assertEquals(1, res);
    }
    @Test
    public void t() {
       // Arrays.binarySearch()
      //  Collections.binarySearch()
    }

}
