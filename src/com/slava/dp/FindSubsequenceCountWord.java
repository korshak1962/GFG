package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

public class FindSubsequenceCountWord {

    static int findSubsequenceCount(String S, String T) {
        int m = T.length();
        int n = S.length();

        // T can't appear as a subsequence in S
        if (m > n)
            return 0;

        // dp[i][j] stores the count of
        // occurrences of T(1..i) in S(1..j).
        int dp[][] = new int[m + 1][n + 1];

        // Initializing first column with
        // all 0s. An emptystring can't have
        // another string as suhsequence
        for (int i = 1; i <= m; i++)
            dp[i][0] = 0;

        // Initializing first row with all 1s.
        // An empty string is subsequence of all.
        for (int j = 0; j <= n; j++)
            dp[0][j] = 1;

        // Fill dp[][] in bottom up manner
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If last characters don't match,
                // then value is same as the value
                // without last character in S.
                if (T.charAt(i - 1) != S.charAt(j - 1))
                    dp[i][j] = dp[i][j - 1];

                    // Else value is obtained considering two cases.
                    // a) All substrings without last character in S
                    // b) All substrings without last characters in
                    // both.
                else
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
            }
        }

        /* uncomment this to print matrix dp
        for (int i = 1; i <= m; i++, cout << endl)
            for (int j = 1; j <= n; j++)
                System.out.println ( dp[i][j] +" "); */
        return dp[m][n];
    }

    // Driver code to check above method
    @Test
    public  void test() {
        String S = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco ";
        String T =        "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxp";

        //String T = "ge";
        //String S = "geeksforgeeks";
        System.out.println(findSubsequenceCount(S, T));
        Assert.assertEquals(4,findSubsequenceCount(S, T));
    }

}
