package com.slava.greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;
//https://practice.geeksforgeeks.org/problems/find-optimum-operation/0
public class MinOperToNum {

    /*
    https://practice.geeksforgeeks.org/problems/find-optimum-operation/0

    You are given a number N. You have to find the number of operations required to reach N from 0. You have 2 operations available:

Double the number
Add one to the number
Input:
The first line of input contains an integer T denoting the number of test cases.
Then T test cases follow. Each test case contains an integer N.

Output:
For each test case, in a new line, print the minimum number of operations required to reach N from 0.

Constraints:
1<=T<=100
1<=N<=104

Example:
Input:
2
8
7
Output:
4
5

Explanation:
Testcase1:
Input  : N = 8
Output : 4
0 + 1 = 1, 1 + 1 = 2, 2 * 2 = 4, 4 * 2 = 8
Testcase2:
Input  : N = 7
Output : 5
0 + 1 = 1, 1 + 1 = 2, 1 + 2 = 3, 3 * 2 = 6, 6 + 1 = 7
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            System.out.println(findMinOper(sc.nextInt()));
        }
    }

    static int findMinOper(int n) {
        int res = 1;
        while (n != 1) {
            res++;
            if (n % 2 != 0) {
                n--;
            } else {
                n = n >> 1;
            }
        }
        return res;
    }

    @Test
    public void testDP() {
        int res = findMinOper(8);
        Assert.assertEquals(4, res);
    }
    @Test
    public void testDP1() {
        int res = findMinOper(7);
        Assert.assertEquals(5, res);
    }


}
