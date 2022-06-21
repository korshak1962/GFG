package com.slava.dp;

import org.junit.Test;

import java.util.Scanner;

public class UniqBsts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        int length;
        for (int it = 0; it < nTest; it++) {
            length = sc.nextInt();
            System.out.println(findUniqBsts(length));
        }
    }

    static int findUniqBsts(int length) {
        int dp[] = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int k = 2; k < length + 1; k++) {
            for (int i = 0; i < k; i++) {
                    dp[k] += dp[i] * dp[k - i - 1]; //dp[2]+dp[0]; dp[1]+dp[1];dp[0]+dp[2]
            }
        }
        return dp[length];
    }

    @Test
    public void testGeek() {
        System.out.println(UniqBsts.findUniqBsts(5));
    }

}
