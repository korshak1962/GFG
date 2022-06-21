package com.slava.dp;

import org.junit.Test;

public class TransformStringToAnotherString {

    @Test
    public void testGeek() {
        String A = "AABBBC";
        String B = "ABC";
        System.out.println(transform(A, B));
       // System.out.println(transformOrig(A, B));
    }

    static int transform(String orig, String target){
        int[][] dp = new int[target.length()][orig.length()];
        char p = target.charAt(0);
        int count = 0;
        for(int i = 0; i < orig.length(); i++){
            if(orig.charAt(i) == p) count++;
            dp[0][i] = count;
        }
        printDP(orig, target, dp);
        for(int j = 1; j < target.length(); j++){
            int num = 0;
            for(int i = 1; i < orig.length(); i++){
                if(orig.charAt(i) == target.charAt(j)){
                    num += dp[j-1][i-1];
                }
                dp[j][i] = num;
            }
        }
        printDP(orig, target, dp);
        return dp[target.length()-1][orig.length()-1];
    }

    static int transformOrig(String orig, String target){
        int[][] dp = new int[target.length()][orig.length()];
        char p = target.charAt(0);
        for(int i = 0; i < orig.length(); i++){
            if(orig.charAt(i) == p) dp[0][i]++;
        }
        for(int j = 1; j < target.length(); j++){
            int num = 0;
            for(int i = 0; i < orig.length(); i++){
                if(orig.charAt(i) == target.charAt(j)){
                    if(i == 0) continue;
                    num += dp[j-1][i-1];
                }
                dp[j][i] = num;
            }
        }
        printDP(orig, target, dp);
        return dp[target.length()-1][orig.length()-1];
    }

    private static void printDP(String orig, String target, int[][] dp) {
        for(int i = 0; i < target.length(); i++){
           for(int j = 0; j < orig.length(); j++){
                System.out.print(dp[i][j] + " ");
           }
           System.out.println();
       }
        System.out.println("=====");
    }

}
