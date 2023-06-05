package com.slava.slidingWindow;

import org.junit.Test;

import java.util.ArrayList;

//https://practice.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1?page=1&sortBy=submissions
public class TwoPointers {

    @Test
    public void simple1(){
       int N = 5, S = 12;
       int A[] = {1,2,3,7,5};
        ArrayList<Integer> res = subarraySum(A,N,S);
        System.out.println(res);
    }

    //Function to find a continuous sub-array which adds up to a given number.
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        ArrayList<Integer> result = new ArrayList<>();

        if (n < 1) {
            result.add(-1);
            return result;
        }

        int left = 0;
        int right = 0;
        long sum = arr[0];

        while (right < n) {
            if (sum == s) {
                result.add(left + 1);
                result.add(right + 1);
                return result;
            }
            if (sum < s && right < n - 1) {
                right++;
                sum += arr[right];
            } else {
                sum -= arr[left];
                if (left<n) left++;
            }
            if (left > right) {
                right = left;
                if (left<n)   sum = arr[left];
            }
        }

        result.add(-1);
        return result;
    }


}
