package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//https://practice.geeksforgeeks.org/problems/palindromic-patitioning4845/1
public class AllPalindromInString {
    //@Test
    public void testPP() {
        String str = "abbasdyny";
        List<String> result = palindromePartitioningOld(str);
        for (String pal : result) {
            System.out.println(pal);
        }
    }

    @Test
    public void test00() {
        String str = "a";
        int result = palindromicPartition(str);
        Assert.assertEquals(0, result);
    }

    @Test
    public void test0() {
        String str = "aaaabbbb";
        int result = palindromicPartition(str);
        Assert.assertEquals(1, result);
    }

    @Test
    public void test01() {
        String str = "ab";
        int result = palindromicPartition(str);
        Assert.assertEquals(1, result);
    }

    @Test
    public void test02() {
        String str = "aba";
        int result = palindromicPartition(str);
        Assert.assertEquals(0, result);
    }

    @Test
    public void test03() {
        String str = "abba";
        int result = palindromicPartition(str);
        Assert.assertEquals(0, result);
    }

    @Test
    public void test04() {
        String str = "abbac";
        int result = palindromicPartition(str);
        Assert.assertEquals(1, result);
    }

    @Test
    public void test05() {
        String str = "abbacd";
        int result = palindromicPartition(str);
        Assert.assertEquals(2, result);
    }

    @Test
    public void test06() {
        String str = "cabbacd";
        int result = palindromicPartition(str);
        Assert.assertEquals(1, result);
    }

    @Test
    public void test07() {
        String str = "caabbacd";
        int result = palindromicPartition(str);
        Assert.assertEquals(4, result);
    }

    @Test
    public void test08() {
        String str = "bbbab";
        int result = palindromicPartition(str);
        Assert.assertEquals(1, result);
    }

    @Test
    public void test() {
        String str = "aaabba";
        int result = palindromicPartition(str);
        Assert.assertEquals(1, result);
    }


    @Test
    public void test1() {
        String str = "ababbbabbababa";
        int result = palindromicPartition(str);
        Assert.assertEquals(3, result);
    }

    static String inputString;
    static int dp[][];

    static int palindromicPartition(String str) {
        inputString = str;
        dp = new int[inputString.length()][inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            for (int j = 0; j < inputString.length(); j++) {
                dp[i][j] = -1;
            }
        }
        final int minCutsCorrect = getMinCutsCorrect(0, inputString.length() - 1);
        return minCutsCorrect;
    }

    static int getMinCutsCorrect(int start, int end) {
        if (dp[start][end] != -1) return dp[start][end];
        if (start >= end) {
            return dp[start][end] = 0;
        }
        if (end - start == 1) {
            if (inputString.charAt(start) != inputString.charAt(end)) {
                return dp[start][end] = 1;
            } else return dp[start][end] = 0;
        }
        if (inputString.charAt(start) == inputString.charAt(end)
                && getMinCutsCorrect(start + 1, end - 1) == 0) {
            return dp[start][end] = 0;
        }
        int min = Integer.MAX_VALUE;
        //System.out.println("start=" + start + " end=" + end + " min=" + min);
        for (int i = start + 1; i < end; i++) {
            int leftCuts = getMinCutsCorrect(start, i);
            int rightCuts = getMinCutsCorrect(i + 1, end);
            min = Integer.min(leftCuts + rightCuts + 1, min);
            //System.out.println("i=" + i + " leftCuts=" + leftCuts + " leftCuts=" + leftCuts + " min=" + min);
        }
        return dp[start][end] = min;
    }

    static int palindromicPartition0(String str) {
        if (str.length() == 1) return 1;
        if (str.length() == 2) {
            if (str.charAt(0) == str.charAt(1)) return 0;
            else return 2;
        }
        int minOdd = Integer.min(palindromicPartition(str.substring(0, str.length() - 1)),
                palindromicPartition(str.substring(1))) + 1;
        int minEven = palindromicPartition(str.substring(1, str.length() - 1));
        if (str.charAt(0) != str.charAt(str.length() - 1)) minEven = +2;
        return Integer.min(minEven, minOdd);
    }

    private static List<String> palindromePartitioningOld(String s) {
        List<String> result = new ArrayList<>();
        if (s == null)
            return result;
        if (s.length() <= 1) {
            result.add(s);
            return result;
        }
        int length = s.length();
        int[][] dp = new int[length][length];
        // len is length, lBound is index of left boundary, rBound is index of right boundary
        for (int len = 1; len <= length; len++) {
            for (int lBound = 0; lBound <= length - len; lBound++) {
                int rBound = lBound + len - 1;
                if (s.charAt(lBound) == s.charAt(rBound)) {
                    if (len == 1 || len == 2) {
                        dp[lBound][rBound] = 1;
                    } else {
                        dp[lBound][rBound] = dp[lBound + 1][rBound - 1];
                    }
                    if (dp[lBound][rBound] == 1) {
                        result.add(s.substring(lBound, rBound + 1));
                    }
                } else {
                    dp[lBound][rBound] = 0;
                }
            }
        }
        return result;
    }

    static int minPalPartion(String str) {
        // Get the length of the string
        int n = str.length();

        /* Create two arrays to build the solution
           in bottom up manner
           C[i][j] = Minimum number of cuts needed
                     for palindrome partitioning
                     of substring str[i..j]
           P[i][j] = true if substring str[i..j] is
                     palindrome, else false
           Note that C[i][j] is 0 if P[i][j] is
           true */
        int[][] C = new int[n][n];
        boolean[][] P = new boolean[n][n];

        int i, j, k, L; // different looping variables

        // Every substring of length 1 is a palindrome
        for (i = 0; i < n; i++) {
            P[i][i] = true;
            C[i][i] = 0;
        }

        /* L is substring length. Build the solution in
         bottom up manner by considering all substrings
         of length starting from 2 to n. The loop
         structure is same as Matrx Chain Multiplication
         problem (
        See https:// www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/ )*/
        for (L = 2; L <= n; L++) {
            // For substring of length L, set different
            // possible starting indexes
            for (i = 0; i < n - L + 1; i++) {
                j = i + L - 1; // Set ending index

                // If L is 2, then we just need to
                // compare two characters. Else need to
                // check two corner characters and value
                // of P[i+1][j-1]
                if (L == 2)
                    P[i][j] = (str.charAt(i) == str.charAt(j));
                else
                    P[i][j] = (str.charAt(i) == str.charAt(j)) && P[i + 1][j - 1];

                // IF str[i..j] is palindrome, then
                // C[i][j] is 0
                if (P[i][j] == true)
                    C[i][j] = 0;
                else {
                    // Make a cut at every possible
                    // localtion starting from i to j,
                    // and get the minimum cost cut.
                    C[i][j] = Integer.MAX_VALUE;
                    for (k = i; k <= j - 1; k++)
                        C[i][j] = Integer.min(C[i][j],
                                C[i][k] + C[k + 1][j] + 1);
                }
            }
        }

        // Return the min cut value for complete
        // string. i.e., str[0..n-1]
        return C[0][n - 1];
    }

}
