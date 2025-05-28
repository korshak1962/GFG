package com.slava.meta.array;

//https://www.geeksforgeeks.org/problems/move-all-zeroes-to-end-of-array0751/1
public class ZerosToRight {
    int[] arr;
    void pushZerosToEnd(int[] arr) {
        this.arr = arr;
        int leftNonZeroIndex = -1;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != 0){
                if (i - leftNonZeroIndex > 1 ) {
                    arr[++leftNonZeroIndex] = arr[i];
                }
                else {
                    leftNonZeroIndex++;
                }
            }
        }
        for (int i = leftNonZeroIndex + 1;i<arr.length;i++){
            arr[i] = 0;
        }
    }

}
