package com.slava;

import org.junit.Test;

import java.util.Arrays;
//https://www.geeksforgeeks.org/problems/-rearrange-array-alternately-1587115620/1?page=1&difficulty=Medium,Hard&sortBy=submissions

// fast solution used modulo technics to store two values in in one element
// keep one value in dividend other is in reminder
public class RearrangeArrayAlt {

    public static void rearrange(long arr[], int n) {
        int max = (int) Arrays.stream(arr).max().getAsLong() + 1;
        int maxInd = arr.length - 1;
        long maxValue = arr[maxInd];
        int minInd = 0;
        long minValue = arr[minInd];
        int ind = 0;
        while (ind < arr.length) {
            if (maxInd >= ind) {
                maxValue = arr[maxInd--];
            } else {
                if (maxInd >= 0) {
                    maxValue = arr[maxInd--] % max;
                }
            }
            arr[ind++] += ((maxValue % max) * max);

            if (minInd >= ind) {
                minValue = arr[minInd++];
            } else {
                if (minInd < arr.length) {
                    minValue = arr[minInd++] % max;
                }
            }
            if (ind<arr.length)
            arr[ind++] += ((minValue % max) * max);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] / max;
        }
    }

    static long arr[];

    public static void rearrangeByReverse(long arr[], int n) {
        RearrangeArrayAlt.arr = arr;
        for (int i = 0; i < arr.length; i++) {
            reverseArray(i, arr.length - 1);
        }
    }

    private static void reverseArray(int start, int end) {
        int k = end;
        for (int i = start; i <= k; i++, k--) { //1, 2, 3, 4, 5, 6
            swap(i, k);
        }
    }

    static void swap(int from, int to) {
        long tmp = arr[to];
        arr[to] = arr[from];
        arr[from] = tmp;
    }

    @Test
    public void test() {
        long arr[] = {1, 2, 3, 4, 5, 6};
        rearrangeByReverse(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        long arr1[] = {1, 2, 3, 4, 5, 6};
        rearrange(arr1, arr1.length);
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    public void test1() {
        long arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rearrangeByReverse(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        long arr1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rearrange(arr1, arr1.length);
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    public void test2() {
        long arr1[] = {10};
        rearrange(arr1, arr1.length);
        System.out.println(Arrays.toString(arr1));
    }
}
