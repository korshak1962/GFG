package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSeq {

    @Test
    public void testLeet01() {
        String str1 = "ylqpejqbalahwr";
        String str2 = "yrkzavgdmdgtqpg";
        int expected = 3;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void testLeet02() {
        String str1 = "abcba";
        String str2 = "abcbcba";
        int expected = 5;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void test1() {
        String str1 = "ABCDGH";
        String str2 = "AEDFHR";
        int expected = 3;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void test2() {
        String str1 = "ABC";
        String str2 = "AC";
        int expected = 2;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void test3() {
        String str1 = "GEBEOCFUFTSXDIXTIGSIEEHKCHZ";
        String str2 = "DFLILRJQFNXZ";
        int expected = 4;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void test4() {
        String str1 = "AAPAHJWTESPLWEQFMNMYMTQ";
        String str2 = "RELEIN";
        int expected = 4;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void test5() {
        String str1 = "XXPOHRYSQ";
        String str2 = "IXLDPKIVXVITPVHATB";
        int expected = 4;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void test6() {
        String str1 = "LRBBMQBHCDARZOWKKYHIDDQSCDXRJMOWFRXSJYBLDBEFSARCBYNECDYGGXXPKLORELLNMPAPQFWKHOPKMCO";
        String str2 = "QHNWNKUEWHSQMGBBUQCLJJIVSWMDKQTBXIXMVTRRBLJPTNSNFWZQFJMAFADRRWSOFSBCNUVQHFFBSAQXWPQCAC";
        int expected = 25;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    @Test
    public void test7() {
        String str1 = "VYJIMLIVQPDZHFNHEVKSUDVJLRGRCAVXZEHLRQGJHMJQTYZZTJSNOPYAGETJFQIEXQ";
        String str2 = "ROIAYROJHJHGIARCPGRNIY";
        int expected = 10;
        Assert.assertEquals(expected, longestCommonSubsequence(str1, str2));
    }

    static int longestCommonSubsequence(String text1, String text2) {

        List<Character> list1 = text1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> list2 = text2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        list1.retainAll(list2);
        list2.retainAll(list1);
        if (list1.isEmpty() || list2.isEmpty()) return 0;
        StringBuilder sb = new StringBuilder();
        for (char ch : list1) {
            sb.append(ch);
        }
        text1 = sb.toString();
        sb.setLength(0);
        for (char ch : list2) {
            sb.append(ch);
        }
        text2 = sb.toString();
        if (text2.length() > text1.length()) {
            String tmp = text2;
            text2 = text1;
            text1 = tmp;
        }

        int dp[][] = new int[text2.length()][text1.length()];
        if (text1.charAt(0) == text2.charAt(0)) dp[0][0] = 1;
        //boundary horiz
        for (int i = 1; i < text1.length(); i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        //boundary vert
        for (int i = 1; i < text2.length(); i++) {
            if (text2.charAt(i) == text1.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i2 = 1; i2 < text2.length(); i2++) {
            for (int i1 = 1; i1 < text1.length(); i1++) {
                dp[i2][i1] = Integer.max(dp[i2 - 1][i1], dp[i2][i1 - 1]);
                if (text1.charAt(i1) == text2.charAt(i2)) {
                    dp[i2][i1] = Integer.max(1 + dp[i2 - 1][i1 - 1], dp[i2][i1]);
                }
            }
        }
        return dp[text2.length() - 1][text1.length() - 1];
    }

    static int longestCommonSubsequence1(String text1, String text2) {

        List<Character> list1 = text1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> list2 = text2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        list1.retainAll(list2);
        list2.retainAll(list1);
        text1 = list1.toString();
        text2 = list2.toString();

        if (text1.length() < text2.length()) {
            String s = text1;
            text1 = text2;
            text2 = s;
        }

        System.out.println("text1 = " + text1);
        System.out.println("text2 = " + text2);
        int[][] dp = new int[text2.length()][text1.length()];
        // boundary condition
        for (int i2 = 0; i2 < text2.length(); i2++) {
            if (text2.charAt(i2) == text1.charAt(0)) {
                dp[i2][0] = 1;
            }
        }
        //print2D(dp);
        for (int i1 = 1; i1 < text1.length(); i1++) {
            if (text1.charAt(i1) == text2.charAt(0)) {
                dp[0][i1] = 1;
            } else dp[0][i1] = dp[0][i1 - 1];
        }
        //print2D(dp);
        for (int i2 = 1; i2 < text2.length(); i2++) {
            for (int i1 = 1; i1 < text1.length(); i1++) {
                if (text1.charAt(i1) == text2.charAt(i2)) {
                    dp[i2][i1] = dp[i2 - 1][i1 - 1] + 1;
                } else {
                    dp[i2][i1] = Integer.max(dp[i2 - 1][i1], dp[i2][i1 - 1]);
                }
            }
        }
        print2D(dp);
        int toReturn = 0;
        for (int i = 0; i < text2.length(); i++) {
            toReturn = Integer.max(toReturn, dp[i][text1.length() - 1]);
        }
        return toReturn;
    }

    /*
        static Set<Character> stringToSet(String str) {
            Set<Character> toReturn = new HashSet<>();
            for (int i = 0; i < str.length(); i++) {
                toReturn.add(str.charAt(i));
            }
            return toReturn;
        }
    */
    static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            System.out.println(Arrays.toString(ar));
        }
        System.out.println();
    }

}

