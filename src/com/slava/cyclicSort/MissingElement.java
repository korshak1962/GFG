package com.slava.cyclicSort;

//https://www.geeksforgeeks.org/problems/missing-number-in-array1416/1?page=1&sortBy=submissions
public class MissingElement {
    int arr[];

    int missingNum(int arr[]) {
        this.arr = arr;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] < arr.length && arr[i] != i + 1) {
                swap(i, arr[i] - 1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) return i + 1;
        }
        return arr[arr.length - 1] + 1;
    }

    void swap(int from, int to) {
        int tmp = arr[to];
        arr[to] = arr[from];
        arr[from] = tmp;
    }
}
