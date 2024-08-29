package com.slava.arraysTasks;

import org.junit.Test;

import java.util.ArrayList;

public class TwoIndexes {

    //https://www.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
    public static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        int start = 0;
        int sum = 0;
        ArrayList<Integer> forRet = new ArrayList<>(2);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            while (sum > s && start<i) {
                sum -= arr[start];
                start++;
            }
            if (sum == s) {
                forRet.add(++start);
                forRet.add(++i);
                return forRet;
            }
        }
        forRet.add(-1);
        return forRet;   // code here
    }

    @Test
    public void test() {
        int[] arr = {15, 2, 4, 8, 9, 5, 10, 23};
        int sum = 23;
        System.out.println(subarraySum(arr, arr.length, sum));
    }  // code here

    @Test
    public void test1() {
        int[] arr = {135,101,170,125,79,159,163,65,106,146,82,28,162,92,196,143,28,37,192,5,103,154,93,183,22,117,119,96,48,127,172,139,70,113,68,100,36,95,104,12,123,134};
        int sum = 468;
        System.out.println(subarraySum(arr, arr.length, sum));
    }

    @Test
    public void test2() {
        int[] arr = {1,2,3,4};
        int sum = 0;
        System.out.println(subarraySum(arr, arr.length, sum));
    }
}
