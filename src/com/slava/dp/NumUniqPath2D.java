package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class NumUniqPath2D {

    /*

    https://practice.geeksforgeeks.org/problems/number-of-unique-paths/0

    Given a M X N matrix with your initial position at the top-left cell, find the number of possible unique paths to reach the bottom-right cell of the matrix from the initial position.

Note: Possible moves can be either down or right at any point in time, i.e., we can move to matrix[i+1][j] or matrix[i][j+1] from matrix[i][j].

Input:
The first line contains an integer T, depicting the total number of test cases. Then following T lines contain two integers M and N depicting the size of the grid.

Output:
Print the number of unique paths to reach the bottom-right cell from the top-left cell.

Expected Time Complexity: O(M*N).
Expected Auxiliary Space: O(M*N).

Constraints:
1 ≤ T ≤ 30
1 ≤ M ≤ 20
1 ≤ N ≤ 20

Example:
Input:
2
2 2
3 4

Output:
2
10
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int nX = sc.nextInt();
            int nY = sc.nextInt();
            System.out.println(findNumPaths(nX, nY));
        }
    }

    static int findNumPaths(int nx, int ny) {
        int dp[][] = new int[nx][ny];
        for (int ix = 0; ix < nx; ix++) {
            dp[ix][0] = 1;
        }
        for (int iy = 0; iy < ny; iy++) {
            dp[0][iy] = 1;
        }
        for (int ix = 1; ix < nx; ix++) {
            for (int iy = 1; iy < ny; iy++) {
                dp[ix][iy] = dp[ix - 1][iy] + dp[ix][iy - 1];
            }
        }
        return dp[nx - 1][ny - 1];
    }

    @Test
    public void testDP() {
        int res = findNumPaths(2,2);
        Assert.assertEquals(2, res);
    }

    @Test
    public void testDP1() {
        int res = findNumPaths(3,4);
        Assert.assertEquals(10, res);
    }

}
