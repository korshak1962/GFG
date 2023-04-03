package com.slava.dp;

import org.junit.Test;

//https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {

    @Test
    public void test1() {
        String res = longestPalindrome("aacabdkacaa");
        System.out.println(res);
    }

    Boolean dp[][];
    char[] chars;

    public String longestPalindrome(String s) {
        dp = new Boolean[s.length()][s.length()];
        chars = s.toCharArray();
        int maxPolyLength = 0;
        int maxFrom = 0, maxTo = 0;
        for (int from = 0; from < s.length(); from++) {
            for (int to = from; to < s.length(); to++) {
                if ((to - from) <= maxPolyLength) continue;
                if (dp[from][to] == null) dp[from][to] = fillDp(from, to);
                if (dp[from][to] && ((to - from) > maxPolyLength)) {
                    maxPolyLength = to - from;
                    maxFrom = from;
                    maxTo = to;
                }
            }
        }
      /*  for (int from = 0; from < s.length(); from++) {
            for (int to = from; to < s.length(); to++) {
                System.out.print(dp[from][to] +" ");
            }
            System.out.println("");
        }
       */
        return s.substring(maxFrom, maxTo + 1);
    }
    boolean fillDp(int from, int to) {
        if (chars[from] != chars[to]) return false;
        if (to - from < 3) return true;
        return fillDp(from + 1, to - 1);
    }
}
