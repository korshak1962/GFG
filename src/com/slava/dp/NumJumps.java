package com.slava.dp;

import java.util.Arrays;
import java.util.Scanner;

public class NumJumps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        int length;
        int[] values;
        for (int it = 0; it < nTest; it++) {
            length = sc.nextInt();
            values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = sc.nextInt();
            }
            System.out.println(findNumJumps(values));
        }
    }

    static int findNumJumps(int[] values) {
        if (values[0] == 0) {
            return -1;
        }
        int[] dp = new int[values.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int k = 0; k < values.length - 1; k++) {
            for (int i = 1; i < values[k] + 1; i++) {
                if ((k + i) < values.length){
                    dp[k + i] = Integer.min(dp[k] + 1, dp[k + i]);
                }
            }
        }
        if (dp[values.length - 1] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[values.length - 1];
    }

}
