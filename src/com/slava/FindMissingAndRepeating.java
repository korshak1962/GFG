package com.slava;

import org.junit.Test;

import java.util.Arrays;

//https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class FindMissingAndRepeating {
    int[] findTwoElement(int arr[], int n) {
        int[] result = new int[2];
        result[0] = -1;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != (i + 1) && arr[i] != result[0]) {
                if (arr[arr[i] - 1] == arr[i]) {
                    result[0] = arr[i];
                } else {
                    int tmp = arr[arr[i] - 1];
                    arr[arr[i] - 1] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (result[0] == arr[i] && arr[i] != (i + 1)) {
                result[1] = i + 1;
            }
        }
        return result;
    }

    @Test
    public void test() {
        int arr[] = {2, 2};
        System.out.println(Arrays.toString(findTwoElement(arr, arr.length)));
    }

    @Test
    public void test1() {
        int arr[] = {1, 3, 3};
        System.out.println(Arrays.toString(findTwoElement(arr, arr.length)));  //3 2
    }

    @Test
    public void test2() {
        int arr[] = {13, 33, 43, 16, 25, 19, 23, 31, 29, 35, 10, 2, 32, 11, 47, 15, 34, 46, 30, 26, 41, 18, 5, 17, 37, 39, 6, 4, 20, 27, 9, 3, 8, 40, 24, 44, 14, 36, 7, 38, 12, 1, 42, 12, 28, 22, 45};
        System.out.println(Arrays.toString(findTwoElement(arr, arr.length))); // 12 21
    }

    @Test
    public void test3() {
        int arr[] = {3, 24, 22, 7, 10, 34, 27, 29, 13, 2, 11, 23, 9, 26, 32, 12, 1, 14, 4, 8, 6, 19, 17, 15, 30, 28, 20, 31, 5, 16, 25, 18, 9, 33};
        System.out.println(Arrays.toString(findTwoElement(arr, arr.length)));  // 9 21
    }
}
