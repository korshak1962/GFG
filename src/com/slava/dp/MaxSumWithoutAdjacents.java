package com.slava.dp;


import java.util.Scanner;
//https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
public class MaxSumWithoutAdjacents {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int nTotal = sc.nextInt();
            int[] inArray = new int[nTotal];
            for (int i = 0; i < nTotal; i++) {
                inArray[i] = sc.nextInt();
            }
            System.out.println(getSum(inArray));
        }
        sc.close();
    }


    public static int getSum(int[] inArray) {
        int incl = inArray[0];
        int excl = 0;
        for (int i = 1; i < inArray.length; i++) {         //{5,5,10,100,10,5,10,10};
            int excl_new=Integer.max(excl,incl);
            incl=excl+inArray[i];
            excl=excl_new;
        }
        return Integer.max(incl, excl);
    }


    public static int getSumOld(int[] inArray) {
        if (inArray.length < 3) {
            return 0;
        }
        int previousSum = 0;
        int maxSum = inArray[0];
        int maxIndex = 0;
        for (int i = 1; i < inArray.length; i++) {
            if (i - maxIndex > 1) {
                maxSum += inArray[i];
                maxIndex = i;
            } else {
                int mayBeMax = maxSum + inArray[i] - inArray[i - 1];
                previousSum += inArray[i];
                if (previousSum > Integer.max(maxSum, mayBeMax)) {
                    int temp = maxSum;
                    maxSum = previousSum;
                    previousSum = temp;
                    maxIndex = i;
                }
                if (mayBeMax > maxSum) {
                    maxSum = mayBeMax;
                    maxIndex = i;
                    previousSum = previousSum - inArray[i] + Integer.max(inArray[i - 1], inArray[i - 1]);
                }
            }
        }
        return maxSum;
    }

}
