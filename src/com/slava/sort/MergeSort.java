package com.slava.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

//https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class MergeSort {

    static long[] array;
    static long inversions = 0;


    static long inversionCount(long A[], long N) {
        mergeSort(A);
        return inversions;
    }

    static  public void  mergeSort(long[] array) {
        inversions = 0;
        MergeSort.array = array;
        MergeSort.sort(0, array.length - 1);
    }

    static void sort(int start, int end) {
        int mid = (start + end) / 2;
        if (end > start) {
            sort(start, mid);
            sort(mid + 1, end);
        }
        merge(start, mid, end);
    }

    static void merge(int start, int mid, int end) {
        if (end == start) return;
        int curIndex = start;
        int left = start;
        int right = mid + 1;
        int leftSize = mid - start + 1;
        long[] arrayLeft = new long[leftSize];
        System.arraycopy(array, left, arrayLeft, 0, leftSize);
        int rightSize = end - mid;
        long[] arrayRight = new long[rightSize];
        System.arraycopy(array, right, arrayRight, 0, rightSize);
        int iLeft = 0;
        int iRight = 0;
        while (curIndex <= end) {
            if (arrayLeft[iLeft] > arrayRight[iRight]) {
                array[curIndex] = arrayRight[iRight];
                inversions += arrayLeft.length - iLeft;
                if (iRight < arrayRight.length - 1) {
                    iRight++;
                } else {
                    while (iLeft < arrayLeft.length) {
                        array[++curIndex] = arrayLeft[iLeft];
                        iLeft++;
                    }
                }
            } else {
                array[curIndex] = arrayLeft[iLeft];
                if (iLeft < arrayLeft.length - 1) {
                    iLeft++;
                } else {
                    while (iRight < arrayRight.length) {
                        array[++curIndex] = arrayRight[iRight];
                        iRight++;
                    }
                }
            }
            curIndex++;
        }

    }

    void swap(int to, int from) {
        long tmp = array[to];
        array[to] = array[from];
        array[from] = tmp;
    }

    @Test
    public void test00() {
        long A[] = {3, 2, 1};
        mergeSort(A);
        System.out.println(Arrays.toString(array));
        Assert.assertEquals(3, inversions);
    }

    @Test
    public void test01() {
        long A[] = {3, 2, 1, 8, 9, 11, -1};
        mergeSort(A);
        System.out.println(Arrays.toString(array));
    }
    @Test
    public void testSolution1() {
        long A[] = {-1, 6, 3, 4, 7, 4};
        mergeSort(A);
        Assert.assertEquals(4, inversions);
    }
}
