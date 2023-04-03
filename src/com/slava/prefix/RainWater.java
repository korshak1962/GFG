package com.slava.prefix;

//https://practice.geeksforgeeks.org/problems/trapping-rain-water-1587115621/1?company%5B%5D=Google&company%5B%5D=Google&page=1&query=company%5B%5DGooglepage1company%5B%5DGoogle
public class RainWater {

    // arr: input array
    // n: size of array
    // Function to find the trapped water between the blocks.
    static long trappingWater(int arr[], int n) {
        long result = 0;
        int[] prefix = new int[arr.length];
        int[] postfix = new int[arr.length];

        prefix[0] = arr[0];
        for (int i=1;i<arr.length;i++){
            prefix[i]=Integer.max(prefix[i-1],arr[i]);
        } //3,3,3,3,3,4

        postfix[arr.length-1] = arr[arr.length-1];
        for (int i=arr.length-2;i>=0;i--){
            postfix[i]=Integer.max(postfix[i+1],arr[i]);
        }  //4,4,4,4,4,4

        int cur=0;
        for (int i=1;i<arr.length-1;i++){
            cur=Integer.min(prefix[i-1],postfix[i+1])-arr[i];
            result+=cur>0?cur:0;   //0,3,3,1,3,0
        }
        return result;
    }

}
