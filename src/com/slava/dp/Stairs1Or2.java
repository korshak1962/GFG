package com.slava.dp;

public class Stairs1Or2 {
/*
https://practice.geeksforgeeks.org/problems/count-ways-to-nth-stairorder-does-not-matter1322/1
There are N stairs, and a person standing at the bottom wants to reach the top. The person can climb either 1 stair or 2 stairs at a time. Count the number of ways, the person can reach the top (order does not matter).
Note: Order does not matter means for n=4 {1 2 1},{2 1 1},{1 1 2} are considered same.

Example 1:

Input:
N = 4
Output: 3
Explanation: You can reach 4th stair in
3 ways.
3 possible ways are:
1, 1, 1
1, 1, 2
2, 2
Example 2:

Input:
N = 5
Output: 3
Explanation:
You may reach the 5th stair in 3 ways.
The 3 possible ways are:
1, 1, 1, 1, 1
1, 1, 1, 2
1, 2, 2
 */

    // function to find number of ways
    Long countWaysClever(int m){
        return (long) (m / 2 + 1);
    }

    // function to find number of ways
    Long countWaysDP(int m) {
        long dp[] = new long[m + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < m + 1; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[m];
    }


}
