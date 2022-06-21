package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

//https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck/0/?category[]=Dynamic%20Programming&page=1&query=category[]Dynamic%20Programmingpage1
public class CountSubSeqStringInString {
/*
    Input  : abbc
    Output : 3
    Subsequences are abc, abc and abbc
    Input:
babcbbaabcbcbcbaabbccaac

Its Correct output is:
9087

And Your Code's output is:
253

*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            String src = sc.next();
            System.out.println(getNumSeq(src));
        }
    }

    private static String buildPattern(String src) {
        if (true) return "abc";
        Set<Character> patternSet = new LinkedHashSet<>();
        for (char ch : src.toCharArray()) {
            patternSet.add(ch);
        }
        StringBuffer patternBuffer = new StringBuffer();
        for (Character ch : patternSet) {
            patternBuffer.append(ch);
        }
        return patternBuffer.toString();
    }

    static int getNumSeq(String src) {
        String pattern = buildPattern(src);
        int dp[][] = new int[src.length() + 1][pattern.length() + 1];
        for (int i = 0; i < src.length() + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < src.length() + 1; i++) {
            for (int j = 1; j < pattern.length() + 1; j++) {
                boolean charEq=false;
                if (src.charAt(i - 1) == pattern.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    charEq=true;
                }
                if (j > 1 && (src.charAt(i - 1) == pattern.charAt(j - 2))) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    charEq=true;
                }
                  if(!charEq)  dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[src.length()][pattern.length()];
    }

    @Test
    public void test() {
        String src = "abcabc";
        final int expected = 7;
        final int actual = getNumSeq(src);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMe() {
        String src = "aabc";
        final int expected = 3;
        final int actual = getNumSeq(src);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        String src = "babcbbaabcbcbcbaabbccaac";
        final int expected = 9087;
        final int actual = getNumSeq(src);
        Assert.assertEquals(expected, actual);
    }

}
