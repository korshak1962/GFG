package com.slava.binarySearch;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.*;

public class FindStartIndexCodepad {

    @Test
    public void testNoop() {
        int arr[] = {1, 2, 3, 5, 5, 5, 6, 7};
        int res = findFirst(arr, 5);
        Assert.assertTrue(res == 3);
    }

    public static int findFirst(int arr[], int target) {
        int lowIndex = 0;
        int hiIndex = arr.length - 1;
        int resultIndex = (hiIndex + lowIndex) >> 1;
        while (hiIndex > lowIndex) {
            if (arr[resultIndex] >= target) {
                if ((arr[resultIndex] == target) && (resultIndex == 0 || arr[resultIndex - 1] < target)) return resultIndex;
                hiIndex = resultIndex - 1;
            } else {
                lowIndex = resultIndex + 1;
            }
            System.out.println("hiIndex=" + hiIndex + " resultIndex=" + resultIndex + " lowIndex=" + lowIndex);
            resultIndex = (hiIndex + lowIndex) >> 1;
        }
        if (arr[resultIndex] != target) resultIndex = -1 * (resultIndex + 1);
        return resultIndex;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        int arr[] = {1, 2, 3, 5, 5, 5, 6, 7};
        int res = findFirst(arr, 5);
        System.out.println("res=" + res);
        Assert.assertTrue(res == 3);

        int arr1[] = {5, 5, 5, 5, 5, 5, 6, 7};
        res = findFirst(arr1, 5);
        System.out.println("res=" + res);
        Assert.assertTrue(res == 0);


        res = findFirst(arr1, 7);
        System.out.println("res=" + res);
        Assert.assertTrue(res == 7);

        res = findFirst(arr1, 9);
        System.out.println("res=" + res);
        Assert.assertTrue(res == -8);

        res = findFirst(arr1, 0);
        System.out.println("res=" + res);
        Assert.assertTrue(res == -1);

    }
}

