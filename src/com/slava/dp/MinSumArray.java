package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class MinSumArray {

    /*
    https://practice.geeksforgeeks.org/problems/skip-the-work/0

    Given an array A[ ] denoting the time taken to complete N tasks, determine the minimum amount of time required to finish the tasks considering that you can skip any task, but skipping two consecutive tasks is forbidden.


For example
For the array arr [] = {10, 5, 2, 4, 8, 6, 7, 10}
the output will be  22  (Skip the tasks taking more time ,avoiding consective skipping(10,8,10). Tasks done in: 5+2+4+6+7)


Input
The first line of input contains an integer T denoting the number of test cases. Then T test cases follow.
The first line of each test case contains a positve integer N, denoting the length of the array A[ ].
The second line of each test case contains a N space seprated positve integers, denoting the elements of the array A[ ].


Output
Print out the minimum time taken to complete the tasks.


Constraints
1 <= T <= 100
0 <   N <= 30
0 <= A[ ] <= 1000


Examples

Input
4
4
10 5 7 10
6
5 6 7 8 9 10
2
10 20
5
22 10 15 3 5

Output
12
21
10
13


Expected Complexity
Time: O(N)
Space: O(1)


Explanation:
Test Case 1: {10, 5, 7, 10}
We can skip tasks 0 and 3. Remaining tasks done in 5 + 7 = 12 units of time.
Test Case 2: {5, 6, 7, 8, 9, 10}
We can skip tasks 1, 3 and 5. Remaining tasks done in 5 + 7 + 9 = 21 units of time.
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int length = sc.nextInt();
            int data[] = new int[length];
            for (int i = 0; i < length; i++) {
                data[i] = sc.nextInt();
            }
            System.out.println(findMinWork(data));
        }
    }

    static int findMinWork(int data[]) {
        if (data.length < 2) {
            return 0;
        }
        int dp[] = new int[data.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = data[0];
        dp[1] =  data[1];
        for (int i = 2; i < data.length; i++) {
            dp[i] = Integer.min(dp[i - 2] + data[i], dp[i - 1] + data[i]);
        }
        return Integer.min(dp[data.length - 2], dp[data.length - 1]) ;
    }

    @Test
    public void testDP() {
        int data[] = {10, 5, 7, 10};
        int res = findMinWork(data);
        Assert.assertEquals(12, res);
    }
    @Test
    public void testDP1() {
        int data[] = {333, 673, 664};
        int res = findMinWork(data);
        Assert.assertEquals(673, res);
    }

}
